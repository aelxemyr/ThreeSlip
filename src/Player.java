/**
 * Represents a player for three-slip game.
 *
 * @author Bennett Alex Myers
 * @version 3 October 2016
 */
public abstract class Player {

    /**
     * Predicate to check if player is using the informed strategy.
     * @return true if player is informed, false otherwise
     */
    public abstract boolean isInformed();

    /**
     * Retrieves this player's choice of slip.
     * @return the slip chosen by this player
     */
    public abstract Slip getChoice();

    /**
     * Returns this player's win count.
     * @return the number of games this player has won
     */
    public abstract long getWinCount();

    /**
     * Increments this player's win count.
     */
    public abstract void incrementWinCount();

    /**
     * Returns the number of times the second slip was kept.
     * @return the number of times the second slip was kept
     */
    public abstract long getKeepSecondCount();

    /**
     * Increment this player's count of times won by keeping the second slip.
     */
    public abstract void incrementKeepSecondCount();

    /**
     * Returns the number of times the second slip was discarded.
     * @return the number of times the second slip was discarded
     */
    public abstract long getDiscardSecondCount();

    /**
     * Increment this player's count of times won by discarding the second
     * slip.
     */
    public abstract void incrementDiscardSecondCount();

    /**
     * Return the strategy used by this player.
     * @return ThreeSlipStrategy object representing this player's
     * strategy
     */
    public abstract ThreeSlipStrategy getStrategy();

    /**
     * Choose a slip based on the player's strategy.
     * @param slips the slips from which to choose
     */
    public abstract void chooseSlipFrom(Slip[] slips);
}
