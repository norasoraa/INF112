package inf112.skeleton.app.view;

import static inf112.skeleton.app.view.GameView.SCREEN_SCALE;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import inf112.skeleton.app.utilities.Constants;

/** This class renders the start screen of the game, as well as screen overlays
 * for the different game states.
 * 
 * Each overlay can be drawn on top of the rest of the game.
 * There is an overlay for each of the following states:
 * 	- Pause
 * 	- Level Finished
 * 	- Game Over
 */
public class ScreenRender {

	/** Draws the pause overlay */
	protected static void pause(SpriteBatch batch) {
		drawTransparentBackground(batch);
		FontManager.drawCenteredText(
			batch, FontManager.headerFont, 
			"Pause", 
			GameView.WINDOW_HEIGHT * 0.6f);
		FontManager.drawCenteredText(
			batch, FontManager.normalFont, 
			"Press ENTER to resume",
			GameView.WINDOW_HEIGHT * 0.5f);

	}

	/** Draws the 'Level Finished'-overlay */
	protected static void finished(SpriteBatch batch, ViewableModel model) {
		drawTransparentBackground(batch);
		FontManager.drawCenteredText(
			batch, FontManager.headerFont, 
			"Level Finished",
			GameView.WINDOW_HEIGHT * 0.6f);

		if (model.getCurrentLevel() != Constants.getAllLevels().size()) {
			FontManager.drawCenteredText(
				batch, FontManager.normalFont, 
				"Press ENTER to continue to next level", 
				GameView.WINDOW_HEIGHT * 0.5f);
		} else {
			FontManager.drawCenteredText(
				batch, FontManager.normalFont, 
				"You finished all levels!", 
				GameView.WINDOW_HEIGHT * 0.5f);
			FontManager.drawCenteredText(
				batch, FontManager.normalFont, 
				"Press ENTER to start over", 
				GameView.WINDOW_HEIGHT * 0.45f);
		}

	}

	/** Draws the 'Game Over'-overlay */
	protected static void gameOver(SpriteBatch batch, ViewableModel model) {
		drawTransparentBackground(batch);
		FontManager.drawCenteredText(
			batch, FontManager.headerFont, 
			"Game Over",
			GameView.WINDOW_HEIGHT * 0.6f);
		FontManager.drawCenteredText(
			batch, FontManager.normalFont, 
			"Press ENTER to restart",
			GameView.WINDOW_HEIGHT * 0.4f);

		String gameOverMessage = "";
		if (model.getSecondsUntilGameOver() == 0 && model.getHealthPoints() == 0) {
			gameOverMessage = "Explosive combination! Your health points and time both hit zero!";
		} 
		else if (model.getHealthPoints() == 0) {
			gameOverMessage = "You ran out of health points! Ghosts and explosions sealed your fate!";
		} 
		else {
			gameOverMessage = "Tick-tock, time's up!";
		}

		FontManager.drawCenteredText(
			batch, FontManager.normalFont, gameOverMessage, GameView.WINDOW_HEIGHT * 0.5f);

	}

	/** Draws the start screen */
	protected static void start(SpriteBatch batch) {
		startImages(batch);
		startText(batch);
	}

	private static void startImages(SpriteBatch batch) {
		// Player and ghost images
		batch.draw(
			TextureManager.playerAnimations[0][GameView.animationIndex],
			GameView.WINDOW_WIDTH / (float) 2.6, GameView.WINDOW_HEIGHT / (float) 1.64,
			90 * SCREEN_SCALE, 90 * SCREEN_SCALE);
		batch.draw(
			TextureManager.ghostAnimations[0][GameView.animationIndex],
			GameView.WINDOW_WIDTH / (float) 1.78, GameView.WINDOW_HEIGHT / (float) 1.66,
			90 * SCREEN_SCALE, 90 * SCREEN_SCALE);

		// Key images
		batch.draw(
			TextureManager.arrowTexture, GameView.WINDOW_WIDTH / (float) 4.5,
			GameView.WINDOW_HEIGHT / (float) 3.4,
			150 * SCREEN_SCALE, 97 * SCREEN_SCALE);
		batch.draw(
			TextureManager.spaceTexture,
			GameView.WINDOW_WIDTH / 2 - (TextureManager.spaceTexture.getWidth() * (float) 2.7) / 2,
			GameView.WINDOW_HEIGHT / (float) 3.4, 140 * SCREEN_SCALE, 46 * SCREEN_SCALE);
		batch.draw(
			TextureManager.enterTexture, GameView.WINDOW_WIDTH / (float) 1.41,
			GameView.WINDOW_HEIGHT / (float) 3.58,
			95 * SCREEN_SCALE, 95 * SCREEN_SCALE);
	}

	private static void startText(SpriteBatch batch) {
		FontManager.drawCenteredText(
			batch, FontManager.headerFont,
			"Bomberman", 
			GameView.WINDOW_HEIGHT / (float) 1.17);
		FontManager.drawCenteredText(
			batch, FontManager.normalFont,
			"VS", 
			GameView.WINDOW_HEIGHT / (float) 1.48);
		FontManager.drawCenteredText(
			batch, FontManager.startScreenFont,
			"Use bombs wisely to eliminate the ghosts \nwithin the time limit to achieve success!",
			GameView.WINDOW_HEIGHT / (float) 1.78);
		FontManager.drawCenteredText(
			batch, FontManager.startScreenFont,
			"Place bomb", 
			GameView.WINDOW_HEIGHT / (float) 3.78);
		FontManager.drawCenteredText(
			batch, FontManager.normalFont,
			"Press ENTER to start", 
			GameView.WINDOW_HEIGHT / (float) 7.7);

		FontManager.startScreenFont.draw(
			batch, "Move the player", GameView.WINDOW_WIDTH / (float) 4.8,
			GameView.WINDOW_HEIGHT / (float) 3.75);

		FontManager.startScreenFont.draw(
			batch, "Pause and resume; \nproceed to next level",
			GameView.WINDOW_WIDTH / (float) 1.53,
			GameView.WINDOW_HEIGHT / (float) 3.75);
	}

	/** Draws a transparent black background on top of whatever is beneath. */
	private static void drawTransparentBackground(SpriteBatch batch) {
		batch.setColor(new Color(0, 0, 0, 0.5f));
		batch.draw(
			TextureManager.blackTexture, 
			0, 0, 
			GameView.WINDOW_WIDTH, GameView.WINDOW_HEIGHT);
		batch.setColor(Color.WHITE);
	}

}
