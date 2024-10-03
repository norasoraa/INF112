package inf112.skeleton.app.utilities;

import java.util.HashMap;
import java.awt.Dimension;
import java.awt.Toolkit;

public class Constants {
   // Cellsize
   public static final int DEFAULT_CELL_SIZE = 150;
   
   // Time
   public static final int FPS = 60;
   public static final int SECONDS_UNTIL_GAMEOVER = 60;
   
   // Level chars
   public static final char WALL = '#';
   public static final char EMPTY = '-';
   public static final char PLAYER = 'P';
   public static final char ENEMY = 'E';
   public static final char BARREL = 'B';

   // Bombs and explosions
   public static final int BOMB_DURATION = 3; // n seconds.
   public static final int EXPLOSION_DURATION = 3;
   public static final int DEFAULT_EXPLOSION_RANGE = 1; // n cells in each direction
   public static final int DEFAULT_MAX_BOMBS = 2; // max bombs that can be placed simultaniously

   // Player
   public static final int PLAYER_DEFAULT_HEALTH = 3;

   // Test levels
   public static final String TEST_LEVEL1 = "testLevels/testLevel.txt";
   public static final String TEST_LEVEL2 = "testLevels/testLevel2.txt";
   public static final String TEST_LEVEL3 = "testLevels/testLevel3.txt";
   public static final String TEST_LEVEL4 = "testLevels/testLevel4.txt";
   public static final String TEST_LEVEL5 = "testLevels/testLevel5.txt";
   public static final String EMPTY_LEVEL = "testLevels/empty_level.txt";
   public static final String BIG_LEVEL = "testLevels/big_level.txt";

   // Actual levels for use in game
   public static final String LEVEL1 = "levels/jostein1.txt";
   public static final String LEVEL2 = "levels/anders1.txt";
   public static final String LEVEL3 = "levels/aida1.txt";

   // Sound file constants.
   public static final String SFX_HURT = "hurt.wav";
   public static final String SFX_EXPLOSION = "SmallExplosion3.5.wav";
   public static final String SFX_SUCCESS = "Success.wav";
   public static final String MUSIC_STRING  = "AudioFiles/Doctor_DreamChip.mp3";

   // Powerups
   public static final int POWERUP_EXPLOSION_RANGE = 0;
   public static final int POWERUP_MAX_BOMBS = 1;
   public static final int POWERUP_EXTRA_LIFE = 2;
   public static float POWERUP_THRESHOLD = 0.2f;  // Is not final to allow for modification in testing
   public static final int POWERUP_TEXT_DURATION = 2;
   
   public static final int[] ALL_POWERUP_TYPES = {
      POWERUP_EXPLOSION_RANGE,
      POWERUP_MAX_BOMBS,
      POWERUP_EXTRA_LIFE
   };

   public static HashMap<Integer, String> POWERUP_TEXT = new HashMap<>();

   static {
      POWERUP_TEXT.put(POWERUP_EXPLOSION_RANGE, "Explosion Range increased!");
      POWERUP_TEXT.put(POWERUP_MAX_BOMBS, "Max Bombs increased!");
      POWERUP_TEXT.put(POWERUP_EXTRA_LIFE, "Extra Life added!");
   }

   // Levels
   private static final HashMap<Integer, String> levels = new HashMap<>();

   static{
      levels.put(1,LEVEL1);
      levels.put(2,LEVEL2);
      levels.put(3,LEVEL3);
   }

   // Get all levels
   public static HashMap<Integer, String> getAllLevels() {
      return levels;
   }
}
