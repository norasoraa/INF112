package inf112.skeleton.app.controller;

import inf112.skeleton.app.model.Direction;

/**
 * The Controller depends on access to the model. To encapsulate our model as
 * much as possible, we use this interface that describes which methods the
 * controller needs access to. Then we let the model GameModel implement this
 * interface. The Controller will thus never know that it is (actually) working
 * with a GameModel, it only knows that it is a ControllableModel.
 */
public interface ControllableModel {

    /**
     * Initiates the player's movement in the specified direction.
     * <p>
     * This method is intended to be called from the controller, triggering the
     * player's move method with the given direction.
     *
     * @param dir The direction in which the player is intended to move.
     */
    void checkAndMovePlayer(Direction dir);

    /**
     * If the player has more bombs to lay, it places a bomb on the player's
     * position with range based on the player's bomb
     * range.
     */
    void placeBomb();

    /**
     * Should be called from the controller, once per second.
     * If the secondsUntilGameOver are <= 0, the gamestate is set to GAME_OVER.
     */
    void decreaseSecondsUntilGameEnd();

    /** Gets the amount of seconds until the game is over */
    int getSecondsUntilGameOver();

    /** Is set from the controller */
    void setPlayerHurtTick(int tick);

    /** Should be called from the controller, to check if player is hurt. */
    boolean isPlayerHurt();

    /**
     * Is called from the controller if the player is hurt and
     * not currently invincible (due to invincibility frames).
     */
    void takePlayerDamage();

    /**
     * Finds a new random direction for each enemy in each call. If the move is
     * valid, the enemy is moved in this direction. For each move, it checks if an
     * enemy hits the player or if an enemy is hit by an explosion.
     */
    void moveEnemies();

    /**
     * Method to reset currentlevel
     */
    void resetLevel();

    /**
     * Method to go to next level. If currentlevel is last level it will go back to first level.
     */
    void goToNextLevel();

    /**
     * Called to set the moving-status of the player.
     * If the user is currently pressing the arrow keys, moving should be set
     * to true, else false.
     * @param moving
     */
    void setPlayerMoving(boolean moving);

}
