package inf112.skeleton.app.model.entities;

import inf112.skeleton.app.model.CellPosition;

/**
 * Interface that limits the access of information into entities. 
 * Only the position of the entity will be viewable.
 */
public interface ViewableEntity {
   
   /**
    * Gets a cell position.
    * @return a {@link CellPosition} object
    */
   public CellPosition getPosition();
}
