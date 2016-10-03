import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * Three slip problem simulation to test hypotheses.
 *
 * @author Bennett Alex Myers
 * @version 2 October 2016
 */
public class SlipHypoTester {

    /**
     * The standard number of slips used in the three-slip game.
     */
    public static final int STANDARD_NUMBER_OF_SLIPS = 3;
    /**
     * The number of slips used in the three-slip game for this tester.
     */
    private int numslips;
    /**
     * The four strategies used in the three-slip game.
     */
    private static final ThreeSlipStrategy[] STRATEGIES = {
            ThreeSlipStrategy.KEEP_ORIGINAL,
            ThreeSlipStrategy.SWITCH_TO_REVEALED,
            ThreeSlipStrategy.SWITCH_TO_UNKNOWN,
            ThreeSlipStrategy.INFORMED_STRATEGY
    };
    /**
     * Default int value used for bounds when bounds are not needed.
     */
    private static final int DEFAULT = 0;

    /**
     * Initialize with the standard number of slips.
     */
    public SlipHypoTester() {
        this.numslips = STANDARD_NUMBER_OF_SLIPS;
    }

    /**
     * Initialize with a given number of slips. Sets up the slips and resets
     * the tallies.
     *
     * @param numberOfSlips the number of slips to use for this simulation
     */
    public SlipHypoTester(final int numberOfSlips) {
        this.numslips = numberOfSlips;
    }

    /**
     * Generate a new array of slips whose length is the number of slips
     * specified for this SlipHypoTester. Uses the empty constructor for Slip
     * if hasBounds is false; otherwise uses the bounded constructor for Slip
     * with given bounds.
     *
     * @param hasBounds whether or not bounds are specified for the slip values
     * @param minimum   the smallest value of the range for slips, if specified
     * @param maximum   the largest value of the range for slips, if specified
     * @return an array of slips with length numslips
     */
    private Slip[] generateSlips(final boolean hasBounds,
                                 final int minimum, final int maximum) {
        Slip[] slips = new Slip[this.numslips];
        ArrayList<Integer> values = new ArrayList<>();
        int i = 0;
        while (i < this.numslips) {
            Slip slip;
            if (hasBounds) {
                slip = new Slip(minimum, maximum);
            } else {
                slip = new Slip();
            }
            if (!values.contains(slip.value())) {
                slips[i] = slip;
                values.add(slip.value());
                i++;
            }
        }
        return slips;
    }

    /**
     * Return the winning slip from the given array of slips.
     *
     * @param slips the array from which to find the winning slip
     * @return the slip in the given array with the largest value
     */
    private Slip findWinningSlip(final Slip[] slips) {
        Slip winner = slips[0];
        for (Slip slip : slips) {
            if (slip.value() > winner.value()) {
                winner = slip;
            }
        }
        return winner;
    }

    /**
     * Resets environment and simulates the game for a specified number of
     * rounds and specified range of values.
     *
     * @param numberOfRounds the number of rounds to simulate
     * @param minimum        the smallest of the range of slip values
     * @param maximum        the largest of the range of slip values
     * @return map from ThreeSlipStrategy object to number of wins associated
     * with that strategy
     */
    public Map<ThreeSlipStrategy, Long> simulatePlay(final long numberOfRounds,
                                                     final int minimum,
                                                     final int maximum) {
        return simulatePlay(numberOfRounds, true, minimum, maximum);
    }

    /**
     * Resets environment and simulate the game for a specified number of
     * rounds.
     *
     * @param numberOfRounds the number of rounds to simulate
     * @return map from ThreeSlipStrategy object to number of wins associated
     * with that strategy
     */
    public Map<ThreeSlipStrategy, Long> simulatePlay(
            final long numberOfRounds) {
        return simulatePlay(numberOfRounds, false, DEFAULT, DEFAULT);
    }

    /**
     * Resets environment and simulates the game for a specified number of
     * rounds. If hasBounds is true, the slips used will have values within
     * the the range defined by the given minimum and maximum values.
     * Otherwise, slips will be made using the default constructor.
     *
     * @param numberOfRounds the number of rounds to simulate
     * @param hasBounds      whether or not bounds are specified for the slip
     *                       values
     * @param minimum        the smallest value of the range for slips, if
     *                       specified
     * @param maximum        the largest value of the range for slips, if
     *                       specified
     * @return map from ThreeSlipStrategy object to number of wins associated
     * with that strategy
     */
    private Map<ThreeSlipStrategy, Long> simulatePlay(final long numberOfRounds,
                                                      final boolean hasBounds,
                                                      final int minimum,
                                                      final int maximum) {
        HashMap<ThreeSlipStrategy, Long> winMap = new HashMap<>();
        for (ThreeSlipStrategy strategy : STRATEGIES) {
            Player player = getNewPlayerWith(strategy);
            simulate(numberOfRounds, player, hasBounds, minimum, maximum);
            winMap.put(player.getStrategy(), player.getWinCount());
            if (player.isInformed()) {
                mapInformedContributions(winMap, player);
            }
        }
        return winMap;
    }

    /**
     * Create a new player that utilizes the given strategy with which to run
     * simulations.
     *
     * @param strategy the new player's strategy
     * @return a player who uses the given strategy
     */
    private Player getNewPlayerWith(final ThreeSlipStrategy strategy) {
        Player player;
        if (strategy == ThreeSlipStrategy.INFORMED_STRATEGY) {
            player = new InformedPlayer();
        } else {
            player = new UninformedPlayer(strategy);
        }
        return player;
    }

    /**
     * Simulates the game for a given number of rounds with a given player.
     * If hasBounds is true, the slips used will have values within the range
     * defined by the given minimum and maximum values. Otherwise, slips will
     * be made using the default constructor.
     *
     * @param numberOfRounds the number of rounds to simulate
     * @param player         the player with which to simulate the game
     * @param hasBounds      whether or not bounds are specified for the slip
     *                       values
     * @param minimum        the smallest value of the range for slips, if
     *                       specified
     * @param maximum        the largest value of the range for slips, if
     *                       specified
     */
    private void simulate(final long numberOfRounds, final Player player,
                          final boolean hasBounds,
                          final int minimum, final int maximum) {
        for (long i = 0; i < numberOfRounds; i++) {
            Slip[] slips = generateSlips(hasBounds, minimum, maximum);
            Slip winningSlip = findWinningSlip(slips);
            player.chooseSlipFrom(slips);
            if (player.getChoice() == winningSlip) {
                player.incrementWinCount();
                if (player.getChoice() == slips[1]) {
                    player.incrementKeepSecondCount();
                }
                if (player.getChoice() == slips[2]) {
                    player.incrementDiscardSecondCount();
                }
            }
        }
    }

    /**
     * Map the ThreeSlipStrategys associated with the two cases of the informed
     * strategy to the number of times that case occurred.
     *
     * @param winMap map from ThreeSlipStrategy object to number of wins
     *               associated with that strategy
     * @param player the player whose informed choices are counted
     */
    private void mapInformedContributions(
            final Map<ThreeSlipStrategy, Long> winMap, final Player player) {
        winMap.put(ThreeSlipStrategy.STOP_IF_BETTER,
                player.getKeepSecondCount());
        winMap.put(ThreeSlipStrategy.CONTINUE_IF_WORSE,
                player.getDiscardSecondCount());
    }

    /**
     * Print the results of running the simulation.
     *
     * @param numberOfRounds the number of rounds that were simulated
     * @param winMap         map from ThreeSlipStrategy object to number of wins
     *                       associated with that strategy
     */
    private void printTranscript(final long numberOfRounds,
                                 final Map<ThreeSlipStrategy, Long> winMap) {
        ThreeSlipStrategy bestUninformedStrategy =
                getBestUninformedStrategy(winMap);
        System.out.print(
                "Simulation Results for " + numberOfRounds + " Rounds\n"
                        + "\n"
                        + "Format>>  Strategy: Number-correct (% correct)\n"
                        + "----\n"
                        + "Uninformed Strategies\n"
                        + format(ThreeSlipStrategy.KEEP_ORIGINAL,
                        winMap, numberOfRounds)
                        + format(ThreeSlipStrategy.SWITCH_TO_REVEALED,
                        winMap, numberOfRounds)
                        + format(ThreeSlipStrategy.SWITCH_TO_UNKNOWN,
                        winMap, numberOfRounds)
                        + "----\n"
                        + "Informed Strategy\n"
                        + format(ThreeSlipStrategy.INFORMED_STRATEGY,
                        winMap, numberOfRounds)
                        + "\n"
                        + "Contributions to Informed Strategy\n"
                        + format(ThreeSlipStrategy.STOP_IF_BETTER,
                        winMap, numberOfRounds)
                        + format(ThreeSlipStrategy.CONTINUE_IF_WORSE,
                        winMap, numberOfRounds)
                        + "----\n"
                        + "Comparison\n"
                        + "                               "
                        + "Best uninformed strategy: "
                        + winMap.get(bestUninformedStrategy) + " ("
                        + getRate(winMap.get(bestUninformedStrategy),
                        numberOfRounds) + "%)\n"
                        + format(ThreeSlipStrategy.INFORMED_STRATEGY,
                        winMap, numberOfRounds)
        );
    }

    /**
     * Returns the best uninformed strategy from the simulation.
     *
     * @param winMap map from ThreeSlipStrategy object to number of wins
     *               associated with that strategy
     * @return the ThreeSlipStrategy object corresponding to the best
     * uninformed strategy
     */
    private ThreeSlipStrategy getBestUninformedStrategy(
            final Map<ThreeSlipStrategy, Long> winMap) {
        ThreeSlipStrategy strategy = ThreeSlipStrategy.KEEP_ORIGINAL;
        long firstSlipWins = winMap.get(ThreeSlipStrategy.KEEP_ORIGINAL);
        long secondSlipWins = winMap.get(ThreeSlipStrategy.SWITCH_TO_REVEALED);
        long thirdSlipWins = winMap.get(ThreeSlipStrategy.SWITCH_TO_UNKNOWN);
        if (firstSlipWins < secondSlipWins) {
            strategy = ThreeSlipStrategy.SWITCH_TO_REVEALED;
        }
        if (thirdSlipWins > secondSlipWins) {
            strategy = ThreeSlipStrategy.SWITCH_TO_UNKNOWN;
        }
        return strategy;
    }

    /**
     * Formats the results from the simulation of a given strategy according
     * to the format:
     * <code>Strategy: Number-correct (% correct)</code>.
     *
     * @param strategy       the strategy whose results are to be formatted
     * @param winMap         map from ThreeSlipStrategy object to number of wins
     *                       associated with that strategy
     * @param numberOfRounds the number of rounds that were simulated
     * @return the formatted String of the result
     */
    private String format(final ThreeSlipStrategy strategy,
                          final Map<ThreeSlipStrategy, Long> winMap,
                          final long numberOfRounds) {
        long wins = winMap.get(strategy);
        long rate = getRate(wins, numberOfRounds);
        return " " + strategy.description() + ": "
                + wins + " (" + rate + "%)\n";
    }

    /**
     * Calculates the win rate given the number of wins and the number of
     * rounds.
     *
     * @param numberOfWins   the number of times the player won
     * @param numberOfRounds the number of rounds simulated
     * @return the rate (as a percentage) of wins to rounds
     */
    private long getRate(final long numberOfWins, final long numberOfRounds) {
        final long percentageMultiplier = 100;
        return percentageMultiplier * numberOfWins / numberOfRounds;
    }

    /**
     * Run simulation and display results.
     *
     * @param args ignored
     */
    public static void main(final String[] args) {
        SlipHypoTester hypoTester = new SlipHypoTester();
        final long numberOfRounds = 10000000;
        Map<ThreeSlipStrategy, Long> winMap;
        winMap = hypoTester.simulatePlay(numberOfRounds);
        hypoTester.printTranscript(numberOfRounds, winMap);
    }
}
