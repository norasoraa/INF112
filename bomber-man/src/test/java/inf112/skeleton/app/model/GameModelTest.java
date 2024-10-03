package inf112.skeleton.app.model;

import static inf112.skeleton.app.testUtilities.TestHelpMethods.GetMockListener;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;

import inf112.skeleton.app.audioplayer.AudioPlayer;
import inf112.skeleton.app.model.entities.Bomb;
import inf112.skeleton.app.utilities.Constants;

public class GameModelTest {

    private GameModel model;

    @BeforeEach
    void setup() {
        model = new GameModel(mock(AudioPlayer.class));
        try {
            model.setLevel(Constants.EMPTY_LEVEL);
        } catch (IllegalArgumentException e) {
            // Catches the 'player-not-found-exception'
        }
        
        model = Mockito.spy(model);
    }

    @BeforeAll
    static void setUpBeforeAll() {
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        ApplicationListener listener = GetMockListener(config);
        new HeadlessApplication(listener, config);
    }

    /*
     * EMPTY_LEVEL:
     * 
     * ###########
     * #---------#
     * #----o----#
     * #---------#
     * #---------#
     * ###########
     */

    @Test
    void isEmptyCellsExplodingTest() {
        try {
            model.setLevel(Constants.EMPTY_LEVEL);
        } catch (IllegalArgumentException e) {
            // Catches the 'player-not-found-exception'
        }

        // Create explosion
        CellPosition source = new CellPosition(2, 5);
        Bomb bomb = new Bomb(model, source);
        model.activeBombs.add(bomb);
        model.triggerExplosion(bomb);

        // test if cell is exploding
        CellPosition testPos = new CellPosition(2, 5);
        assertTrue(model.isCellExploding(source), "Cell should be exploding but is not.");
        assertTrue(model.isCellExploding(testPos), "Cell should be exploding but is not.");
    }

    /*
     * TEST_LEVEL2:
     * 
     * ###########
     * #------P--#
     * #---------#
     * #-EE------#
     * #--BB--B--#
     * ###########
     */

    @Test
    void isWallExplodingTest() {
        model.setLevel(Constants.TEST_LEVEL2);
        CellPosition source = new CellPosition(2, 1);
        Bomb bomb = new Bomb(model, source);
        model.activeBombs.add(bomb); // activate
        model.triggerExplosion(bomb);

        assertFalse(model.isCellExploding(new CellPosition(2, 0)), "Wall should not be exploding.");
    }

    @Test
    void isEnemyExplodingTest() {
        model.setLevel(Constants.TEST_LEVEL2);
        CellPosition source = new CellPosition(2, 2);
        Bomb bomb = new Bomb(model, source);
        model.activeBombs.add(bomb); // activate
        model.triggerExplosion(bomb);

        assertTrue(model.isCellExploding(new CellPosition(3, 2)), "Enemy should be exploding.");
    }

    @Test
    void isBarrelExplodingTest() {
        model.setLevel(Constants.TEST_LEVEL2);
        CellPosition source = new CellPosition(3, 8);
        Bomb bomb = new Bomb(model, source);
        model.activeBombs.add(bomb); // activate
        model.triggerExplosion(bomb);

        assertTrue(model.isCellExploding(new CellPosition(3, 8)), "Barrel should be exploding.");
    }

    @Test
    void isPlayerExplodingTest() {
        model.setLevel(Constants.TEST_LEVEL2);
        CellPosition source = new CellPosition(2, 8);
        Bomb bomb = new Bomb(model, source);
        model.activeBombs.add(bomb); // activate
        model.triggerExplosion(bomb);

        assertTrue(model.isCellExploding(new CellPosition(1, 8)), "Player should be exploding.");
    }


    @Test
    void explosionChainReactionTest() {
        // Checks that triggering a bomb next to another bomb triggers the other
        // bomb to explode as well.
        model.setLevel(Constants.TEST_LEVEL2);
        /*
         * ###########
         * #------P--#
         * #-----OO--# O = Bombs at (2, 6), (2, 7), (3, 7)
         * #-EE---O--#
         * #--BB--B--#
         * ###########
         */

        // Adds bombs according to the chart above
        Bomb bomb1 = new Bomb(model, new CellPosition(2, 6));
        Bomb bomb2 = new Bomb(model, new CellPosition(2, 7));
        Bomb bomb3 = new Bomb(model, new CellPosition(3, 7));
        model.activeBombs.add(bomb1); 
        model.activeBombs.add(bomb2); 
        model.activeBombs.add(bomb3);

        // Trigger only the leftmost bomb
        model.triggerExplosion(bomb1);

        // Assume all 3 bombs have exploded in a chain reaction
        assertEquals(3, model.getExplosions().size());
    }

    @Test
    void levelFinishedTest() {
        model.setLevel(Constants.TEST_LEVEL1);
        ArrayList<Bomb> bombsNearEnemies = new ArrayList<>();

        bombsNearEnemies.add(new Bomb(model, new CellPosition(3, 6)));
        bombsNearEnemies.add(new Bomb(model, new CellPosition(7, 6)));
        bombsNearEnemies.add(new Bomb(model, new CellPosition(9, 5)));

        for (Bomb bomb : bombsNearEnemies) {
            model.activeBombs.add(bomb); // activate
            model.triggerExplosion(bomb);
        }

        // Check that the gamestate is LEVEL_FINISHED when all enemies are dead
        assertEquals(GameState.LEVEL_FINISHED, GameState.currentState);
    }

    @Test
    void testGameOverWhenTimerExpires() {
        model.setLevel(Constants.TEST_LEVEL1);
        GameState.currentState = GameState.PLAYING;  // Simulates that the game has started.

        for (int i = 0; i < Constants.SECONDS_UNTIL_GAMEOVER; i++) {
            model.decreaseSecondsUntilGameEnd();
        }

        assertEquals(GameState.GAME_OVER, GameState.currentState);
    }

}
