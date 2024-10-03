package inf112.skeleton.app.model.entities;

import inf112.skeleton.app.model.CellPosition;
import inf112.skeleton.app.utilities.Constants;
/**
 * Represents a bomb entity in the game.
 * When a bomb is placed, a timer will start and the bomb will explode when the
 * timer runs out.
 */
public class Bomb extends Entity implements Countdown, ViewableEntity {

    private final int RANGE;
    private final int DURATION;

    private int countDown;

    /**
     * Constructs a new bomb at the specified position with a given range.
     * 
     * @param position the position of the bomb, represented as a
     *                 {@link CellPosition}
     * @param range    the range of the bomb
     */
    public Bomb(CellPosition position, int range) {
        super(position);
        this.RANGE = range;
        this.DURATION = Constants.BOMB_DURATION;
        this.countDown = DURATION;
    }

    /**
     * Constructs a new bomb at the specified position with default range.
     * 
     * @param gameModel the entity model representing the game entities
     * @param position  the position of the bomb, represented as a
     *                  {@link CellPosition}
     */
    public Bomb(EntityModel gameModel, CellPosition position) {
        this(position, Constants.DEFAULT_EXPLOSION_RANGE);
    }

    @Override
    public void tick() {
        this.countDown--;
    }

    @Override
    public boolean isOver() {
        return this.countDown <= 0;
    }

    /** Returns the range of the bomb. */
    public int getRange() {
        return RANGE;
    }
}