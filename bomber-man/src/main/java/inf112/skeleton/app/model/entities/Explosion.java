package inf112.skeleton.app.model.entities;

import java.util.ArrayList;
import inf112.skeleton.app.model.CellPosition;
import inf112.skeleton.app.model.Direction;
import inf112.skeleton.app.model.GameBoard;
import inf112.skeleton.app.utilities.Constants;

/**
 * Represents an explosion within the game. It calculates the impact positions based on a source location and range,
 * and schedules an end to the explosion effect.
 */
public class Explosion implements Countdown {

    public ArrayList<CellPosition> impactPositions; // holds positions of exploding cells
    
    private final int RANGE;
    private final CellPosition SOURCE;
    private final int DURATION = Constants.EXPLOSION_DURATION; // in seconds
    private final GameBoard BOARD;
    
    private EntityModel model;
    private int countDown;

    /**
     * Constructs an Explosion object.
     * 
     * @param model The entity model for interaction.
     * @param board The game board on which the explosion occurs.
     * @param position The source position of the explosion.
     * @param range The maximum range of the explosion's effect.
     */
    public Explosion(EntityModel model, GameBoard board, CellPosition position, int range) {
        this.BOARD = board;
        this.RANGE = range;
        this.SOURCE = position;
        this.model = model;
        this.impactPositions = new ArrayList<>();
        this.countDown = DURATION;

        addImpactPositions();
    }
    
    /* Gather exploding cells */
    private void addImpactPositions() {
        impactPositions.add(SOURCE); // Source is included in impact

        // Evaluate the impact in all directions
        for (Direction dir : Direction.values()) {
            fireInDirection(dir);
        }
    }

    /**
     * Propagates explosion from the source position in a specified direction,
     * marking explodable cells until an obstacle is met, or the range is exhausted.
     */
    private void fireInDirection(Direction dir) {
        for (int i = 1; i <= RANGE; i++) {
            CellPosition nextCp = new CellPosition(SOURCE.row() + i * dir.deltaY, SOURCE.col() + i * dir.deltaX);

            if (isExplodable(nextCp)) {
                impactPositions.add(nextCp);
            }

            if (!permitsPropagation(nextCp)) {
                break; // Stops further explosions in this direction
            }
        }
    }

    /* Determines if the given position is susceptible to an explosion */ 
    private boolean isExplodable(CellPosition cp) {
        if (!BOARD.positionIsOnGrid(cp)) {
            return false; // Position is outside the grid, no explosion
        }

        if (BOARD.isWallAt(cp)) {
            return false; // Walls block explosions
        }
        return true;  
    }

    /* Check wether given cell allows for further explosion propagation */
    private boolean permitsPropagation(CellPosition cp) {
        return !BOARD.isWallAt(cp) && !model.isBarrelAt(cp) && !model.isEnemyAt(cp);  // Only empty cells allow the explosion to propagate
    }

    @Override
    public void tick() {
        this.countDown--;
    }

    @Override
    public boolean isOver() {
        return this.countDown <= 0;
    }
}
