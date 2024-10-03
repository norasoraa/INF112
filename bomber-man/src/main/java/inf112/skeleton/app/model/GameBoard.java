package inf112.skeleton.app.model;

import java.util.List;

import inf112.skeleton.app.model.grid.Grid;
import inf112.skeleton.app.utilities.Constants;

/**
 * Represents the game board, which is a grid of characters organized in rows
 * and columns.
 * <p>
 * This class extends the {@link Grid} class to provide functionality for
 * accessing the game board.
 */
public class GameBoard extends Grid<Character> {

    /**
     * Creates a new GameBoard from the specified 2D list of characters.
     * Each character represents a cell on the board, where the cell is either an
     * empty cell or part of the wall. All other entities are drawn on top of the
     * game board.
     * 
     * @param characterGrid 2D-list representing the cells on the board
     */
    public GameBoard(List<List<Character>> characterGrid) {
        super(characterGrid);
    }

    /**
     * Checks if the specified cell position corresponds to a wall on the game
     * board.
     * 
     * @param cp The cell position to check, represented as a {@link CellPosition}
     *           object.
     * @return {@code true} if the cell at the specified position is a wall;
     *         {@code false} otherwise.
     */
    public boolean isWallAt(CellPosition cp) {
        return get(cp) == Constants.WALL;
    }
}
