package inf112.skeleton.app.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import inf112.skeleton.app.model.CellPosition;
import inf112.skeleton.app.model.GameBoard;
import inf112.skeleton.app.model.GameState;
import inf112.skeleton.app.utilities.Constants;

/**
 * GameView is the central view class, coordinating with specialized
 * renderers to visualize the entire game state.
 * <p>
 * It implements UpdateableView and DrawableView interfaces to manage and
 * restrict access to game information.
 */
public class GameView implements UpdateableView, DrawableView {
   /* Screen specs */
   public static final int WINDOW_WIDTH = Gdx.graphics.getWidth();
   public static final int WINDOW_HEIGHT = Gdx.graphics.getHeight();
   public static final float SCREEN_SCALE = WINDOW_WIDTH / 1382f;  
   // 1382 is the 'default' window width. If the window width on another
   // computer is bigger / smaller, the SCREEN_SCALE will reflect this.

   /* Static fields to maintain universal view settings */
   protected static float cellSize = Constants.DEFAULT_CELL_SIZE; // Way too big by default. Will be adjusted later.
   protected static float margin; // Margin between the edge of the screen and gameBoard
   protected static Rectangle boardArea; // The area GameBoard should stay within
   protected static Rectangle textArea; // The area active-game-text should stay within
   protected static int animationIndex = 0; // Which animation sprite is currently being displayed

   /* Constants */
   private final int BOARD_HEIGHT = (int) (Gdx.graphics.getHeight() * 0.90f);
   private final int BOARD_WIDTH = BOARD_HEIGHT;

   /* Renders */
   private EntityRender entityRender;
   private HUDRender hudRender;

   /* Limited access to game logic */
   private ViewableModel model;
   private GameBoard board;

   /**
    * Constructs a new GameView object, taking a ViewableModel as an argument.
    * 
    * @param model
    */
   public GameView(ViewableModel model) {
      this.model = model;
      margin = (BOARD_HEIGHT / 15);
      constructScreenRectangles();
      this.entityRender = new EntityRender(model);
      this.hudRender = new HUDRender(model);
      updateBoard();
   }

   @Override
   public void drawCurrentGameState(SpriteBatch batch) {
      switch (GameState.currentState) {
         case START_SCREEN:
            drawStartScreen(batch);
            break;
         case PLAYING:
            drawGame(batch);
            break;
         case LEVEL_FINISHED:
            drawGame(batch);
            drawLevelFinishedOverlay(batch);
            break;
         case GAME_OVER:
            drawGame(batch);
            drawGameOverOverlay(batch);
            break;
         case PAUSE:
            drawGame(batch);
            drawPauseOverlay(batch);
            break;
      }
   }

   @Override
   public void updateBoard() {
      this.board = model.getBoard();
      adjustCellSize();
   }

   /**
    * Call to draw the gameboard, game-text, and all entities.
    * 
    * @param batch, current spriteBatch.
    */
   private void drawGame(SpriteBatch batch) {
      drawGrid(batch);
      entityRender.drawAllEntities(batch);
      hudRender.drawHUD(batch);
   }

   /* Renders the start screen using the given sprite batch. */
   protected void drawStartScreen(SpriteBatch batch) {
      ScreenRender.start(batch);
   }

   /* Renders the game over overlay using the given sprite batch. */
   protected void drawGameOverOverlay(SpriteBatch batch) {
      ScreenRender.gameOver(batch, model);
   }

   /* Renders the level completion overlay using the given sprite batch. */
   protected void drawLevelFinishedOverlay(SpriteBatch batch) {
      ScreenRender.finished(batch, model);
   }

   /* Renders the pause game overlay using the given sprite batch. */
   protected void drawPauseOverlay(SpriteBatch batch) {
      ScreenRender.pause(batch);
   }

   /* Update animations periodically */
   public void updateAnimations() {
      animationIndex = (animationIndex + 1) % TextureManager.ANIMATION_LENGTH;
   }

   /*
    * Draws the game grid based on board contents, positioning each cell within the
    * board area.
    */
   private void drawGrid(SpriteBatch batch) {
      for (int r = 0; r < board.rows(); r++) {
         for (int c = 0; c < board.cols(); c++) {
            char content = board.get(new CellPosition(r, c));

            float x = (boardArea.x + c * cellSize);
            float y = (boardArea.y + boardArea.height - (r + 1) * cellSize);
            if (content == Constants.WALL) {
               batch.draw(TextureManager.wallTexture, x, y, cellSize, cellSize);
            } else {
               batch.draw(TextureManager.tileTexture, x, y, cellSize, cellSize);
            }
         }
      }
   }

   /*
    * Defines and sets up the rectangles for the board and text areas of the
    * screen.
    */
   private void constructScreenRectangles() {
      boardArea = new Rectangle(
            margin, // x coordinate
            margin, // y coordinate
            BOARD_WIDTH, // width
            BOARD_HEIGHT); // height

      textArea = new Rectangle(
            margin * 2 + BOARD_WIDTH, // x coordinate
            margin, // y coordinate
            GameView.WINDOW_WIDTH - (margin * 2 + BOARD_WIDTH),
            (int) (BOARD_HEIGHT * 0.8));
   }

   /*
    * Adjusts the cell size to fit within the board area dimensions, using default
    * size as a baseline.
    */
   private void adjustCellSize() {
      cellSize = Constants.DEFAULT_CELL_SIZE;
      if (board.cols() * cellSize > boardArea.width) {
         cellSize = boardArea.width / board.cols();
      }
      if (board.rows() * cellSize > boardArea.height) {
         cellSize = boardArea.height / board.rows();
      }
   }

   @Override
   public void dispose() {
      FontManager.disposeResources();
      TextureManager.disposeResources();
   }
}
