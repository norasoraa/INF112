package inf112.skeleton.app.view;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import inf112.skeleton.app.utilities.Constants;

/**
 * This class serves as a container for textures used in the game.
 * It also holds numerical constants related to the spritesheet, 
 * like sprite size and animation length.
 * 
 * Upon initialization it loads the necessary images from the resource folder.
 * It uses the spritesheet to construct animation arrays and single images,
 * which can later be accessed statically by the view.
 * 
 * Call the dispose()-method to dispose of all textures when drawing is finished.
*/
public class TextureManager {

    private static final int SPRITE_SIZE = 25;
    protected static final int ANIMATION_LENGTH = 4; // Number of sprites in all animations

    protected static Texture 
        blackTexture, arrowTexture, spaceTexture, enterTexture, spriteSheet;

    protected static TextureRegion 
        barrelTexture, bombPowerupTexture, extraLifePowerupTexture,
        rangePowerupTexture, tileTexture, wallTexture;

    protected static HashMap<Integer, TextureRegion> powerupTextures;

    protected static TextureRegion[][] 
        playerAnimations, ghostAnimations, bombAnimations, explosionsAnimations,
        extraLifePowerupAnimations, bombRangePowerupAnimations, extraBombPowerupAnimations;

    static {
        loadTextures();
    }

    private static void loadTextures() {
        blackTexture = new Texture("Images/black_texture.png");
        arrowTexture = new Texture("Images/ArrowKeys.png");
        spaceTexture = new Texture("Images/SpaceBar.png");
        enterTexture = new Texture("Images/EnterKey.png");

        spriteSheet = new Texture(Gdx.files.internal("sprites/spriteSheet.png"));

        barrelTexture = getTextureRegion(spriteSheet, 11, 2);
        tileTexture = getTextureRegion(spriteSheet, 11, 0);
        wallTexture = getTextureRegion(spriteSheet, 11, 1);
        extraLifePowerupTexture = getTextureRegion(spriteSheet, 8, 0);
        bombPowerupTexture = getTextureRegion(spriteSheet, 9, 0);
        rangePowerupTexture = getTextureRegion(spriteSheet, 10, 0);

        extraLifePowerupAnimations = getAnimationArray(spriteSheet, 8, 1, 4);
        extraBombPowerupAnimations = getAnimationArray(spriteSheet, 9, 1, 4);
        bombRangePowerupAnimations = getAnimationArray(spriteSheet, 10, 1, 4);

        initializePowerupTextures();

        playerAnimations = getAnimationArray(spriteSheet, 0, 4, 4);
        ghostAnimations = getAnimationArray(spriteSheet, 4, 2, 4);
        bombAnimations = getAnimationArray(spriteSheet, 7, 1, 4);
        explosionsAnimations = getAnimationArray(spriteSheet, 6, 1, 4);


    }

    private static void initializePowerupTextures() {
        powerupTextures = new HashMap<>();
        powerupTextures.put(Constants.POWERUP_EXTRA_LIFE, extraLifePowerupAnimations[0][0]); // Temporarily sets first frame                                                                      
        powerupTextures.put(Constants.POWERUP_MAX_BOMBS, extraBombPowerupAnimations[0][0]);
        powerupTextures.put(Constants.POWERUP_EXPLOSION_RANGE, bombRangePowerupAnimations[0][0]);
    }

    /**
     * Returns a 2D-TextureRegion array for use in animations.
     * 
     * @param spriteSheet with animations
     * @param startRow    : which row in the sheet the animation starts at
     * @param nrOfRows    : the amount of rows for the animation
     * @param cols        : the amount of columns in the animation sheet
     * @param spriteSize  : the width and height of the individual sprites
     */
    private static TextureRegion[][] getAnimationArray(Texture spriteSheet, int startRow, int nrOfRows, int cols) {
        TextureRegion[][] spriteArray = new TextureRegion[nrOfRows][cols];
        for (int r = 0; r < nrOfRows; r++) {
            for (int c = 0; c < cols; c++) {
                spriteArray[r][c] = getTextureRegion(
                        spriteSheet, startRow + r, c);
            }
        }
        return spriteArray;
    }

    // Can be used to get a single sprite as TextureRegion from a spriteSheet
    protected static TextureRegion getTextureRegion(Texture spriteSheet, int row, int col) {
        return new TextureRegion(
            spriteSheet,
            col * SPRITE_SIZE, // x
            row * SPRITE_SIZE, // y
            SPRITE_SIZE, SPRITE_SIZE); // width, height
    }

    // Disposes of all textures. 
    // This automatically disposes of all TextureRegions associated with the textures.
    protected static void disposeResources() {
        blackTexture.dispose();
        arrowTexture.dispose();
        spaceTexture.dispose();
        enterTexture.dispose();
        spriteSheet.dispose();
    }
}
