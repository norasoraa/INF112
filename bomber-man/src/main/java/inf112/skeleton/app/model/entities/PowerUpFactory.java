package inf112.skeleton.app.model.entities;

import java.util.Random;
import inf112.skeleton.app.model.CellPosition;
import inf112.skeleton.app.utilities.Constants;

/**
 * Factory class for creating PowerUp objects.
 */
public class PowerUpFactory {
    
    private Player player;
    private Random rand;

    /**
     * Constructs a PowerUpFactory for a specific player.
     *
     * @param player The player who will be associated with the created power-ups.
     */
    public PowerUpFactory(Player player) {
        this.player = player;
        this.rand = new Random();
    }
    
    private PowerUp createPowerUp(CellPosition cp, int type) {
        return new PowerUp(cp, type, player);
    }

    /**
     * Creates a PowerUp with a random type at a specified position.
     *
     * @param cp The cell position where the power-up will be placed.
     * @return A new PowerUp object with a random type.
     */
    public PowerUp createRandomPowerUp(CellPosition cp) {
        int type = rand.nextInt(Constants.ALL_POWERUP_TYPES.length);
        return createPowerUp(cp, type);
    }
}
