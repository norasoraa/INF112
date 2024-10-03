package inf112.skeleton.app.model.entities;

import inf112.skeleton.app.model.CellPosition;
/**
 * Utility class for creating bombs.
 */
public class BombFactory {

    /**
     * Generates a bomb according to the provided cell position and bomb range.
     * 
     * @param playerPos The cell position to place bomb
     * @param range The range of the bomb
     * @return a {@link #Bomb} object
     */
    public static Bomb createBomb(CellPosition playerPos, int range) {
        return new Bomb(playerPos, range);
    }

}
