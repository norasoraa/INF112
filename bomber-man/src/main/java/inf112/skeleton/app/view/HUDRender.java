package inf112.skeleton.app.view;

import static inf112.skeleton.app.view.GameView.SCREEN_SCALE;

import java.util.AbstractMap.SimpleEntry;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import inf112.skeleton.app.model.CellPosition;
import inf112.skeleton.app.model.entities.ViewableEntity;

/**
 * Class that contains methods for drawing the HUD (heads-up display) or status
 * bar where players can see their character's vital statistics.
 */
public class HUDRender {

    private ViewableModel model;

    /**
     * Constructs a new HUDRender with the given model. A call to {@link #drawHUD()}
     * on this object will draw the HUD on the board.
     * 
     * @param model the model as a {@link ViewableModel}
     */
    public HUDRender(ViewableModel model) {
        this.model = model;
    }

    /**
     * Draws all player statistics:
     * <p> -Time left
     * <p> -Current level
     * <p> -Health points
     * <p> -Max bombs
     * <p> -PowerUp text
     */
    protected void drawHUD(SpriteBatch batch) {
        drawActiveGameText(batch);
        drawPlayerHearts(batch);
        drawMaxBombs(batch);
        drawPowerUpText(batch);
    }

    private void drawActiveGameText(SpriteBatch batch) {
        // Time left
        FontManager.normalFont.draw(
                batch,
                "TIME LEFT:\n" + Integer.toString(model.getSecondsUntilGameOver()),
                GameView.textArea.x, GameView.textArea.y + GameView.textArea.height + (55 * SCREEN_SCALE));

        // Current level
        FontManager.normalFont.draw(
                batch,
                "\nCURRENT LEVEL:\n" + Integer.toString(model.getCurrentLevel()),
                GameView.textArea.x, GameView.textArea.y + GameView.textArea.height - (45 * SCREEN_SCALE));

        // Player healthpoints
        FontManager.normalFont.draw(
                batch,
                "\nHEALTH POINTS: ",
                GameView.textArea.x, GameView.textArea.y + GameView.textArea.height - (195 * SCREEN_SCALE));

        // Max bombs
        FontManager.normalFont.draw(
                batch,
                "\nMAX BOMBS: ",
                GameView.textArea.x, GameView.textArea.y + GameView.textArea.height - (345 * SCREEN_SCALE));
    }

    /* Draws activated power-up text above the player for a short duration. */
    private void drawPowerUpText(SpriteBatch batch) {
        if (model.recentlyActivatedPowerup()) {
            // Get powerup visuals
            SimpleEntry<ViewableEntity, String> powerUp = model.getPowerupText();
            String text = powerUp.getValue();
            BitmapFont font = FontManager.powerUpFont;

            // Calculate text position
            CellPosition cell = powerUp.getKey().getPosition(); // draw above powerup cell
            float w = FontManager.getTextWidth(font, text);
            float x = cell.col() * GameView.cellSize - 0.1f * w;
            float y = GameView.boardArea.height - (cell.row() * GameView.cellSize) + GameView.cellSize;

            // draw text
            font.draw(batch, text, x, y);
        }
    }

    private void drawPlayerHearts(SpriteBatch batch) {
        for (int i = 0; i < model.getHealthPoints(); i++) {
            float offsetX = (-5) + SCREEN_SCALE + (i * 65);
            batch.draw(TextureManager.extraLifePowerupTexture, GameView.textArea.x + offsetX,
                    GameView.textArea.y + GameView.textArea.height - (325 * SCREEN_SCALE),
                    55 * SCREEN_SCALE, 55 * SCREEN_SCALE);
        }
    }

    private void drawMaxBombs(SpriteBatch batch) {
        for (int i = 0; i < model.getMaxBombsVisuals(); i++) {
            float offsetX = (-13) + SCREEN_SCALE + (i * 60);
            batch.draw(TextureManager.bombPowerupTexture, GameView.textArea.x + offsetX,
                    GameView.textArea.y + GameView.textArea.height - (485 * SCREEN_SCALE),
                    60 * SCREEN_SCALE, 60 * SCREEN_SCALE);
        }
    }

}
