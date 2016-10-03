import java.util.ArrayList;

/**
 * Represents a player for three-slip game.
 *
 * @author Bennett Alex Myers
 * @version 2 October 2016
 */
public abstract class Player {

    /**
     * Predicate to check if player is using the informed strategy.
     *
     * @return true if player is informed, false otherwise
     */
    public abstract boolean isInformed();

    /**
     * Retrieves this player's choice of slip.
     *
     * @return the slip chosen
     */
    public abstract Slip getChoice();

    /**
     * Returns this player's win count.
     *
     * @return the number of games this player has won
     */
    public abstract long getWinCount();

    /**
     * Returns the number of times the second slip was kept using the informed
     * strategy.
     *
     * @return the number of times the second slip was kept is player is
     * informed; otherwise, 0
     */
    public abstract long getKeepSecondCount();

    /**
     * Returns the number of times the second slip was discarded using the
     * informed strategy.
     *
     * @return the number of times the second slip was discarded if player is
     * informed; otherwise 0
     */
    public abstract long getDiscardSecondCount();

    /**
     * Increments this player's win count.
     */
    public abstract void incrementWinCount();

    /**
     * Return the strategy used by this player.
     *
     * @return the ThreeSlipStrategy object representing this player's
     * strategy
     */
    public abstract ThreeSlipStrategy getStrategy();

    /**
     * Choose a slip from the available slips based on the player's strategy.
     *
     * @param slips the available slips from which to choose
     */
    public abstract void chooseSlipFrom(Slip[] slips);
}
