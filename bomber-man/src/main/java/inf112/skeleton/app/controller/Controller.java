package inf112.skeleton.app.controller;

import inf112.skeleton.app.model.Direction;
import inf112.skeleton.app.model.GameState;
import inf112.skeleton.app.utilities.Constants;
import inf112.skeleton.app.view.UpdateableView;

/** The purpose of this class is to modify the BomberMan-model.
 * It does this by calling the methods available through the 
 * ControllableModel-interface in response to keyboard inputs
 * and custom timers.
 * 
 * All modification is done in the modifyModel()-method, 
 * which is called from the main render-loop in the libGDX-application.
 * 
 * Custom timers specify:
 *    -how often the player can move
 *    -how often enemies can move
 *    -how often the player can lay bombs
 *    -how long the player stays invincible after getting hit.
 *    -how long between each animation update
 * 
 * The controller is also responsible for ticking down the seconds until
 * the game is over.
 */
public class Controller {
   private BomberKeyboard kbInputs;
   private ControllableModel model;
   private UpdateableView view;

   // Custom timer for player movement
   private int playerSpeed = 10; 
   private int playerMoveTick = 0;

   // Custom timer for player invincibility frames
   private int invincibilityFrames = Constants.FPS;
   private int invincibilityTick = 0;

   // Custom timer for placing bomb
   private int placeBombSpeed = 10;
   private int placeBombTick = 0;

   // Custom timer for enemy movement
   private int enemyMoveSpeed = 30; 
   private int enemyMoveTick = 0;

   // Custom timer for updating animations
   private int animationSpeed = 7; 
   private int animationTick = 0;
   
   // Custom timer to help with ticking down seconds until game over.
   private int gameoverTick = 0; 

   /** Creates a new Controller-object
    * @param kbInputs
    * @param model
    * @param view : a view-object which must contain a single updateAnimations-method.
    */
   public Controller(BomberKeyboard kbInputs, ControllableModel model, UpdateableView view) {
      this.kbInputs = kbInputs;
      this.model = model;
      this.view = view;
   }

   /** 
    * This method should be called from the main render-loop at
    * a specified time-interval, e.g. 60 FPS.
    * It modifies the model by calling the methods available through the 
    * ControllableModel-interface in response to keyboard inputs
    * and custom timers. 
    * See javadoc for the Controller-class for a more general explanation.
   */
   public void modifyModel() {
      handleEnterKey();

      if (GameState.currentState == GameState.PLAYING) {
         handlePlayerMovement();
         handleBombPlacement(); 
         tickDownGlobalTimer();
         checkPlayerDamage();
         updateEnemyPositions();
         updateAnimationTick();

      // only has access to update the animation and press ENTER
      } else if (GameState.currentState == GameState.START_SCREEN) {
         updateAnimationTick();
         
      // should only be able to press ENTER
      } else {
         return;
      }
   }

   /** Ticks down the time until the animations should be updated */
   private void updateAnimationTick() {
      this.animationTick++;
      if (this.animationTick >= animationSpeed) {
         this.animationTick = 0;
         this.view.updateAnimations();
      }
   }

   /** Ticks down the time until the enemies can move. If the timer is 0,
    * it moves the enemies and starts the custom timer anew.
    */
   private void updateEnemyPositions() {
      updateEnemyMoveTick();
      if (enemyMoveTick == 0) {
         model.moveEnemies();
         enemyMoveTick = enemyMoveSpeed;
      }
   }

   private void updateEnemyMoveTick() {
      if (enemyMoveTick > 0) {
         enemyMoveTick--;
      }
   }

   /**  
    * Is responsible for calling model.decreaseSecondsUntilGameEnd() 
    * at the correct time interval, which is exactly once per second 
    * (i.e the same interval as the FPS).
    * If secondsUntilGameEnd == 0, the method does nothing.
    */
   private void tickDownGlobalTimer() {
      if (model.getSecondsUntilGameOver() > 0) {
         gameoverTick++;
         if (gameoverTick == Constants.FPS) {
            model.decreaseSecondsUntilGameEnd();
            gameoverTick = 0;
         }
      }
   }

   /** First it ticks down the timer until player can move.
    * If the timer is 0, it can move the player in response to keyboard inputs.
    * Then it resets the custom timer.
    */
   private void handlePlayerMovement() {
      updatePlayerMoveTick();
      if (playerMoveTick == 0) {
         model.setPlayerMoving(false);
         if (kbInputs.upIsPressed) {
            movePlayer(Direction.UP);
         } else if (kbInputs.downIsPressed) {
            movePlayer(Direction.DOWN);
         } else if (kbInputs.rightIsPressed) {
            movePlayer(Direction.RIGHT);
         } else if (kbInputs.leftIsPressed) {
            movePlayer(Direction.LEFT);
         }
      }
   }

   private void movePlayer(Direction dir) {
      model.checkAndMovePlayer(dir);
      model.setPlayerMoving(true);
      playerMoveTick = playerSpeed;
   }

   private void updatePlayerMoveTick() {
      if (playerMoveTick > 0) {
         playerMoveTick--;
      }
   }

   /** Ticks down the time until the player can lay bombs. If the timer is 0,
    * it can lay a bomb in response to keyboard inputs.
    * Then it resets the custom timer.
    */
   private void handleBombPlacement() {
      updatePlaceBombTick();
      if (placeBombTick == 0) {
         if (kbInputs.bombIsPressed) {
            model.placeBomb();
            placeBombTick = placeBombSpeed;
         }
      }
   }

   private void updatePlaceBombTick() {
      if (placeBombTick > 0) {
         placeBombTick--;
      }
   }

   /** It checks if the player is currently invincible due to previous damage.
    * If yes, it ticks down the custom timer and updates 
    * the playerHurtTick in the model.
    * If no, it checks if the player should take damage. If the player
    * should take damage, it restarts the timer and takes damage from the player.
    */
   private void checkPlayerDamage() {
      updateInvincibilityTick();
      if (invincibilityTick == 0 && model.isPlayerHurt()) {
         invincibilityTick = invincibilityFrames;
         model.takePlayerDamage();
      }
      model.setPlayerHurtTick(invincibilityTick);
   }

   private void updateInvincibilityTick() {
      if (invincibilityTick > 0) {
         invincibilityTick--;
      } 
   }

   /**
    * The enter key is used to transition between states, and thus pressing
    * it has different effects depending on state.
    * At the end of the method the enter key is automatically reset,
    * ensuring that it's not registered as pressed for more than a single frame.
    */
   private void handleEnterKey() {
      if (kbInputs.enterIsPressed) {
         
         switch (GameState.currentState) {
            case LEVEL_FINISHED:
               model.goToNextLevel();
               view.updateBoard();
               this.resetTimers();
               break;

            case START_SCREEN:
               GameState.currentState = GameState.PLAYING;
               break;

            case PLAYING:
               GameState.currentState = GameState.PAUSE;
               break;

            case PAUSE:
               GameState.currentState = GameState.PLAYING;
               break;

            case GAME_OVER:
               model.resetLevel();
               this.resetTimers();
               break;

            default:
               break;
         }
         kbInputs.resetEnter();
      }
   }

   /** This method resets all custom timers. 
    * Should be called whenever a level is reset. 
    */
   private void resetTimers() {
      this.enemyMoveTick = 0;
      this.placeBombTick = 0;
      this.invincibilityTick = 0;
      this.gameoverTick = 0;
      this.animationTick = 0;
   }
}
