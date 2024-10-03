package inf112.skeleton.app.model.entities;

import java.util.Random;

import inf112.skeleton.app.model.CellPosition;
import inf112.skeleton.app.model.Direction;
import inf112.skeleton.app.model.GameBoard;

/**
 * Represents an enemy entity in the game.
 * Enemies can move around the game board and interact with other entities.
 */

public class Enemy extends Entity implements MovingViewableEntity {
   private GameBoard board;
   private EntityModel model;
   private Direction facingDirection;
   private Random rand;

   /**
    * Constructs a new enemy at the specified position on the game board.
    * 
    * @param row   the row position of the enemy
    * @param col   the column position of the enemy
    * @param board the game board on which the enemy exists
    * @param model the entity model representing the game entities
    */
   public Enemy(int row, int col, GameBoard board, EntityModel model) {
      super(new CellPosition(row, col));
      this.rand = new Random();
      this.board = board;
      this.model = model;
   }

   /**
    * Generates a random direction for the enemy to move.
    * 
    * @return a randomly chosen direction for movement
    */
   public Direction getRandomDirection() {
      Direction[] directions = Direction.values();
      return directions[rand.nextInt(directions.length)];
   }

   /**
    * Calculates the new position of the enemy after moving in a given direction.
    * 
    * @param currentPosition the current position of the enemy
    * @param direction       the direction in which the enemy intends to move
    * @return the new position after moving in the specified direction
    */
   private CellPosition calculateNewPosition(CellPosition currentPosition, Direction direction) {
      int newRow = currentPosition.row();
      int newCol = currentPosition.col();

      switch (direction) {
         case UP:
            newRow--;
            break;
         case DOWN:
            newRow++;
            break;
         case LEFT:
            newCol--;
            break;
         case RIGHT:
            newCol++;
            break;
      }

      return new CellPosition(newRow, newCol);
   }

   /**
    * Checks if a move to the specified CellPosition is valid for the enemy.
    * 
    * @param cp
    * @return true if the move is valid, false otherwise.
    */
   private boolean isValidMove(CellPosition cp) {
      if (board.isWallAt(cp))
         return false;

      if (model.isBarrelAt(cp))
         return false;

      if (model.isEnemyAt(cp)) {
         return false;
      }
      return true;
   }

   /**
    * Sets the position of the enemy to the spesicied cell position.
    * 
    * @param newPosition
    */
   private void setPosition(CellPosition newPosition) {
      this.position = newPosition;
   }

   /**
    * Moves the enemy in the specified direction if the move is valid.
    * 
    * @param direction the direction to move in
    * @return {@code true} if the move is successful, {@code false} otherwise
    */
   public boolean move(Direction direction) {
      CellPosition newPosition = calculateNewPosition(this.getPosition(), direction);
      if (isValidMove(newPosition)) {
         setPosition(newPosition);
         return true;
      }
      return false;
   }

   /** Sets the facing direction of the enemy, only if the direction is
    * LEFT or RIGHT. The facing direction is needed in animation. */
   public void setFacingDirection(Direction direction) {
      if (direction == Direction.LEFT || direction == Direction.RIGHT) {
         this.facingDirection = direction;
      }
   }

   @Override
   public Direction getDirection() {
      return this.facingDirection;
   }

   @Override
   public boolean isMoving() {
      return true;  // Enemies are always moving
   }

}