package inf112.skeleton.app.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static inf112.skeleton.app.testUtilities.TestHelpMethods.GetMockListener;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;

import inf112.skeleton.app.audioplayer.AudioPlayer;
import inf112.skeleton.app.utilities.Constants;

public class NextLevelTest {
    
    private HeadlessApplicationConfiguration config;
    private ApplicationListener listener;
    private GameModel model;

    @BeforeEach
    void setUp() {
        config = new HeadlessApplicationConfiguration();
        listener = GetMockListener(config);
        new HeadlessApplication(listener, config);
        model = new GameModel(mock(AudioPlayer.class));
    }

    @Test
    /**
     * For each level, when goToNextLevel() is called, the current level, 
     * which is of type int, should be increased by one, except for the last level,
     * where the current level should be reset to 1
     */
    void testGoToNextLevel() {
        HashMap<Integer, String> levels = Constants.getAllLevels();
        for (int i = 1; i <= levels.size(); i++) {
            this.model.goToNextLevel();
            if (i != levels.size()) { 
                assertEquals(i + 1, model.getCurrentLevel());
            } else {  
                assertEquals(1, model.getCurrentLevel());
            }
        }
    }

    @Test
    /**
     * A new GameBoard should be created after proceeding to the next level
     */
    void testNewGameBoard() {
        GameBoard boardBefore = this.model.getBoard();
        this.model.goToNextLevel();
        GameBoard boardAfter = this.model.getBoard();

        assertTrue(!boardBefore.equals(boardAfter));
    }
}
