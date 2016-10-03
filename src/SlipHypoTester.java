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
    private ArrayList<Slip> slips;
    private Slip winningSlip;
    private Player player;
    private static final ThreeSlipStrategy[] STRATEGIES = {
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
        this.slips = new ArrayList<>();
        for (int i = 0; i < numslips; i++) {
            this.slips.add(new Slip());
        }
        this.winningSlip = findWinningSlip();
    }

    /**
     * Initialize with a given number of slips. Sets up the slips and resets
     * the tallies.
     *
     * @param numslips the number of slips to use for this simulation
     */
    public SlipHypoTester(int numslips) {
        this.numslips = numslips;
        this.slips = new ArrayList<>();
        for (int i = 0; i < numslips; i++) {
            this.slips.add(new Slip());
        }
        this.winningSlip = findWinningSlip();
    }

    private Slip findWinningSlip() {
        Slip winner = this.slips.get(0);
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
     * Resets envionment and simulate the game for a specified number of
     * rounds.
     *
     * @param numberOfRounds the number of rounds to simulate
     * @return map from ThreeSlipStrategy object to number of wins associated
     * with that strategy
     */
    public Map<ThreeSlipStrategy, Long> simulatePlay(long numberOfRounds) {
        HashMap<ThreeSlipStrategy, Long> winMap = new HashMap<>();
        for (ThreeSlipStrategy strategy : STRATEGIES) {
            Long numberOfWins = simulateWith(strategy, numberOfRounds);
            winMap.put(strategy, numberOfWins);
        }
        return winMap;
    }

    private Long simulateWith(ThreeSlipStrategy strategy, long numberOfRounds) {
        return 0L;
    }

    /**
     * Run simulation and display results.
     *
     * @param args ignored
     */
    public static void main(String[] args) {

    }
}
