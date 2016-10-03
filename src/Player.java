import java.util.ArrayList;

/**
 * Represents a player for three-slip game.
 *
 * @author Bennett Alex Myers
 * @version 2 October 2016
 */
public abstract class Player {

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
     * Increments this player's win count.
     */
    public abstract void incrementWinCount();

    /**
     * Choose a slip from the available slips based on the player's strategy.
     *
     * @param slips the available slips from which to choose
     */
    public abstract void chooseSlipFrom(Slip[] slips);
}
