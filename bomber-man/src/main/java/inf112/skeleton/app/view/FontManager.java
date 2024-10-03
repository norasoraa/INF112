package inf112.skeleton.app.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import inf112.skeleton.app.utilities.Constants;

/**
 * FontManager is responsible for loading, creating and handling fonts.
 * Also is responsible for drawing of centered text in game.
 */
public class FontManager {

    protected static FreeTypeFontGenerator generator;
    protected static FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    protected static BitmapFont headerFont;
    protected static BitmapFont normalFont;
    protected static BitmapFont startScreenFont;
    protected static BitmapFont powerUpFont;

    static {
        loadFonts(); // Fonts are loaded once when the class is loaded
    }

    private static void loadFonts() {
        generator = new FreeTypeFontGenerator(Gdx.files.internal("pixel_font.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        headerFont = createFont((int) (75 * GameView.SCREEN_SCALE), Color.WHITE);
        normalFont = createFont((int) (30 * GameView.SCREEN_SCALE), Color.WHITE);
        startScreenFont = createFont((int) (19 * GameView.SCREEN_SCALE), Color.WHITE);
        powerUpFont = createFont((int) (20 * GameView.SCREEN_SCALE), Color.LIGHT_GRAY);
    }

    /**
     * Create BitmapFont object.
     * @param size define size in pixels.
     * @param color color of font.
     * @return BitmapFont object.
     */
    protected static BitmapFont createFont(int size, Color color) {
        parameter.size = size;
        BitmapFont font = generator.generateFont(parameter);
        font.setColor(color);
        return font;
    }

    /**
     * Draws centered text in regards to the x-axis in the game window.
     * @param batch spritebatch to draw in.
     * @param font BitmapFont to use,
     * @param text text to display
     * @param yPos y position to draw the text.
     */
    protected static void drawCenteredText(SpriteBatch batch, BitmapFont font, String text, float yPos) {
        float textWidth = getTextWidth(font, text);
        float textXpos = GameView.WINDOW_WIDTH / 2 - textWidth / 2;
        font.draw(batch, text, textXpos, yPos);
    }

    /**
     * Calculates the width of given text with given font.
     * @param font bitmapFont to use.
     * @param str string to measure.
     * @return float representing width of text.
     */
    protected static float getTextWidth(BitmapFont font, String str) {
        GlyphLayout layout = new GlyphLayout(font, str);
        float textLength = layout.width;
        return textLength;
    }

    /**
     * Method to dispose all fonts
     */
    protected static void disposeResources() {
        if (headerFont != null) headerFont.dispose();
        if (normalFont != null) normalFont.dispose();
        if (startScreenFont != null) startScreenFont.dispose();
        if (powerUpFont != null) powerUpFont.dispose();
    }
}
