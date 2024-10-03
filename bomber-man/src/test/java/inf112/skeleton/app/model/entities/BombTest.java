package inf112.skeleton.app.model.entities;

import static inf112.skeleton.app.testUtilities.TestHelpMethods.GetMockListener;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;

import inf112.skeleton.app.audioplayer.AudioPlayer;
import inf112.skeleton.app.model.CellPosition;
import inf112.skeleton.app.model.GameModel;
import inf112.skeleton.app.model.GameState;
import inf112.skeleton.app.utilities.Constants;

public class BombTest {
   private GameModel model;

   @BeforeEach
   void setUp() {
      HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
      ApplicationListener listener = GetMockListener(config);
      new HeadlessApplication(listener, config);
      model = new GameModel(mock(AudioPlayer.class));
      model.setLevel(Constants.TEST_LEVEL1);
   }

   @Test
   void PlacingBombIncreasesActiveBombs() {
      int maxBombs = 3;
      model.setPlayerMaxBombs(maxBombs);
      for (int i = 0; i < maxBombs; i++) {
         model.placeBomb();
      }
      assertEquals(maxBombs, model.getActiveBombs().size());
   }

   @Test
   void DontLayBombsIfEmpty() {
      int maxBombs = 3;
      model.setPlayerMaxBombs(maxBombs);
      for (int i = 0; i < (maxBombs + 1); i++) { // Tries to lay one bomb too many
         model.placeBomb();
      }
      assertEquals(maxBombs, model.getActiveBombs().size());
   }

   @Test
   void BombPlacedOnPlayerPosition() {
      CellPosition playerPos = model.getPlayerVisuals().getPosition();
      model.placeBomb();
      ArrayList<Bomb> bombs = model.getActiveBombs();
      CellPosition bombPos = bombs.get(0).position;
      assertEquals(playerPos, bombPos);
   }

   @Test
   void BombLeadsToExplosion() {
      GameState.currentState = GameState.PLAYING;   // Simulating that the game has begun
      model.placeBomb();
      for (int i = 0; i < Constants.BOMB_DURATION; i ++) {
         model.decreaseSecondsUntilGameEnd();
      }
      // After the designated amount of seconds has passed, we expect an explosion
      assertEquals(1, model.getExplosions().size());
   }
}
