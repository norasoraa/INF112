package inf112.skeleton.app.controller;

import com.badlogic.gdx.InputProcessor;


import com.badlogic.gdx.Input;

/** Maps the events received from the applicationListener, to concrete booleans which can be
 * used to modify the game.
 * This InputHandler can be switched out if a different system is desired.
 */
public class BMInputHandler implements InputProcessor {
   private BomberKeyboard kbInputs;
   
   public BMInputHandler(BomberKeyboard kbInputs) {
      this.kbInputs = kbInputs;
   }

   @Override
   public boolean keyDown(int keycode) {
      // Holding down a key only results in a single event
      if (keycode == (Input.Keys.DOWN)) {
         kbInputs.downIsPressed = true;
      }
      else if (keycode == (Input.Keys.UP)) {
         kbInputs.upIsPressed = true;
      }
      else if (keycode == (Input.Keys.LEFT)) {
         kbInputs.leftIsPressed = true;
      }
      else if (keycode == (Input.Keys.RIGHT)) {
         kbInputs.rightIsPressed = true;
      }
      else if (keycode == (Input.Keys.SPACE)) {
         kbInputs.bombIsPressed = true;
      }
      if (keycode == (Input.Keys.ENTER)) {
         kbInputs.enterIsPressed = true;
      }
      return false;
   }

   @Override
   public boolean keyUp(int keycode) {
      if (keycode == (Input.Keys.DOWN)) {
         kbInputs.downIsPressed = false;
      }
      else if (keycode == (Input.Keys.UP)) {
         kbInputs.upIsPressed = false;
      }
      else if (keycode == (Input.Keys.LEFT)) {
         kbInputs.leftIsPressed = false;
      }
      else if (keycode == (Input.Keys.RIGHT)) {
         kbInputs.rightIsPressed = false;
      }
      else if (keycode == (Input.Keys.SPACE)) {
         kbInputs.bombIsPressed = false;
      }
      return true; 
   }

   @Override
   public boolean keyTyped(char character) {return false;}

   @Override
   public boolean touchDown(int screenX, int screenY, int pointer, int button) {return false;}

   @Override
   public boolean touchUp(int screenX, int screenY, int pointer, int button) {return false;}

   @Override
   public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {return false;}

   @Override
   public boolean touchDragged(int screenX, int screenY, int pointer) {return false;}

   @Override
   public boolean mouseMoved(int screenX, int screenY) {return false;}

   @Override
   public boolean scrolled(float amountX, float amountY) {return false;}
   
}
