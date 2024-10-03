package inf112.skeleton.app.controller;

import static inf112.skeleton.app.testUtilities.TestHelpMethods.GetMockListener;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;

import inf112.skeleton.app.audioplayer.AudioPlayer;
import inf112.skeleton.app.model.GameModel;
import inf112.skeleton.app.model.GameState;
import inf112.skeleton.app.utilities.Constants;
import inf112.skeleton.app.view.GameView;
import inf112.skeleton.app.view.UpdateableView;

public class ControllerTest {
    GameModel model;
    UpdateableView view;
    Controller controller;
    BomberKeyboard kbInputs;

    @BeforeAll
    static void setUpBeforeAll(){
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        ApplicationListener listener = GetMockListener(config);
        new HeadlessApplication(listener, config);
    }

    @BeforeEach
    void setUp(){
        
        this.model = new GameModel(mock(AudioPlayer.class));
        // Set level where player and enemy can't get to eachother.
        model.setLevel(Constants.TEST_LEVEL5);
        this.kbInputs = new BomberKeyboard();
        this.view = mock(GameView.class);
        this.controller = new Controller(this.kbInputs, model, view);

    }
    

    @Test
    void globalTimerTest(){
        //Check if when timer runs out, that gamestate is changed and timer stops. 
        GameState.currentState = GameState.PLAYING;
        for(int i = 0; i<Constants.SECONDS_UNTIL_GAMEOVER*Constants.FPS; i++){
            controller.modifyModel();
        }
        
        assertEquals(GameState.GAME_OVER, GameState.currentState);
        assertEquals(0, model.getSecondsUntilGameOver());
    }

    @Test
    void dontTickWhenGameStatePauseTest(){
        //Checks if gameState.PAUSE, then the timer does not run.
        GameState.currentState = GameState.PAUSE;
        int gameOverSeconds = model.getSecondsUntilGameOver();
        for(int i = 0; i<Constants.FPS; i++){
            controller.modifyModel();
        }
        assertEquals(gameOverSeconds, model.getSecondsUntilGameOver());

        GameState.currentState = GameState.PLAYING;
        gameOverSeconds = model.getSecondsUntilGameOver();
        for(int i = 0; i<Constants.FPS; i++){
            controller.modifyModel();
        }
        assertNotEquals(gameOverSeconds, model.getSecondsUntilGameOver());
        
    }

    @Test
    void enterIsPressedChangesGameStateTest(){
        GameState.currentState = GameState.START_SCREEN;

		BMInputHandler inputHandler = new BMInputHandler(this.kbInputs);

        inputHandler.keyDown(Input.Keys.ENTER);
        controller.modifyModel();
        assertEquals(GameState.PLAYING, GameState.currentState);
    }

}
