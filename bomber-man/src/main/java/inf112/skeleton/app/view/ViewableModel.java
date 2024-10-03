package inf112.skeleton.app.view;

import java.util.ArrayList;
import java.util.AbstractMap.SimpleEntry;

import inf112.skeleton.app.model.GameBoard;
import inf112.skeleton.app.model.entities.Explosion;
import inf112.skeleton.app.model.entities.MovingViewableEntity;
import inf112.skeleton.app.model.entities.ViewableEntity;
import inf112.skeleton.app.model.entities.ViewablePowerup;

/**
 * The GameView needs to have access to the model in order to draw it, but we
 * don't want to be able to unexpectedly change the model when we do things in
 * GameView. To encapsulate our model, we use this interface that describes
 * which methods GameView needs to draw a Game board. Then we let the model
 * GameModel implement this interface. GameView will thus never know that it
 * is (actually) working with a GameModel, it only knows that it is a
 * ViewableModel.
 */
public interface ViewableModel {

    /**
     * Gets the current GameBoard
     * 
     * @return a {@link GameBoard} object
     */
    public GameBoard getBoard();

    /** Gets the player's hurt-tick */
    public int getPlayerHurtTick();

    /**
     * Gets the viewable information for player
     * 
     * @return a {@link MovingViewableEntity} object
     */
    public MovingViewableEntity getPlayerVisuals();

    /**
     * Gets the viewable information for the barrels
     * 
     * @return an Arraylist of type Barrel
     */
    public ArrayList<? extends ViewableEntity> getBarrelVisuals();

    /**
     * Gets the viewable information for enemies
     * 
     * @return an Arraylist of type Enemy
     */
    public ArrayList<? extends MovingViewableEntity> getEnemyVisuals();

    /**
     * Gets the viewable information for bombs
     * 
     * @return an Arraylist of type Bomb
     */
    public ArrayList<? extends ViewableEntity> getBombVisuals();

    /**
     * Gets the viewable information for powerups
     * 
     * @return an Arraylist of type PowerUp
     */
    public ArrayList<? extends ViewablePowerup> getPowerUpVisuals();

    /**
     * Determines if a power-up was recently activated.
     * 
     * @return true if a power-up has been recently activated, otherwise false.
     */
    public boolean recentlyActivatedPowerup();

    /**
     * Fetches the viewable entity and description of the most recently activated power-up.
     * 
     * @return a SimpleEntry pairing the ViewableEntity of the power-up with its text description.
     */
    public SimpleEntry<ViewableEntity, String> getPowerupText();

    /**
     * Gets a list of explosions. The Explosion-object only contains 
     * getter-methods, and thus is suitable for viewing.
     * 
     * @return an Arraylist of type Explosion
     */
    public ArrayList<Explosion> getExplosions();

    /** Gets the current health points of the player */
    public int getHealthPoints();

    /** Gets the amount of seconds until the game is over */
    public int getSecondsUntilGameOver();


    /** Gets the current level */
    public int getCurrentLevel();

    /** Gets the maximum number of bombs the player can place at one time */
    public int getMaxBombsVisuals();

}
