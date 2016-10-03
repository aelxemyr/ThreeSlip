import java.util.ArrayList;

/**
 * Represents a player utilizing the uninformed strategy in the three-slip
 * game.
 *
 * @author Bennett Alex Myers
 * @version 3 October 2016
 */
public class UninformedPlayer implements Player{

    private ThreeSlipStrategy strategy;
    private Slip choice;
    private long winCount;

    public UninformedPlayer(ThreeSlipStrategy strategy) {
        this.strategy = strategy;
        this.winCount = 0;
    }

    public Slip getChoice() {
        return this.choice;
    }

    public long getWinCount() {
        return this.winCount;
    }

    public void chooseSlipFrom(ArrayList<Slip> slips) {
        switch (strategy) {
            case KEEP_ORIGINAL:
                chooseFirstSlipFrom(slips);
            case SWITCH_TO_REVEALED:
                chooseSecondSlipFrom(slips);
            case SWITCH_TO_UNKNOWN:
                chooseThirdSlipFrom(slips);
        }
    }

    public void chooseFirstSlipFrom(ArrayList<Slip> slips) {

    }

    public void chooseSecondSlipFrom(ArrayList<Slip> slip) {

    }

    public void chooseThirdSlipFrom(ArrayList<Slip> slip) {

    }
}
