package inf112.skeleton.app.model.grid;

/**
 * The GridDimension interface represents the dimensions of a grid in terms of
 * rows and columns. It provides methods to retrieve the number of rows and
 * columns in a GridDimension object.
 */
public interface GridDimension {

  /**
   * Returns number of rows in GridDimension object
   * 
   * @return int rows
   */
  int rows();

  /**
   * Returns number of columns in GridDimension object
   * 
   * @return int cols
   */
  int cols();
}
