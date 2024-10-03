package inf112.skeleton.app.model.entities;

import inf112.skeleton.app.model.CellPosition;
import static inf112.skeleton.app.utilities.Constants.POWERUP_EXPLOSION_RANGE;
import static inf112.skeleton.app.utilities.Constants.POWERUP_EXTRA_LIFE;
import static inf112.skeleton.app.utilities.Constants.POWERUP_MAX_BOMBS;

/**
 * Represents power-ups in the game.
 * Power-ups can be activated by players to gain advantages.
 */
public class PowerUp extends Entity implements ViewablePowerup {

    private final int type;
    private Player player;

     /**
     * Constructs a PowerUp object with the specified parameters.
     * 
     * @param cp The cell position where the power-up is located.
     * @param type The type of the power-up.
     * @param player The player associated with the power-up.
     */
    public PowerUp(CellPosition cp, int type, Player player) {
        super(cp);
        this.type = type;
        this.position = cp;
        this.player = player;
    }

    /**
     * Activates the power-up based on its type.
     * The method applies corresponding effects to the associated player.
     * 
     * For POWERUP_EXPLOSION_RANGE: Increases bomb explosion range of the player.
     * For POWERUP_MAX_BOMBS: Increases maximum bombs count of the player.
     * For POERUP_EXTRA_LIFE: Increases healthpoints of the player.
     */
    public void activate() {
        switch (type) {
            case POWERUP_EXPLOSION_RANGE:
                player.increaseBombRange();
                break;
            case POWERUP_MAX_BOMBS:
                player.setMaxBombs(player.getMaxBombs()+1);
                break;
            case POWERUP_EXTRA_LIFE:
                player.increaseLife();
                break;
            default:
                break;
        }
    }

    @Override
    public int getType(){
        return type;
    }

}
