import java.util.ArrayList;

/**
 * Represents a player for three-slip game.
 *
 * @author Bennett Alex Myers
 * @version 2 October 2016
 */
public interface Player {

    /**
     * Choose a slip from the available slips based on the player's strategy.
     *
     * @param slips the available slips from which to choose
     */
    void chooseSlipFrom(ArrayList<Slip> slips);

    /**
     * Retrieves this player's current choice.
     *
     * @return the slip currently chosen
     */
    Slip getChoice();

    /**
     * Returns this player's win count.
     *
     * @return the number of games this player has won
     */
    long getWinCount();
}
