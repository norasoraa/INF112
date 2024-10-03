package inf112.skeleton.app.model.entities;

import inf112.skeleton.app.model.CellPosition;
import inf112.skeleton.app.model.Direction;
import inf112.skeleton.app.model.GameBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

   private EntityModel gameModel;
   private GameBoard gameBoard;
   private CellPosition initialPos;
   private Player player;

   @BeforeEach
   void setUp() {
      // Mock the game model and game board
      gameModel = mock(EntityModel.class);
      gameBoard = mock(GameBoard.class);
      initialPos = new CellPosition(5, 5);
      // Initialize player with mocked dependencies
      player = new Player(gameModel, gameBoard, initialPos.row(), initialPos.col());

      // Ensure the game board is considered valid for movement
      when(gameBoard.positionIsOnGrid(any())).thenReturn(true);
   }

   @Test
   void testMoveWithNoObstacles() {
      // Setup: no obstacles present
      when(gameBoard.isWallAt(any(CellPosition.class))).thenReturn(false);
      when(gameModel.isBarrelAt(any(CellPosition.class))).thenReturn(false);
      when(gameModel.isBombAt(any(CellPosition.class))).thenReturn(false);
      when(gameModel.isEnemyAt(any(CellPosition.class))).thenReturn(false);

      player.move(Direction.LEFT);

      // Verify the player moves left successfully
      assertNotEquals(initialPos, player.getPosition(), "Player should have moved.");
      assertEquals(new CellPosition(5, 4), player.getPosition(), "Player should have moved left.");
   }

   @Test
   void testMoveIntoWall() {
      // Setup: a wall blocks the movement
      when(gameBoard.isWallAt(any(CellPosition.class))).thenReturn(true);

      player.move(Direction.DOWN);

      // Player's position remains unchanged due to the wall
      assertEquals(initialPos, player.getPosition(), "Player should not move into a wall.");
   }

   @Test
   void testMoveIntoBomb() {
      // Setup: a bomb is in the next cell
      when(gameBoard.isWallAt(any(CellPosition.class))).thenReturn(false);
      when(gameModel.isBombAt(any(CellPosition.class))).thenReturn(true);

      player.move(Direction.LEFT);

      // Player moves into the cell with the bomb
      assertEquals(new CellPosition(5, 4), player.getPosition(),
            "Player should be able to move onto bomb");
   }

   @Test
   void testMoveIntoBarrel() {
      // Setup: a barrel blocks the movement
      when(gameBoard.isWallAt(any(CellPosition.class))).thenReturn(false);
      when(gameModel.isBarrelAt(any(CellPosition.class))).thenReturn(true);

      player.move(Direction.LEFT);

      // Player's position should remain unchanged due to the barrel
      assertEquals(initialPos, player.getPosition(), "Player should not move into cell with barrel.");
   }

   @Test
   void testMoveWhenPositionNotOnGrid() {
      // setup: position not on grid
      when(gameBoard.positionIsOnGrid(any(CellPosition.class))).thenReturn(false);

      // Attempt to move the player
      player.move(Direction.RIGHT); // should be invalid

      // Assert that the player's position remains unchanged
      assertEquals(initialPos, player.getPosition(), "Player should not move to a position off the grid.");
   }

   @Test
   void testMoveIntoEnemyPosition() {
      // Setup: an enemy in the next cell
      when(gameBoard.isWallAt(any(CellPosition.class))).thenReturn(false);
      when(gameModel.isEnemyAt(any(CellPosition.class))).thenReturn(true);

      //Attempt to move the player
      player.move(Direction.UP);

      // Assert that the player's position remains unchanged
      assertEquals(initialPos, player.getPosition(), "Spilleren bør ikke kunne flytte seg inn i cellen til en fiende.");
   }

}
