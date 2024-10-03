package inf112.skeleton.app.model.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;

import inf112.skeleton.app.audioplayer.AudioPlayer;
import inf112.skeleton.app.model.CellPosition;
import inf112.skeleton.app.model.GameBoard;
import inf112.skeleton.app.model.GameModel;
import inf112.skeleton.app.utilities.Constants;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static inf112.skeleton.app.testUtilities.TestHelpMethods.GetMockListener;

public class ExplosionTest {
   private GameModel model;
   private GameBoard board;
   private HeadlessApplicationConfiguration config;
   private ApplicationListener listener;

   @BeforeEach
   void setUp() {
      model = mock(GameModel.class);
      board = mock(GameBoard.class);
      config = new HeadlessApplicationConfiguration();
      listener = GetMockListener(config);
      new HeadlessApplication(listener, config); // Needed for Explosion.startTimer()

      // This simulates a completely empty board, with no boundaries.
      when(board.positionIsOnGrid(any())).thenReturn(true);
      when(board.isWallAt(any(CellPosition.class))).thenReturn(false);
      when(model.isBarrelAt(any(CellPosition.class))).thenReturn(false);
      when(model.isBombAt(any(CellPosition.class))).thenReturn(false);
      when(model.isEnemyAt(any(CellPosition.class))).thenReturn(false);
   }

   @Test
   void RangeAndDirection() {
      // Checks that explosions are added in four directions, with the desired range.

      /**
       * Since the board is empty, it should look like so:
       * -------
       * ---O---
       * ---O---
       * -OOOOO-
       * ---O---
       * ---O---
       * -------
       */

      Explosion exp = new Explosion(model, board, new CellPosition(3, 3), 2);
      ArrayList<CellPosition> impactPositions = exp.impactPositions;
      ArrayList<CellPosition> expectedPositions = new ArrayList<>();

      // Adds explosions to expectedPositions as seen in the illustration above.
      for (int i = 1; i <= 5; i++) {
         expectedPositions.add(new CellPosition(i, 3));
         expectedPositions.add(new CellPosition(3, i));
      }

      // Confirm that only expected positions are in impactPositions.
      confirmExpectedPositions(6, 6, expectedPositions, impactPositions);

      // Check that ending the explosion removes the explosions.
      model.endExplosion(exp); 
      for (CellPosition cp : expectedPositions) {
         assertFalse(model.isCellExploding(cp)); 
      }
   }

   @Test
   void PropagationStoppedByWall() {
      // Checks that explosions are stopped by walls at different positions.

      /**
       * Explosion center at (3,3), with walls added:
       * ---#---
       * ---O---
       * --#O---
       * -#OOOO#
       * ---#---
       * ---#---
       * -------
       */

      when(board.isWallAt(new CellPosition(0, 3))).thenReturn(true);
      when(board.isWallAt(new CellPosition(2, 2))).thenReturn(true);
      when(board.isWallAt(new CellPosition(3, 1))).thenReturn(true);
      when(board.isWallAt(new CellPosition(3, 6))).thenReturn(true);
      when(board.isWallAt(new CellPosition(4, 3))).thenReturn(true);
      when(board.isWallAt(new CellPosition(5, 3))).thenReturn(true);

      Explosion exp = new Explosion(model, board, new CellPosition(3, 3), 2);

      ArrayList<CellPosition> impactPositions = exp.impactPositions;
      ArrayList<CellPosition> expectedPositions = new ArrayList<>();

      // Adds expected positions as seen in the illustration above
      expectedPositions.add(new CellPosition(1, 3));
      expectedPositions.add(new CellPosition(2, 3));
      expectedPositions.add(new CellPosition(3, 3));
      expectedPositions.add(new CellPosition(3, 2));
      expectedPositions.add(new CellPosition(3, 4));
      expectedPositions.add(new CellPosition(3, 5));

      // Confirm that only expected positions are in impactPositions.
      confirmExpectedPositions(6, 6, expectedPositions, impactPositions);
   }

   @Test
   void PropagationStoppedByEnemyAndBarrel() {
      // TEST : Checks that explosions are stopped by enemies and barrels

      /**
       * Explosion center at (3,3), with enemies and barrels added:
       * -------
       * -------
       * ---E---
       * -B-OB--
       * -------
       * ---E---
       * -------
       */

      when(model.isEnemyAt(new CellPosition(2, 3))).thenReturn(true);
      when(model.isEnemyAt(new CellPosition(5, 3))).thenReturn(true);
      when(model.isBarrelAt(new CellPosition(3, 1))).thenReturn(true);
      when(model.isBarrelAt(new CellPosition(3, 4))).thenReturn(true);

      Explosion exp = new Explosion(model, board, new CellPosition(3, 3), 2);

      ArrayList<CellPosition> impactPositions = exp.impactPositions;
      ArrayList<CellPosition> expectedPositions = new ArrayList<>();

      // Adds explosions at expected positions
      expectedPositions.add(new CellPosition(2, 3));
      expectedPositions.add(new CellPosition(3, 1));
      expectedPositions.add(new CellPosition(3, 2));
      expectedPositions.add(new CellPosition(3, 3));
      expectedPositions.add(new CellPosition(3, 4));
      expectedPositions.add(new CellPosition(4, 3));
      expectedPositions.add(new CellPosition(5, 3));

      // Confirm that only expected positions are in impactPositions.
      confirmExpectedPositions(6, 6, expectedPositions, impactPositions);
   }

   @Test
   void EnemiesAndBarrelsAreRemoved() {
      GameModel newModel = new GameModel(mock(AudioPlayer.class));
      newModel.setLevel(Constants.TEST_LEVEL3);
      /*
       * ###########
         #----P--B-#
         #---------#
         #--E-O--E-#       O - Explosion at (3,5)
         #----B----#
         ###########
       */

      Bomb bomb = new Bomb(new CellPosition(3, 5), 2);
      newModel.getActiveBombs().add(bomb);
      newModel.triggerExplosion(bomb);

      ArrayList<Enemy> enemies = newModel.getEnemies();
      ArrayList<Barrel> barrels = newModel.getBarrels();

      // Check that the number of enemies and barrels has been reduced from 2 to 1
      assertEquals(1, enemies.size());
      assertEquals(1, barrels.size());
   }

   /**
    * Loops through a virtual grid of the given size (rows, cols),
    * and checks that only the expected positions are in the impactPositions-list.
    */
   private void confirmExpectedPositions(int rows, int cols, ArrayList<CellPosition> expectedPositions,
         ArrayList<CellPosition> actualPositions) {
      for (int r = 0; r <= rows; r++) {
         for (int c = 0; c <= cols; c++) {
            CellPosition cp = new CellPosition(r, c);
            if (expectedPositions.contains(cp)) {
               assertTrue(actualPositions.contains(cp));
            } else {
               assertFalse(actualPositions.contains(cp));
            }
         }
      }
   }
}
