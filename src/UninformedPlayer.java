/**
 * Represents a player utilizing the uninformed strategy in the three-slip
 * game.
 *
 * @author Bennett Alex Myers
 * @version 3 October 2016
 */
public class UninformedPlayer extends Player {

    /** The strategy used by this player. */
    private ThreeSlipStrategy strategy;
    /** The slip currently chosen by this player. */
    private Slip choice;
    /** The number of times this player has won. */
    private long winCount;
    /** The number of times won by keeping the second slip. */
    private long keepSecondCount;
    /** The number of times won by discarding the second slip. */
    private long discardSecondCount;

    /**
     * Predicate to check if player is using the informed strategy.
     * @return false
     */
    public boolean isInformed() {
        return false;
    }

    /**
     * Create a new uninformed player with the specified strategy.
     * @param strtgy the uninformed strategy the player is using
     */
    public UninformedPlayer(final ThreeSlipStrategy strtgy) {
        this.strategy = strtgy;
        this.winCount = 0;
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
     * @return ThreeSlipStrategy object representing this player's
     * strategy
     */
    public ThreeSlipStrategy getStrategy() {
        return this.strategy;
    }

    /**
     * Choose a slip based on the player's strategy.
     * @param slips the slips from which to choose
     */
    public void chooseSlipFrom(final Slip[] slips) {
        switch (strategy) {
            case KEEP_ORIGINAL:
                this.choice = slips[0];
                break;
            case SWITCH_TO_REVEALED:
                this.choice = slips[1];
                break;
            case SWITCH_TO_UNKNOWN:
                this.choice = slips[2];
                break;
            default:
                break;
        }
    }
}
