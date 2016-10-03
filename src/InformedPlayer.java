import java.util.ArrayList;

/**
 * Represents a player utilizing the informed strategy in the three-slip
 * game.
 *
 * @author Bennett Alex Myers
 * @version 3 October 2016
 */
public class InformedPlayer extends Player{

    private final ThreeSlipStrategy strategy =
            ThreeSlipStrategy.INFORMED_STRATEGY;
    private Slip choice;
    private long winCount;
    private long keepSecondCount;
    private long discardSecondCount;

    public InformedPlayer() {
        this.winCount = 0;
        this.keepSecondCount = 0;
        this.discardSecondCount = 0;
    }

    public boolean isInformed() {
        return true;
    }

    public Slip getChoice() {
        return this.choice;
    }

    public long getWinCount() {
        return this.winCount;
    }

    public void incrementWinCount() {
        this.winCount++;
    }

    public long getKeepSecondCount() {
        return this.keepSecondCount;
    }

    public long getDiscardSecondCount() {
        return this.discardSecondCount;
    }

    public ThreeSlipStrategy getStrategy() {
        return this.strategy;
    }

    public void chooseSlipFrom(Slip[] slips) {
        if (slips[1].value() > slips[0].value()) {
            this.choice = slips[1];
            this.keepSecondCount++;
        }
        else {
            this.choice = slips[2];
            this.discardSecondCount++;
        }
    }
}
