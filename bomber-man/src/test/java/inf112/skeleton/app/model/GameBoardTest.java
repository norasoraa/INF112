package inf112.skeleton.app.model;

import static inf112.skeleton.app.testUtilities.TestHelpMethods.GetMockListener;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;

import inf112.skeleton.app.utilities.Constants;

public class GameBoardTest {
   LevelReader levelReader = new LevelReader();
   GameBoard board;

   /** We make a mock Gdx-application, so that the grid can utilize the Gdx-library. */
   @BeforeAll
   static void setUpBeforeAll() {
      HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
      ApplicationListener listener = GetMockListener(config);
      new HeadlessApplication(listener, config);
   }

   @BeforeEach
   void setUpBeforeEach() {
      this.levelReader.setNewLevel(Constants.TEST_LEVEL1);
      this.board = levelReader.getBoard();
   }

   
   /*
   ###########
   #-P-------#
   #-#-#-#-#-#
   #------E--#
   #-#-#-#-#-#
   #--BBBB---#
   #-#-#-#-#-#
   #--BB--E--#
   #-#-#-#-#-#
   #-----E---#
   ###########
    */

   @Test
   void testGet() {
      // Check that the content of pos (0, 0) and (1, 1) is as seen above
      char content1 = board.get(new CellPosition(0, 0));
      char content2 = board.get(new CellPosition(1, 1));
      assertEquals(Constants.WALL, content1);
      assertEquals(Constants.EMPTY, content2);

      // Check that the board throws errors when given faulty values.
		assertThrows(IndexOutOfBoundsException.class, 
			() -> {
				board.get(new CellPosition(-1, 0));
			});

      assertThrows(IndexOutOfBoundsException.class, 
			() -> {
				board.get(new CellPosition(0, -1));
			});

      assertThrows(IndexOutOfBoundsException.class, 
			() -> {
				board.get(new CellPosition(11, 0)); // 11 is outside this grid
			});

      assertThrows(IndexOutOfBoundsException.class, 
			() -> {
				board.get(new CellPosition(0, 11)); // 11 is outside this gr
			});

   }

   @Test
   void testPositionIsOnGrid() {
      // Checks that it returns true for positions on the grid
      CellPosition cp1 = new CellPosition(0, 0);
      CellPosition cp2 = new CellPosition(10, 10);
      assertTrue(board.positionIsOnGrid(cp1));
      assertTrue(board.positionIsOnGrid(cp2));

      // Checks that it returns false for positions not on the grid
      CellPosition cp3 = new CellPosition(-1, 0);
      CellPosition cp4 = new CellPosition(0, -1);
      CellPosition cp5 = new CellPosition(11, 0);
      CellPosition cp6 = new CellPosition(0, 11);
      assertFalse(board.positionIsOnGrid(cp3));
      assertFalse(board.positionIsOnGrid(cp4));
      assertFalse(board.positionIsOnGrid(cp5));
      assertFalse(board.positionIsOnGrid(cp6));
   }

   @Test
   void testRows() {
      assertEquals(11, board.rows());
   }

   @Test
   void testCols() {
      assertEquals(11, board.cols());
   }
}
