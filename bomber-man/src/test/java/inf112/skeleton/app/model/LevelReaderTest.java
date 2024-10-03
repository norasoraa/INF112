package inf112.skeleton.app.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static inf112.skeleton.app.testUtilities.TestHelpMethods.GetMockListener;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;

import inf112.skeleton.app.utilities.Constants;

public class LevelReaderTest {
   LevelReader levelReader = new LevelReader();
   GameBoard board;

   /** We make a mock Gdx-application, so that the ObjectFactory can utilize the Gdx-library. */
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
   void testGetBarrels() {
      ArrayList<CellPosition> barrels = levelReader.getBarrelsPos();
      assertEquals(6, barrels.size());

      CellPosition barrel1 = barrels.get(0);  // Checks first barrel
      assertEquals(5, barrel1.row());
      assertEquals(3, barrel1.col());

      CellPosition barrel2 = barrels.get(5);  // Checks last barrel
      assertEquals(7, barrel2.row());
      assertEquals(4, barrel2.col());
   }

   @Test
   void testGetEnemies() {
      ArrayList<CellPosition> enemies = levelReader.getEnemiesPos();
      assertEquals(3, enemies.size());

      CellPosition enemy1 = enemies.get(0);  // Checks first enemy
      assertEquals(3, enemy1.row());
      assertEquals(7, enemy1.col());

      CellPosition enemy2 = enemies.get(2);  // Checks last enemy
      assertEquals(9, enemy2.row());
      assertEquals(6, enemy2.col());
   }

   @Test
   void testGetPlayer() {
      CellPosition p;
      p = levelReader.getPlayerPos();

      assertTrue(p != null);
      assertEquals(1, p.row());
      assertEquals(2, p.col());
   }

   @Test
   void testGetBoard() {
      GameBoard board = levelReader.getBoard();

      assertTrue(board != null);
      // See GameBoard-test
   }

   @Test
   void testSetNewLevel() {
      levelReader.setNewLevel(Constants.TEST_LEVEL2);
      /*
      ###########
      #------P--#
      #---------#
      #-EE------#
      #--BB--B--#
      ###########
       */

      CellPosition p = levelReader.getPlayerPos();
      assertTrue(p != null);
      assertEquals(1, p.row());
      assertEquals(7, p.col());

      ArrayList<CellPosition> barrels = levelReader.getBarrelsPos();
      assertEquals(3, barrels.size());

      ArrayList<CellPosition> enemies = levelReader.getEnemiesPos();
      assertEquals(2, enemies.size());
   }
}
