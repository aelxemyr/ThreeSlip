/**
 * Represents a player utilizing the informed strategy in the three-slip
 * game.
 *
 * @author Bennett Alex Myers
 * @version 3 October 2016
 */
public class InformedPlayer extends Player {

    /** The strategy used by this player. */
    private final ThreeSlipStrategy strategy =
            ThreeSlipStrategy.INFORMED_STRATEGY;
    /** The slip currently chosen by this player. */
    private Slip choice;
    /** The number of times this player has won. */
    private long winCount;
    /** The number of times won by keeping the second slip. */
    private long keepSecondCount;
    /** The number of times won by discarding the second slip. */
    private long discardSecondCount;

    /**
     * Create a new informed player.
     */
    public InformedPlayer() {
        this.winCount = 0;
        this.keepSecondCount = 0;
        this.discardSecondCount = 0;
    }

    /**
     * Predicate to check if the player is using the informed strategy.
     * @return true
     */
    public boolean isInformed() {
        return true;
    }

    /**
     * Retrieves this player's choice of slip.
     * @return the slip chosen by this player
     */
    public Slip getChoice() {
        return this.choice;
    }

    /**
     * Returns this player's win count.
     * @return the number of games this player has won
     */
    public long getWinCount() {
        return this.winCount;
    }

    /**
     * Increments this player's win count.
     */
    public void incrementWinCount() {
        this.winCount++;
    }

    /**
     * Returns the number of times the second slip was kept using the informed
     * strategy.
     * @return the number of times the second slip was kept
     */
    public long getKeepSecondCount() {
        return this.keepSecondCount;
    }

    /**
     * Increment this player's count of wins by keeping the second slip.
     */
    public void incrementKeepSecondCount() {
        this.keepSecondCount++;
    }

    /**
     * Returns the number of times the second slip was discarded using the
     * informed strategy.
     * @return the number of times the second slip was discarded
     */
    public long getDiscardSecondCount() {
        return this.discardSecondCount;
    }

    /**
     * Increment this player's count of wins by discarding the second slip.
     */
    public void incrementDiscardSecondCount() {
        this.discardSecondCount++;
    }

    /**
     * Return the strategy used by this player.
     * @return INFORMED_STRATEGY
     */
    public ThreeSlipStrategy getStrategy() {
        return this.strategy;
    }

    /**
     * Choose a slip based on the informed strategy.
     * @param slips the slips from which to choose
     */
    public void chooseSlipFrom(final Slip[] slips) {
        if (slips[1].value() > slips[0].value()) {
            this.choice = slips[1];
        } else {
            this.choice = slips[2];
        }
    }
}
