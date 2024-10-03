package inf112.skeleton.app.controller;

/**
 * Contains a protected boolean for each key/action which can modify our game.
 * An inputHandler should map the key events to the correct boolean.
 * This inputHandler can be switched out if needed.
 * The model should only utilize these booleans, not the events themselves.
 */
public class BomberKeyboard {

   // Movement and bomb
   protected boolean upIsPressed = false;
   protected boolean downIsPressed = false;
   protected boolean rightIsPressed = false;
   protected boolean leftIsPressed = false;
   protected boolean bombIsPressed = false;
   protected boolean enterIsPressed = false;

   protected void resetEnter() {
      enterIsPressed = false;
   }
}
