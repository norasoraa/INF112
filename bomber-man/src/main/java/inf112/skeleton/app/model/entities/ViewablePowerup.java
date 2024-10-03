package inf112.skeleton.app.model.entities;

/** Interface that limits the access of information into powerups. 
 * It extends the ViewableEntity-interface.
 * Only the type of the powerup, as well as position
 * (inherited from the ViewableEntity-interface) will be viewable */
public interface ViewablePowerup extends ViewableEntity {

   /** Gets the type of the powerup, represented by an int value */
   public int getType();
}

