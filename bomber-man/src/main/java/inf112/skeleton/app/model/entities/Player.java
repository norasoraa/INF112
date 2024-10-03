package inf112.skeleton.app.model.entities;

import inf112.skeleton.app.model.CellPosition;
import inf112.skeleton.app.model.Direction;
import inf112.skeleton.app.model.GameBoard;
import inf112.skeleton.app.utilities.Constants;

/**
 * Represents a player in the game, has healthpoints and is capable of moving
 * and placing bombs.
 */
public class Player extends Entity implements MovingViewableEntity {

   private int healthPoints;
   public boolean movedIntoEnemy;
   private final GameBoard BOARD;
   private EntityModel model;
   private int maxBombs;
   private int bombRange;
   private Direction facingDirection = Direction.RIGHT;
   private boolean isMoving = true;

   /**
    * Initializes a new Player with a starting position, facing direction, health
    * points, bomb capacity, and range.
    * 
    * @param model Reduced game model.
    * @param board The game board.
    * @param row   Starting row position.
    * @param col   Starting column position.
    */
   public Player(EntityModel model, GameBoard board, int row, int col) {
      super(new CellPosition(row, col)); // Creates entity
      this.model = model;
      this.BOARD = board;
      this.healthPoints = Constants.PLAYER_DEFAULT_HEALTH;
      this.maxBombs = Constants.DEFAULT_MAX_BOMBS;
      this.bombRange = Constants.DEFAULT_EXPLOSION_RANGE;
   }

   /**
    * Attempts to move the player in a specified direction, checking for validity
    * and updating position. If the direction is either LEFT or RIGHT, 
    * it sets the player's facing-direction to this value.
    * 
    * @param dir The direction to move the player.
    * @return True if the move is successful, false otherwise.
    */
   public boolean move(Direction dir) {
      if (dir == Direction.LEFT || dir == Direction.RIGHT) {
         this.facingDirection = dir;
      }
      CellPosition potentialPos = new CellPosition(
            this.getPosition().row() + dir.deltaY,
            this.getPosition().col() + dir.deltaX);

      checkMoveIntoEnemy(potentialPos);
      if (isValidMove(potentialPos)) {
         this.position = potentialPos; // update position
         return true;
      }

      return false;
   }

   private void checkMoveIntoEnemy(CellPosition newCp) {
      if (model.isEnemyAt(newCp)) {
         this.movedIntoEnemy = true;
      }
   }

   /**
    * Determines if a proposed move to a new cell position is valid.
    * 
    * @param cp Cell position to evaluate.
    * @return True if the move is valid, false otherwise.
    */
   private boolean isValidMove(CellPosition cp) {
      if (!BOARD.positionIsOnGrid(cp))
         return false;

      if (BOARD.isWallAt(cp) || model.isBarrelAt(cp))
         return false;

      if (model.isEnemyAt(cp)) {
         return false;
      }
      return true;
   }

   /**
    * Reduces the player's health points by one and 
    * delegates to the model to check for player death.
    */
   public void takeDamage() {
      healthPoints -= 1;
      this.movedIntoEnemy = false;
      model.checkForPlayerDeath();
   }

   /**
    * Sets the maximum number of bombs the player can lay at one time.
    * 
    * @param max New maximum bomb capacity.
    */
   public void setMaxBombs(int max) {
      this.maxBombs = max;
   }

   /**
    * Sets the moving-status of the player
    * 
    * @param moving true if the player is moving, false if stationary.
    */
    public void setMoving(boolean moving) {
      this.isMoving = moving;
   }

   /**
    * Increases the bomb explosion range by 1.
    */
   public void increaseBombRange() {
      this.bombRange++;
   }

   /**
    * Increases the player's health points by one, up to a maximum of three.
    * If the player's health points are already at the maximum, this method has no effect.
    */
   public void increaseLife() {
      if (this.healthPoints < Constants.PLAYER_DEFAULT_HEALTH){
         this.healthPoints ++;
      }
   }

   // Getter methods

   /**
    * Returns the player's current health points.
    * 
    * @return The current health points of the player.
    */
   public int getHealthPoints() {
      return this.healthPoints;
   }

   /**
    * Gets the maximum number of bombs the player can place at one time.
    * 
    * @return The maximum number of bombs.
    */
   public int getMaxBombs() {
      return maxBombs;
   }

   /**
    * Retrieves the current explosion range of the player's bombs.
    * 
    * @return The bomb explosion range.
    */
   public int getBombRange() {
      return bombRange;
   }

   @Override
   public Direction getDirection() {
      return this.facingDirection;
   }

   @Override
   public boolean isMoving() {
      return this.isMoving;
   }

}
