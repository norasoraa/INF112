package inf112.skeleton.app.model;

/** 
 * Enum that represents the various states of the game. 
 * Use the {@link #currentState} variable to get the current state of the game.
 */
public enum GameState {
   START_SCREEN,
   PLAYING,
   PAUSE,
   GAME_OVER,
   LEVEL_FINISHED;

   public static GameState currentState = GameState.START_SCREEN;
}



