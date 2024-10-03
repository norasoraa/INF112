package inf112.skeleton.app.model.entities;

import inf112.skeleton.app.model.CellPosition;

/**
 * Represents a barrel entity in the game.
 * A barrel could contain a power-up which will be available when the barrel
 * explodes.
 */
public class Barrel extends Entity implements ViewableEntity {

   /**
    * Constructs a new barrel at the specified position.
    * 
    * @param row the row position of the barrel
    * @param col the column position of the barrel
    */
   public Barrel(int row, int col) {
      super(new CellPosition(row, col));
   }

}
