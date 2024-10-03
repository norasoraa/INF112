package inf112.skeleton.app.model.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;

import static inf112.skeleton.app.testUtilities.TestHelpMethods.GetMockListener;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import inf112.skeleton.app.audioplayer.AudioPlayer;
import inf112.skeleton.app.model.CellPosition;
import inf112.skeleton.app.model.Direction;
import inf112.skeleton.app.model.GameBoard;
import inf112.skeleton.app.model.GameModel;
import inf112.skeleton.app.utilities.Constants;

public class EnemyTest {

    private GameBoard board;
    private Enemy enemy;
    private Player player;

    private GameModel model;
    private HeadlessApplicationConfiguration config;
    private ApplicationListener listener;

    @BeforeEach
    void setUp() {
        config = new HeadlessApplicationConfiguration();
        listener = GetMockListener(config);
        new HeadlessApplication(listener, config);

        model = new GameModel(mock(AudioPlayer.class));
        model.setLevel(Constants.TEST_LEVEL1);

        board = mock(GameBoard.class);

        enemy = new Enemy(1, 3, board, model);
        model.getEnemies().add(enemy);

        player = model.getPlayer();
        // player = new Player(model, board, 1, 2);
    }

    @Test
    void testMoveWithNoObstacles() {
        when(board.isWallAt(any())).thenReturn(false);
        // Test that the enemy can move in different directions
        assertTrue(enemy.move(Direction.UP));
        assertTrue(enemy.move(Direction.DOWN));
        assertTrue(enemy.move(Direction.LEFT));
        assertTrue(enemy.move(Direction.RIGHT));
    }

    @Test
    void testInvalidMove() {
        // Test that the enemy cant move through walls
        when(board.isWallAt(any())).thenReturn(true);
        assertFalse(enemy.move(Direction.UP));
        assertFalse(enemy.move(Direction.LEFT));
    }

    @Test
    void testPositionUpdateAfterMove() {
        // Test that the enemy's position is correctly updated after movement
        enemy.move(Direction.LEFT);
        assertEquals(new CellPosition(1, 2), enemy.getPosition());
    }

    @Test
    void testEnemyPlayerCollision() {
        // Test that enemy can move into players position
        // Playerposition: (1,2)
        // Initial enemy position: (1,3)

        enemy.move(Direction.LEFT); 
        assertTrue(player.getPosition().equals(enemy.getPosition()));
    }

}