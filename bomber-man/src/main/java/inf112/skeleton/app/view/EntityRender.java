package inf112.skeleton.app.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import inf112.skeleton.app.model.CellPosition;
import inf112.skeleton.app.model.Direction;
import inf112.skeleton.app.model.entities.Explosion;
import inf112.skeleton.app.model.entities.MovingViewableEntity;
import inf112.skeleton.app.model.entities.ViewableEntity;
import inf112.skeleton.app.model.entities.ViewablePowerup;
import inf112.skeleton.app.utilities.Constants;

/** Class that contains methods for drawing the entities on the board. */
public class EntityRender {

    private ViewableModel model;

    /**
     * Constructs a new EntityRender with the given model. A call to
     * {@link #drawAllEntities()} on this object will draw all the entities on the
     * board.
     * 
     * @param model the model as a {@link ViewableModel}
     */
    public EntityRender(ViewableModel model) {
        this.model = model;
    }

    /**
     * Draws all entities in the game, in this order
     * (from bottom layer to top layer):
     * <p> -Barrels
     * <p> -Enemies
     * <p> -Bombs
     * <p> -Powerups
     * <p> -Explosions
     * <p> -Player
     */
    protected void drawAllEntities(SpriteBatch batch) {
        // Barrels
        for (ViewableEntity b : model.getBarrelVisuals()) {
            drawEntity(batch, b, TextureManager.barrelTexture);
        }

        // Enemies
        for (MovingViewableEntity e : model.getEnemyVisuals()) {
            drawEnemy(batch, e);
        }

        // Bombs
        for (ViewableEntity bomb : model.getBombVisuals()) {
            drawEntity(batch, bomb, TextureManager.bombAnimations[0][GameView.animationIndex]);
        }

        // Powerups
        for (ViewablePowerup powerUp : model.getPowerUpVisuals()) {
            drawPowerUp(batch, powerUp);
        }

        // Explosions
        drawExplosions(batch);

        // Player
        drawPlayer(batch);
    }

    private void drawPowerUp(SpriteBatch batch, ViewablePowerup powerUp) {
    TextureRegion texture;
    switch (powerUp.getType()) {
        case Constants.POWERUP_EXPLOSION_RANGE:
            texture = TextureManager.bombRangePowerupAnimations[0][GameView.animationIndex % 4];
            break;
        case Constants.POWERUP_MAX_BOMBS:
            texture = TextureManager.extraBombPowerupAnimations[0][GameView.animationIndex % 4];
            break;
        case Constants.POWERUP_EXTRA_LIFE:
            texture = TextureManager.extraLifePowerupAnimations[0][GameView.animationIndex % 4];
            break;
        default:
            texture = null;
    }
    
    if (texture != null) {
        drawEntity(batch, powerUp, texture);
    }
}

    private void drawEnemy(SpriteBatch batch, MovingViewableEntity e) {
        int animationRow = 0;
        if (e.getDirection() == Direction.RIGHT) {
            animationRow++; // Animations for RIGHT are 1 row below animations for LEFT.
        }
        drawEntity(batch, e, TextureManager.ghostAnimations[animationRow][GameView.animationIndex]);
    }

    private void drawPlayer(SpriteBatch batch) {
        // If the player is hurt we want the player to blink.
        // We acchieve this by NOT drawing the player every other frame,
        // if the player is hurt.
        if ((model.getPlayerHurtTick() > 0) && (model.getPlayerHurtTick() % 4 == 0)) {
            return;
        } else {
            int animationRow = 0;
            MovingViewableEntity player = model.getPlayerVisuals();
            if (player.isMoving()) {
                animationRow = 2; // Moving animations start at row 2
            }
            if (player.getDirection() == Direction.LEFT) {
                animationRow++; // Animations for LEFT are 1 row below animations for RIGHT.
            }
            drawEntity(batch, player, TextureManager.playerAnimations[animationRow][GameView.animationIndex]);
        }
    }

    // We need a separate method for drawing explosions, since they are not
    // entities.
    private void drawExplosions(SpriteBatch batch) {
        for (Explosion explosion : model.getExplosions()) {
            for (CellPosition cp : explosion.impactPositions) {
                float x = (GameView.boardArea.x + cp.col() * GameView.cellSize);
                float y = (GameView.boardArea.y + GameView.boardArea.height - (cp.row() + 1) * GameView.cellSize);
                batch.draw(TextureManager.explosionsAnimations[0][GameView.animationIndex], x, y, GameView.cellSize,
                        GameView.cellSize);
            }
        }
    }

    private void drawEntity(SpriteBatch batch, ViewableEntity entity, TextureRegion image) {
        float x = (GameView.boardArea.x + entity.getPosition().col() * GameView.cellSize);
        float y = (GameView.boardArea.y + GameView.boardArea.height
                - (entity.getPosition().row() + 1) * GameView.cellSize);
        batch.draw(image, x, y, GameView.cellSize, GameView.cellSize);
    }

}
