package inf112.skeleton.app.view;

/** 
 * An interface which specifies methods for updating potential animations, 
 * as well as updating a potential GameBoard.
 */
public interface UpdateableView {
   
   /** Call this method at a specific time interval to update the animations */
   public void updateAnimations();

   /**
    * Call to update the GameBoard in view. 
    * If the new board has different dimensions compared to the old one, 
    * the view should update its cellSize to make sure the entire board fits
    * into the window.
    */
   public void updateBoard();
}
