package inf112.skeleton.app.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static inf112.skeleton.app.testUtilities.TestHelpMethods.GetMockListener;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;

import inf112.skeleton.app.audioplayer.AudioPlayer;
import inf112.skeleton.app.model.entities.Bomb;
import inf112.skeleton.app.model.entities.PowerUp;
import inf112.skeleton.app.utilities.Constants;

public class PowerupTest {
   GameModel model = new GameModel(mock(AudioPlayer.class));

   @BeforeAll
   static void setUpBeforeAll() {
      HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
      ApplicationListener listener = GetMockListener(config);
      new HeadlessApplication(listener, config);
   }

   @Test
   /** Tests:
    *    1. Checks that exploding a barrel leads to powerup (when chance of creation is 100%)
    *    2. Checks that the powerup has the right coordinate
    *    3. Checks that the powerup.activate()-method is called when player
    *       moves into the powerup.
    */
   public void PowerupCreationAndActivation() {
      this.model.setLevel(Constants.TEST_LEVEL1);

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

      // 1. Check that exploding a barrel leads to powerup
      // 1.1 First we place a bomb next to barrel at pos (5, 3)
      Bomb bomb = new Bomb(model, new CellPosition(5, 2));
      model.activeBombs.add(bomb);

      // 1.2 Next, we modify the POWERUP_THRESHOLD, so that a powerup will always be created.
      Constants.POWERUP_THRESHOLD = 1.0f;
      
      // 1.3 Trigger the explosion
      model.triggerExplosion(bomb);

      // 1.4 Check that a powerup has been added 
      assertEquals(1, model.powerUps.size());

      // 2. Check that the powerup has the right coordinate
      assertEquals(
         new CellPosition(5, 3), 
         model.powerUps.get(0).getPosition());
      
      // 3. Check that the activate method is called when player moves into it.
      // 3.1 Create a mock for the PowerUp
      PowerUp powerup = mock(PowerUp.class);
      model.powerUps.set(0, powerup);
      when(powerup.getPosition()).thenReturn(new CellPosition(5, 3));

      // 3.2 Move player into powerup
      model.checkAndMovePlayer(Direction.LEFT);
      for (int i = 0; i < 4; i++) {model.checkAndMovePlayer(Direction.DOWN);}
      for (int i = 0; i < 2; i++) {model.checkAndMovePlayer(Direction.RIGHT);}
      
      // 3.3 Check that it has been activated
      verify(powerup).activate();
   }

   @Test
   public void PowerupExplosionRange() {
      // Create an explosion-range powerup
      PowerUp powerUp = new PowerUp(
         new CellPosition(0, 0), 
         Constants.POWERUP_EXPLOSION_RANGE, 
         model.player);
      
      // Save the player's previous explosion range into a variable
      int oldExplosionRange = model.player.getBombRange();

      // Activate the powerup
      powerUp.activate();

      // Check that the player's explosion range has increased
      assertEquals(oldExplosionRange + 1, model.player.getBombRange());
      }
   
   @Test
   public void PowerupMaxBombs() {
      // Create a max bombs powerup
      PowerUp powerUp = new PowerUp(
         new CellPosition(0, 0), 
         Constants.POWERUP_MAX_BOMBS, 
         model.player);
      
      // Save the player's previous max bombs into a variable
      int oldMaxBombs = model.player.getMaxBombs();

      // Activate the powerup
      powerUp.activate();

      // Check that the player's max bombs has increased
      assertEquals(oldMaxBombs + 1, model.player.getMaxBombs());
      }

   @Test
   public void PowerupExtraLife1() {
      // Create an extra life powerup
      PowerUp powerUp = new PowerUp(
         new CellPosition(0, 0), 
         Constants.POWERUP_EXTRA_LIFE, 
         model.player);
      
      // Save the player's previous healthpoints into a variable
      int oldHP = model.player.getHealthPoints();

      // Activate the powerup
      powerUp.activate();
      
      // No extra life will be added, as the player's healthpoints were already at the maximum
      assertEquals(oldHP, model.player.getHealthPoints());
   }

   @Test
   public void PowerupExtraLife2() {
      // Create an extra life powerup
      PowerUp powerUp = new PowerUp(
         new CellPosition(0, 0), 
         Constants.POWERUP_EXTRA_LIFE, 
         model.player);

      // Manually decrease the player's healthpoints by one
      model.player.takeDamage();
      
      // Save the player's previous healthpoints into a variable
      int oldHP = model.player.getHealthPoints();

      // Activate the powerup
      powerUp.activate();

      // Check that the player's healthpoints have been increased by one
      assertEquals(oldHP + 1, model.player.getHealthPoints());
   }
}
