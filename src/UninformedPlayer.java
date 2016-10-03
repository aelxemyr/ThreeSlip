/**
 * Represents a player utilizing the uninformed strategy in the three-slip
 * game.
 *
 * @author Bennett Alex Myers
 * @version 3 October 2016
 */
public class UninformedPlayer extends Player{

    private ThreeSlipStrategy strategy;
    private Slip choice;
    private long winCount;

    public boolean isInformed() {
        return false;
    }

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

    public long getKeepSecondCount() {
        return 0;
    }

    public long getDiscardSecondCount() {
        return 0;
    }

    public void incrementWinCount() {
        this.winCount++;
    }

    public ThreeSlipStrategy getStrategy() {
        return this.strategy;
    }

    public void chooseSlipFrom(Slip[] slips) {
        switch (strategy) {
            case KEEP_ORIGINAL:
                this.choice = slips[0];
            case SWITCH_TO_REVEALED:
                this.choice = slips[1];
            case SWITCH_TO_UNKNOWN:
                this.choice = slips[2];
        }
    }
}
