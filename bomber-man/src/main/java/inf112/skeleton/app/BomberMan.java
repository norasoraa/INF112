package inf112.skeleton.app;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import inf112.skeleton.app.controller.Controller;
import inf112.skeleton.app.audioplayer.AudioPlayer;
import inf112.skeleton.app.controller.BMInputHandler;
import inf112.skeleton.app.controller.BomberKeyboard;
import inf112.skeleton.app.controller.ControllableModel;
import inf112.skeleton.app.model.GameModel;
import inf112.skeleton.app.utilities.Constants;
import inf112.skeleton.app.view.DrawableView;
import inf112.skeleton.app.view.GameView;
import inf112.skeleton.app.view.UpdateableView;
import inf112.skeleton.app.view.ViewableModel;

public class BomberMan implements ApplicationListener {

	private GameModel model;
	private DrawableView view;
	private Controller controller;
	private SpriteBatch batch;
	private AudioPlayer audioPlayer;

	@Override
	public void create() {
		this.audioPlayer = new AudioPlayer();
		this.model = new GameModel(audioPlayer);
		this.view = new GameView((ViewableModel) model);
		BomberKeyboard kbInputs = new BomberKeyboard();
		Gdx.input.setInputProcessor(new BMInputHandler(kbInputs));
		this.controller = new Controller(
			kbInputs, (ControllableModel) model, (UpdateableView) view);
		this.batch = new SpriteBatch();

		Gdx.graphics.setForegroundFPS(Constants.FPS);
		Gdx.graphics.setTitle("BomberMan");
	}

	@Override
	public void dispose() {
		// Called at shutdown

		// Graphics and sound resources aren't managed by Java's garbage collector, so
		// they must generally be disposed of manually when no longer needed. But,
		// any remaining resources are typically cleaned up automatically when the
		// application exits, so these aren't strictly necessary here.
		// (We might need to do something like this when loading a new game level in
		// a large game, for instance, or if the user switches to another application
		// temporarily (e.g., incoming phone call on a phone, or something).
		batch.dispose();
		audioPlayer.dispose();
		view.dispose();
	}

	@Override
	public void render() {
		// Start with a blank screen
		ScreenUtils.clear(Color.BLACK);

		// Drawing wrapped in batch.begin and batch.end.
		batch.begin();
		view.drawCurrentGameState(batch);
		batch.end();

		// Update the model
		controller.modifyModel();
	}

	@Override
	public void resize(int width, int height) {
		// Called whenever the window is resized (including with its original site at
		// startup)
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
