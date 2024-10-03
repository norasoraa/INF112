package inf112.skeleton.app.model.grid;

import inf112.skeleton.app.model.CellPosition;

/**
 * The IGrid interface is used to represent a grid data structure and provides
 * methods for accessing and managing grid elements. It extends the
 * {@link GridDimension} interface to provide additional functionality for
 * accessing the dimensions of the grid.
 * <p>
 * Source of this interface: Tetris INF101 Spring 2023
 */
public interface IGrid<E> extends GridDimension {

   /**
    * Gets the current value at the given coordinate.
    * 
    * @param pos the position to get
    * @return the value stored at the position
    * @throws IndexOutOfBoundsException if the position does not exist in the grid
    */
   E get(CellPosition pos);

   /**
    * Reports whether the position is within bounds for this grid
    * 
    * @param pos position to check
    * @return {@code true} if the coordinate is within bounds, {@code false}
    *         otherwise
    */
   boolean positionIsOnGrid(CellPosition pos);
}