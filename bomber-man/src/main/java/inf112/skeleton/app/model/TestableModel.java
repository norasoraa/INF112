package inf112.skeleton.app.model;

import java.util.ArrayList;

import inf112.skeleton.app.model.entities.Barrel;
import inf112.skeleton.app.model.entities.Bomb;
import inf112.skeleton.app.model.entities.Enemy;
import inf112.skeleton.app.model.entities.Player;

/** An interface specifying methods for use in testing */
public interface TestableModel {

  /** Gets the player */
  public Player getPlayer();

  /** Gets the list of enemies */
  public ArrayList<Enemy> getEnemies();

  /** Gets the list of barrels */
  public ArrayList<Barrel> getBarrels();

  /** Gets the list of active bombs */
  public ArrayList<Bomb> getActiveBombs();

  /**
   * Reads the levelMap from the given path, and resets all objects in the model.
   */
  public void setLevel(String path);

  /** Sets the maximum number of bombs the player has */
  public void setPlayerMaxBombs(int maxBombs);
}
