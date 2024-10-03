package inf112.skeleton.app.model.entities;

import inf112.skeleton.app.model.Direction;

/** Interface that limits the access of information into entities. 
 * It extends the ViewableEntity-interface.
 * Only the direction and moving-status, as well as position
 * (inherited from the ViewableEntity-interface) will be viewable */
public interface MovingViewableEntity extends ViewableEntity {

   /**
    * Gets the direction the entity is facing. The direction must be either
    * RIGHT or LEFT
    * @return a {@link Direction} object
    */
   public Direction getDirection();

   /**
    * Returns true if the entity is currently moving. The view needs this information
    * to decide if it should display a standing- or walking-animation.
    * @return isMoving
    */
   public boolean isMoving();
}
