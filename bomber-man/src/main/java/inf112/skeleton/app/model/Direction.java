package inf112.skeleton.app.model;

/**
 * This enum represents the possible directions in the game. Each direction has
 * an associated deltaX and deltaY value that represents the change in
 * coordinates.
 */
public enum Direction {
    UP(0, -1),
    DOWN(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0);

    /** The change in coordinates associated with the direction */
    public final int deltaX, deltaY;

    /**
     * Creates a new Direction with the specified delta values for x and y.
     * 
     * @param deltaX the change in x-coordinate
     * @param deltaY the change in y-coordinate
     */
    Direction(int deltaX, int deltaY) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }
}