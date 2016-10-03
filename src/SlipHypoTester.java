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

    private int numslips;
    private final ThreeSlipStrategy[] STRATEGIES = {
            ThreeSlipStrategy.KEEP_ORIGINAL,
            ThreeSlipStrategy.SWITCH_TO_REVEALED,
            ThreeSlipStrategy.SWITCH_TO_UNKNOWN,
            ThreeSlipStrategy.INFORMED_STRATEGY
    };

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
     * @param numslips the number of slips to use for this simulation
     */
    public SlipHypoTester(int numslips) {
        this.numslips = numslips;
    }

    private Slip[] populateSlips(int numslips) {
        Slip[] slips = new Slip[numslips];
        ArrayList<Integer> values = new ArrayList<>();
        int i = 0;
        while (i < numslips){
            Slip slip = new Slip();
            if (!values.contains(slip.value())) {
                slips[i] = slip;
                values.add(slip.value());
                i++;
            }
        }
        return slips;
    }

    private Slip findWinningSlip(Slip[] slips) {
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
     * @param minimum the smallest of the range of slip values
     * @param maximum the largest of the range of slip values
     * @return map from ThreeSlipStrategy object to number of wins associated
     * with that strategy
     */
    public Map<ThreeSlipStrategy, Long> simulatePlay(long numberOfRounds,
                                                     int minimum,
                                                     int maximum)
    {
        return new HashMap<>();
    }

    /**
     * Resets environment and simulate the game for a specified number of
     * rounds.
     *
     * @param numberOfRounds the number of rounds to simulate
     * @return map from ThreeSlipStrategy object to number of wins associated
     * with that strategy
     */
    public Map<ThreeSlipStrategy, Long> simulatePlay(long numberOfRounds) {
        HashMap<ThreeSlipStrategy, Long> winMap = new HashMap<>();
        for (ThreeSlipStrategy strategy : STRATEGIES) {
            Player player = getPlayerUsingStrategy(strategy);
            simulate(numberOfRounds, player);
            winMap.put(player.getStrategy(), player.getWinCount());
            if (player.isInformed()) {
                winMap.put(ThreeSlipStrategy.STOP_IF_BETTER,
                        player.getKeepSecondCount());
                winMap.put(ThreeSlipStrategy.CONTINUE_IF_WORSE,
                        player.getDiscardSecondCount());
            }
        }
        return winMap;
    }

    private Player getPlayerUsingStrategy(ThreeSlipStrategy strategy) {
        Player player;
        if (strategy == ThreeSlipStrategy.INFORMED_STRATEGY) {
            player = new InformedPlayer();
        }
        else {
            player = new UninformedPlayer(strategy);
        }
        return player;
    }

    private void simulate(long numberOfRounds, Player player) {
        for (long i = 0; i < numberOfRounds; i++) {
            Slip[] slips = populateSlips(numslips);
            Slip winningSlip = findWinningSlip(slips);
            player.chooseSlipFrom(slips);
            if (player.getChoice() == winningSlip) {
                player.incrementWinCount();
            }
        }
    }

    private void printTranscript(long numberOfRounds,
                                 Map<ThreeSlipStrategy, Long> winMap) {
        ThreeSlipStrategy bestUninformedStrategy =
                getBestUninformedStrategy(winMap);
        System.out.print(
                "Simulation Results for " + numberOfRounds + " Rounds\n" +
                "\n" +
                "Format>>  Strategy: Number-correct (% correct)\n" +
                "----\n" +
                "Uninformed Strategies\n" +
                format(ThreeSlipStrategy.KEEP_ORIGINAL,
                        winMap, numberOfRounds) +
                format(ThreeSlipStrategy.SWITCH_TO_REVEALED,
                        winMap, numberOfRounds) +
                format(ThreeSlipStrategy.SWITCH_TO_UNKNOWN,
                        winMap, numberOfRounds) +
                "----\n" +
                "Informed Strategy\n" +
                format(ThreeSlipStrategy.INFORMED_STRATEGY,
                        winMap, numberOfRounds) +
                "\n" +
                "Contributions to Informed Strategy\n" +
                format(ThreeSlipStrategy.STOP_IF_BETTER,
                        winMap, numberOfRounds) +
                format(ThreeSlipStrategy.CONTINUE_IF_WORSE,
                        winMap, numberOfRounds) +
                "----\n" +
                "Comparison\n" +
                "                              Best uninformed strategy: " +
                winMap.get(bestUninformedStrategy) + " (" +
                getRate(winMap.get(bestUninformedStrategy),
                        numberOfRounds) + "%)\n" +
                format(ThreeSlipStrategy.INFORMED_STRATEGY,
                        winMap, numberOfRounds)
        );
    }

    private ThreeSlipStrategy getBestUninformedStrategy(
            Map<ThreeSlipStrategy, Long> winMap) {
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

    private String format(ThreeSlipStrategy strategy, 
                          Map<ThreeSlipStrategy, Long> winMap,
                          long numberOfRounds) {
        long wins = winMap.get(strategy);
        long rate = getRate(wins, numberOfRounds);
        return " " + strategy.description() + ": " +
                wins + " (" + rate + "%)\n";
    }
    
    private long getRate(long numberOfWins, long numberOfRounds) {
        return 100 * numberOfWins / numberOfRounds;
    }

    /**
     * Run simulation and display results.
     *
     * @param args ignored
     */
    public static void main(String[] args) {
        SlipHypoTester hypoTester = new SlipHypoTester();
        final long NUMBER_OF_ROUNDS = 10000000;
        Map<ThreeSlipStrategy, Long> winMap;
        winMap = hypoTester.simulatePlay(NUMBER_OF_ROUNDS);
        hypoTester.printTranscript(NUMBER_OF_ROUNDS, winMap);
    }
}
