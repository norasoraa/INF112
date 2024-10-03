package inf112.skeleton.app.model.entities;

import inf112.skeleton.app.model.CellPosition;

/*
 * Framework for all game entities, like players, enemies, or obstacles.
 */
public abstract class Entity {

    protected CellPosition position;

    /**
     * Constructs an entity and initializes its position.
     * 
     * @param position The starting position of the entity on the game board.
     */
    public Entity(CellPosition position) {
        this.position = position;
    }

    /**
     * Returns the current position of the entity.
     * 
     * @return The current position of the entity on the game board.
     */
    public CellPosition getPosition() {
        return position;
    }
    
}
