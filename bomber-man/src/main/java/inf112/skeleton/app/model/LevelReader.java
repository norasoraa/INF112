package inf112.skeleton.app.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import inf112.skeleton.app.utilities.Constants;

/**
 * Can read a text-file, produce the GameBoard and save Player-, Enemy-, and
 * Barrel-positions from the file.
 * If a new level is to be loaded, then call the {@link #setNewLevel}-method
 * with the path to a text-file. Then call the {@link #getPlayerPos}, {@link #getEnemiesPos},
 * {@link #getBarrelsPos} and {@link #getBoard}-methods to extract the entities.
 * <p>
 * All existing boards and all new boards that are created must have walls
 * around the edge and all rows in the boards must have the same number of columns.
 */
public class LevelReader {

   private GameBoard board;
   private boolean playerFound;
   private CellPosition playerPos;
   private ArrayList<CellPosition> barrels;
   private ArrayList<CellPosition> enemies;

   /**
    * Should be called whenever you want to load a new level.
    * This method deletes the previous player-, barrel- and enemy-objects, and
    * makes new ones based on the given text-file.
    * 
    * @param path The path to the text-file that represents the new level
    * @throws IllegalArgumentException if the path to the file is null
    * @throws IllegalArgumentException if a player is not found in the sheet.
    */
   public void setNewLevel(String path) {
      if (path == null) {
         throw new IllegalArgumentException("The string of the path to the file to be read cannot be null!");
      }
      this.playerFound = false;
      this.barrels = new ArrayList<>();
      this.enemies = new ArrayList<>();
      String fileText = readFromFile(path);
      String[] levelTxtArray = fileText.split("\n");
      List<String> formattedLevel = formatLevel(levelTxtArray);
      this.board = makeGrid(formattedLevel);
   }

   private String readFromFile(String path) {
      FileHandle filePath = Gdx.files.internal(path);
      String text = filePath.readString();
      return text;
   }


   /**
    * In some operative systems there seems to be an extra character at the end of
    * the strings in the textfile that is not meant to be a part of the board.
    * Therefore, when reading the contents of the textfile, the boards dimension
    * may not be correct. This method checks if an extra character exist at the end of 
    * the strings. It corrects the length of the strings if it exists, 
    * before it checks if all rows have an equal number of columns and 
    *  that the last character in each row is now a wall.
    *
    * @throws IllegalArgumentException if not all the rows in the text file have
    *                                  the same number of columns or the last
    *                                  character in the rows is not a wall
    */
   private List<String> formatLevel(String[] txtArray) {
      // 1. Removes potential extra char at the end of each string
      for (int i = 0; i < txtArray.length; i++) {
         String str = txtArray[i];
         if (!lastCharInRowIsWall(str)) {
            txtArray[i] = str.substring(0, str.length() - 1);
         }
      }
      // 2. Then we check that the level is correctly formatted
      int firstRowLength = txtArray[0].length();
      for (int i = 0; i < txtArray.length; i++) {
         String row = txtArray[i];

         // 2.1 Checks that the row has the same length as the first.
         if (!(firstRowLength == row.length())) {
            throw new IllegalArgumentException("All rows must have equal length");
         }
         // 2.2 Checks that the last char in the row is a wall
         else if (!lastCharInRowIsWall(row)) {
            throw new IllegalArgumentException("The last char in the row must be a wall");
         }
      }
      // 3. Returns the formatted array as a list.
      return Arrays.asList(txtArray);
   }

   private boolean lastCharInRowIsWall(String row) {
      return row.charAt(row.length() - 1) == Constants.WALL;
   }

   private GameBoard makeGrid(List<String> formattedLevel) {
      List<List<Character>> gridList = new ArrayList<>();

      // For each row:
      for (int r = 0; r < formattedLevel.size(); r++) {
         char[] row = formattedLevel.get(r).toCharArray();
         gridList.add(new ArrayList<Character>());

         // For each column:
         for (int c = 0; c < row.length; c++) {
            addEntitiesPos(row[c], r, c);
            addToGrid(row[c], r, c, gridList);
         }
      }
      if (!playerFound) {
         throw new IllegalArgumentException("There is no player in the given text-file!");
      }
      return new GameBoard(gridList);
   }

   /** Saves the position of an entity, based on the character in the cell. */
   private void addEntitiesPos(Character c, int row, int col) {
      switch (c) {
         case Constants.PLAYER:
            this.playerPos = new CellPosition(row, col);
            this.playerFound = true;
            break;
         case Constants.ENEMY:
            this.enemies.add(new CellPosition(row, col));
            break;
         case Constants.BARREL:
            this.barrels.add(new CellPosition(row, col));
            break;
      }
   }

   private void addToGrid(char c, int row, int col, List<List<Character>> gridList) {
      if (c == Constants.WALL) {
         gridList.get(row).add(Constants.WALL);
      } else {
         gridList.get(row).add(Constants.EMPTY);
      }
   }


   /**
    * Returns the players position from the text-file which was most recently read.
    */
   public CellPosition getPlayerPos() {
      return this.playerPos;
   }

   /**
    * Returns the positions of the barrels from the text-file which was most
    * recently read.
    */
   public ArrayList<CellPosition> getBarrelsPos() {
      return this.barrels;
   }

   /**
    * Returns the positions of the enemies from the text-file which was most
    * recently read.
    */
   public ArrayList<CellPosition> getEnemiesPos() {
      return this.enemies;
   }

   /** Returns the board from the text-file which was most recently read. */
   public GameBoard getBoard() {
      return this.board;
   }
}