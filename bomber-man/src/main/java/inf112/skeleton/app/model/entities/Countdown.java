package inf112.skeleton.app.model.entities;

public interface Countdown {

    /**
     * Decrements the countdown.
     */
    public void tick();

    /**
     * Checks if the countdown is complete.
     * 
     * @return true if the countdown is over.
     */
    public boolean isOver();
    
}
