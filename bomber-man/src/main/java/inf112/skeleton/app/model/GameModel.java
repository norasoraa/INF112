package inf112.skeleton.app.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.AbstractMap.SimpleEntry;

import inf112.skeleton.app.audioplayer.AudioPlayer;
import inf112.skeleton.app.controller.ControllableModel;
import inf112.skeleton.app.model.entities.*;
import inf112.skeleton.app.utilities.Constants;
import inf112.skeleton.app.view.ViewableModel;

/**
 * This class represents the model component of the game. It implements the
 * ViewableModel, ControllableModel, TestableModel and EntityModel interfaces.
 * <p>
 * It is responsible for managing the logic, data structures and the state of
 * the game, incuding the game board, player, enemies, bombs, explosions,
 * barrels, power-ups, and audio player.
 */
public class GameModel implements ViewableModel, ControllableModel, EntityModel, TestableModel {
    protected LevelReader levelReader;
    protected GameBoard board;
    protected int secondsUntilGameover;
    protected AudioPlayer audioPlayer;

    // entities
    protected Player player;
    protected ArrayList<Enemy> enemies;
    protected ArrayList<Bomb> activeBombs;
    protected ArrayList<Barrel> barrels;
    protected ArrayList<Explosion> activeExplosions;
    protected ArrayList<PowerUp> powerUps;
    protected BombFactory bombFactory;

    private PowerUpFactory powerUpFactory;
    private int playerHurtTick;
    private Integer currentLevel;
    private HashMap<Integer,String> allLevels;
    
    // Power-Up display logic
    private SimpleEntry<ViewableEntity, String> lastActivatedPowerup; // Tracks most recent power-up and description.
    private String powerUpText = ""; // Power-up text to be displayed.
    private int textTimer; // Controls display duration of power-up text.

    /**
     * Creates a new GameModel with the specified audio player.
     * <p>
     * It sets the new level and initializes the entities with {@link #setLevel()}.
     * It also initializes the {@link PowerUpFactory} that produces power-ups for
     * the player.
     * 
     * @param audioPlayer the audio player used represented as {@link AudioPlayer}
     */
    public GameModel(AudioPlayer audioPlayer) {
        this.currentLevel = 1;
        this.levelReader = new LevelReader();
        this.allLevels = Constants.getAllLevels();
        this.textTimer = Constants.POWERUP_TEXT_DURATION;
        this.audioPlayer = audioPlayer;
        this.audioPlayer.setPlayTheme(true);
        this.setLevel(allLevels.get(currentLevel));
    }

    @Override
    // This method is only public because its needed in tests.
    public void setLevel(String path) {
        this.levelReader.setNewLevel(path);
        this.board = levelReader.getBoard();
        this.player = createPlayer();
        this.enemies = createEnemies();
        this.barrels = createBarrel();
        this.activeBombs = new ArrayList<>();
        this.activeExplosions = new ArrayList<>();
        this.powerUps = new ArrayList<>();
        this.powerUpFactory = new PowerUpFactory(player);
        this.secondsUntilGameover = Constants.SECONDS_UNTIL_GAMEOVER;
    }

    /** Creates Player-object from the position stored in LevelReader */
    private Player createPlayer() {
        int playerPosRow = levelReader.getPlayerPos().row();
        int playerPosCol = levelReader.getPlayerPos().col();
        return new Player(this, board, playerPosRow, playerPosCol);
    }

    /** Creates Enemy-objects from the positions stored in LevelReader */
    private ArrayList<Enemy> createEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();
        for (CellPosition enemyPos : levelReader.getEnemiesPos()) {
            enemies.add(new Enemy(enemyPos.row(), enemyPos.col(), board, this));
        }
        return enemies;
    }

    /** Creates Barrel-objects from the positions stored in LevelReader */
    private ArrayList<Barrel> createBarrel() {
        ArrayList<Barrel> barrels = new ArrayList<>();
        for (CellPosition barrelPos : levelReader.getBarrelsPos()) {
            barrels.add(new Barrel(barrelPos.row(), barrelPos.col()));
        }
        return barrels;
    }

    @Override
    public void decreaseSecondsUntilGameEnd() {
        if (GameState.currentState == GameState.PLAYING) {
            this.secondsUntilGameover--;
            tickEntities();
            if (secondsUntilGameover <= 0) {
                GameState.currentState = GameState.GAME_OVER;
            }
        }
    }

    /* Ticks down timers and triggers actions for expired entities. */
    private void tickEntities() {
        /* decrease bomb countdowns */
        activeBombs.forEach(Bomb::tick);
        ArrayList<Bomb> expiredBombs = new ArrayList<>();
        activeBombs.stream().filter(bomb -> bomb.isOver()).forEach(expiredBombs::add);
        expiredBombs.forEach(this::triggerExplosion);

        /* decrease explosion countdowns */
        activeExplosions.forEach(Explosion::tick);
        ArrayList<Explosion> expiredExplosions = new ArrayList<>();
        activeExplosions.stream().filter(explosion -> explosion.isOver()).forEach(expiredExplosions::add);
        expiredExplosions.forEach(this::endExplosion);

        /* decrease powerup text timer */
        tickPowerupTextTimer();
    }

    /* Decreases the timer for description of recently activated power-up */
    private void tickPowerupTextTimer() {
        this.textTimer--;
        // Clear the power-up text if timer expired
        if (textTimer <= 0) powerUpText = "";
    }


    @Override
    public void checkAndMovePlayer(Direction dir) {
        if (player.move(dir)) {
            lookForPowerUp();
        }
    }

    /* Check wether player is on the same position as power-up*/
    private void lookForPowerUp() {
        ArrayList<PowerUp> listCopy = new ArrayList<>(powerUps);
        for (PowerUp powerUp : listCopy) {
            if (powerUp.getPosition().equals(player.getPosition())) {
                activatePowerUp(powerUp);
            }
        }
    }

    /* Activates the power-up, plays a sound, and updates ui-text */
    private void activatePowerUp(PowerUp powerUp) {
        powerUp.activate(); // activate specific powerup
        audioPlayer.playSound(Constants.SFX_SUCCESS);
        powerUps.remove(powerUp); // remove powerup from cell
        textTimer = Constants.POWERUP_TEXT_DURATION; // restart timer
        powerUpText = Constants.POWERUP_TEXT.get(powerUp.getType()); // set description
        lastActivatedPowerup = new SimpleEntry<>(powerUp, powerUpText);
    }

    @Override
    public void moveEnemies() {
        ArrayList<Enemy> enemiesCopy = new ArrayList<>(this.enemies); //needed to avoid concurrent modification
        for (Enemy enemy : enemiesCopy) {
            Direction randomDirection = enemy.getRandomDirection();
            if (enemy.move(randomDirection)) { 
                enemy.setFacingDirection(randomDirection);
                if (isCellExploding(enemy.getPosition())) {
                    enemies.remove(enemy);
                }
            }
        }
        checkIfAllEnemiesKilled();
    }

    @Override
    public boolean isBarrelAt(CellPosition cp) {
        return isEntityInCell(cp, barrels);
    }

    @Override
    public boolean isBombAt(CellPosition cp) {
        return isEntityInCell(cp, activeBombs);
    }

    @Override
    public boolean isEnemyAt(CellPosition cp) {
        return isEntityInCell(cp, enemies);
    }

    /* Checks if any entity of a specified type is present in the given cell position.  */
    private boolean isEntityInCell(CellPosition cp, ArrayList<? extends Entity> entities) {
        return entities.stream().anyMatch(entity -> entity.getPosition().equals(cp));
    }

    @Override
    public boolean isCellExploding(CellPosition cp) { /* Explosion extender ikke entity, får dermed egen metode */
        if (activeExplosions.isEmpty())
            return false;

        for (Explosion explosion : activeExplosions) {
            for (CellPosition explodingCell : explosion.impactPositions) {
                if (explodingCell.equals(cp)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void placeBomb() {
        if (player.getMaxBombs() - activeBombs.size() > 0) {
            Bomb newBomb = BombFactory.createBomb(player.getPosition(), player.getBombRange());
            activeBombs.add(newBomb);
        }
    }

    @Override
    public void triggerExplosion(Bomb explodingBomb) {
        if (!activeBombs.contains(explodingBomb)) { return; } // Exit if bomb is inactive
        activeBombs.remove(explodingBomb);
        Explosion newExplosion = new Explosion(this, board, explodingBomb.getPosition(), explodingBomb.getRange());
        activeExplosions.add(newExplosion);
        processExplosionImpact(newExplosion);
        audioPlayer.playSound(Constants.SFX_EXPLOSION);
    }

    /**
     * Processes explosion effects: removes enemies, explodes barrels, and triggers
     * bombs at impacted positions. Finally checks for victory condition; 
     * all enemies eliminated.
     */
    private void processExplosionImpact(Explosion newExplosion) {
        ArrayList<Bomb> triggeredBombs = new ArrayList<>();
        for (CellPosition cp : newExplosion.impactPositions) {
            // Eliminate enemy at impacted position
            enemies.removeIf(enemy -> enemy.getPosition().equals(cp));

            // Explode barrel
            explodeBarrels(cp);

            // Trigger bombs for chain reaction
            activeBombs.stream()
                    .filter(bomb -> bomb.getPosition().equals(cp))
                    .forEach(triggeredBombs::add);
        }
        chainReaction(triggeredBombs); // initiate further explosions
        checkIfAllEnemiesKilled(); // check for victory
    }

    /* Looks for and explodes barrel at a given position. Also checks if a power-up should be created. */
    private void explodeBarrels(CellPosition cp) {
        barrels.removeIf(barrel -> {

            // check for barrel at position
            if (barrel.getPosition().equals(cp)) {
                checkAndCreatePowerUp(cp);
                return true; // Indicate that the barrel should be removed
            }

            return false; // no barrel to remove
        });
    }

    /* Determines if a power-up exists based on predefined chance */
    private boolean containsPowerUp() {
        Random rand = new Random();
        float chance = rand.nextFloat(); // float between 0-1
        float threshold = Constants.POWERUP_THRESHOLD;
        return chance < threshold;
    }

    /* Checks and creates a power-up at the specified position if conditions are met. */
    private void checkAndCreatePowerUp(CellPosition cp) {
        if (containsPowerUp()) {
            powerUps.add(powerUpFactory.createRandomPowerUp(cp));
        }
    }

    @Override
    public void checkForPlayerDeath() {
        if (player.getHealthPoints() <= 0) {
            GameState.currentState = GameState.GAME_OVER;
        }
    }

    /* Updates game state to victory if all enemies are eliminated. */
    private void checkIfAllEnemiesKilled() {
        if (enemies.size() == 0) {
            GameState.currentState = GameState.LEVEL_FINISHED;
        }
    }

    /* Detonate all bombs overlapped by another explosion */
    private void chainReaction(ArrayList<Bomb> triggeredBombs) {
        for (Bomb bomb : triggeredBombs) {
            triggerExplosion(bomb);
        }
    }

    @Override
    public void endExplosion(Explosion explosion) {
        activeExplosions.remove(explosion);
    }

    @Override
    public boolean isPlayerHurt() {
        boolean isHurt = (
            // 1. Checks if enemies have moved into the player
            isEntityInCell(player.getPosition(), enemies) || 
            // 2. Checks if player has moved into enemies (yes, this check is also needed)
            player.movedIntoEnemy ||
            // 3. Checks if the player is standing on an explosion
            isCellExploding(player.getPosition())
            );
        return isHurt;
    }

    @Override
    public void takePlayerDamage() {
        audioPlayer.playSound(Constants.SFX_HURT);
        this.player.takeDamage();
    }

    @Override
    public void resetLevel() {
        this.setLevel(allLevels.get(currentLevel));
        GameState.currentState = GameState.PLAYING;
    }

    @Override
    public void goToNextLevel() {
        if(currentLevel == allLevels.size()) {
            this.currentLevel = 1;
        } else {
            this.currentLevel++;
        }

        resetLevel();
    }

    ////////////////////// Getters //////////////////////


    @Override
    public Player getPlayer() {
        return this.player;
    }

    @Override
    public ArrayList<Enemy> getEnemies() {
        return this.enemies;
    }

    @Override
    public ArrayList<Barrel> getBarrels() {
        return this.barrels;
    }

    @Override
    public ArrayList<Bomb> getActiveBombs() {
        return this.activeBombs;
    }

    @Override
    public GameBoard getBoard() {
        return this.board;
    }

    @Override
    public MovingViewableEntity getPlayerVisuals() {
        return this.player;
    }

    @Override
    public ArrayList<? extends ViewableEntity> getBarrelVisuals() {
        return this.barrels;
    }

    @Override
    public ArrayList<? extends MovingViewableEntity> getEnemyVisuals() {
        return this.enemies;
    }

    @Override
    public ArrayList<? extends ViewableEntity> getBombVisuals() {
        return this.activeBombs;
    }

    @Override
    public ArrayList<? extends ViewablePowerup> getPowerUpVisuals() {
        return this.powerUps;
    }

    @Override
    public boolean recentlyActivatedPowerup() {
        return !this.powerUpText.isBlank();
    }

    @Override
    public SimpleEntry<ViewableEntity, String> getPowerupText() {
        return this.lastActivatedPowerup;
    }

    @Override
    public ArrayList<Explosion> getExplosions() {
        return activeExplosions;
    }

    @Override
    public int getSecondsUntilGameOver() {
        return this.secondsUntilGameover;
    }

    @Override
    public int getHealthPoints() {
        return this.player.getHealthPoints();
    }

    @Override
    public int getPlayerHurtTick() {
        return this.playerHurtTick;
    }

    
    @Override
    public int getCurrentLevel() {
        return currentLevel;
    }

    @Override
    public int getMaxBombsVisuals() {
        return this.player.getMaxBombs();
    }


    ////////////////////// Setters //////////////////////
    
    @Override
    public void setPlayerMaxBombs(int maxBombs) {
        this.player.setMaxBombs(maxBombs);
    }

    @Override
    public void setPlayerMoving(boolean moving) {
        this.player.setMoving(moving);
    }

    @Override
    public void setPlayerHurtTick(int tick) {
        this.playerHurtTick = tick;
    }
}