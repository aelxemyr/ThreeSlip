import java.util.Random;

/**
 * Represents slip of paper for 3-slip game.
 *
 * @author Bennett Alex Myers
 * @version 3 October 2016
 */
public class Slip {

    /** The value written on this slip. */
    private int value;

    /**
     * Constructor that sets value to a random integer in the range
     * 0..Integer.MAX_VALUE.
     */
    public Slip() {
        Random rng = new Random();
        this.value = rng.nextInt(Integer.MAX_VALUE);
    }

    /**
     * Constructor that sets value to a random integer in the specified range.
     * @param minimum the smallest value of the range
     * @param maximum the largest value of the range
     */
    public Slip(final int minimum, final int maximum) {
        Random rng = new Random();
        this.value = rng.nextInt(maximum - minimum + 1) + minimum;
    }

    /**
     * Access the value written on this slip.
     * @return the value written on this slip
     */
    public int value() {
        return this.value;
    }
}
