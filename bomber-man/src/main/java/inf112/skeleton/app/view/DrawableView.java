package inf112.skeleton.app.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * An interface which specifies methods for drawing the current game state, 
 * as well as disposing of resources.
 */
public interface DrawableView {

    /**
     * Method that draws the game. The game will look different based on what the
     * current gamestate is.
     * 
     * @param batch
     */
    void drawCurrentGameState(SpriteBatch batch);
    
    /** Disposes all textures */
    void dispose();
}
