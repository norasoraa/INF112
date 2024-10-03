package inf112.skeleton.app.model.entities;

import inf112.skeleton.app.model.CellPosition;

/**
 * Encapsulate the logic for entity-specific events within the game.
 * Is passed to entities to restrict direct access to the game model.
 */
public interface EntityModel {

    /**
     * Checks if there is a barrel at the specified cell position.
     * 
     * @param cp The cell position to check.
     * @return true if a barrel is present at the given position, false otherwise.
     */
    boolean isBarrelAt(CellPosition cp);

    /**
     * Checks if there is a bomb at the specified cell position.
     * 
     * @param cp The cell position to check.
     * @return true if a bomb is present at the given position, false otherwise.
     */
    boolean isBombAt(CellPosition cp);

    /**
     * Checks if there is an enemy at the specified cell position.
     * 
     * @param cp The cell position to check.
     * @return true if an enemy is present at the given position, false otherwise.
     */
    boolean isEnemyAt(CellPosition cp);

    /**
     * Checks if the specified cell position is currently exploding.
     * 
     * @param cp The cell position to check.
     * @return true if the cell is exploding, false otherwise.
     */
    boolean isCellExploding(CellPosition cp);

    /**
     * Removes an exploding bomb, creates explosion at its location, and
     * processes the explosion's impact.
     * <p>
     * Invoked by the bomb itself, when the timer expires.
     * 
     * @param b The bomb to be detonated.
     */
    void triggerExplosion(Bomb b);

    /**
     * Ends an explosion, removing its effects from the game.
     * <p>
     * Invoked by the explosion itself, when its timer expires.
     * 
     * @param e The explosion to end.
     */
    void endExplosion(Explosion e);

    /**
     * Checks if the player's health points are depleted and sets the game state to GAME_OVER if so.
     */
    void checkForPlayerDeath();
}
