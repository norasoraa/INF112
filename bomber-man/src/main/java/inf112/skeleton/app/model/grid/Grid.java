package inf112.skeleton.app.model.grid;

import java.util.List;

import inf112.skeleton.app.model.CellPosition;

/** 
 * A 2D grid with rows and columns, where each cell contains a value
 * representing its content.
 */
public class Grid<E> implements IGrid<E> { 

    private List<List<E>> grid;
    private int rows;
    private int cols;
   
    /**
     * Creates a grid based on the specified 2D-list. The grid represents 
     * a collection of elements organized in rows and columns. <p>
     * Use {@link #rows()} and {@link #cols()} to extract the dimensions
     * of the grid.
     * 
     * @param gridToDraw the 2D-list representing the grid
     * @throws IllegalArgumentException if the specified grid is null
     */
    public Grid(List<List<E>> gridToDraw) {
        if (gridToDraw == null) {
            throw new IllegalArgumentException("The grid to be drawn cannot be null!");
        }
        this.grid = gridToDraw;
        this.rows = grid.size();
        this.cols = grid.get(0).size();
    }

    @Override
    public int rows() {
        return this.rows;
    }

    @Override
    public int cols() {
        return this.cols;
    }

    @Override
    public boolean positionIsOnGrid(CellPosition pos) {
        return (
            (pos.col() >= 0) && (pos.col() < cols) &&
            (pos.row() >= 0) && (pos.row() < rows)
        );
    }

    @Override
    public E get(CellPosition pos) {
        if (positionIsOnGrid(pos)) {
            return grid.get(pos.row()).get(pos.col());  
        }
        throw new IndexOutOfBoundsException("The position does not exist in the grid");
    }
}

