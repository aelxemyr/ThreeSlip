import java.util.HashMap;

/**
 *
 *
 * @author Bennett Alex Myers
 * @version 2 October 2016
 */
public class SlipCollection {

    private int numberOfSlips;
    private HashMap<Integer, Slip> valueSlipMap;

    public SlipCollection(int numberOfSlips) {
        this.numberOfSlips = numberOfSlips;
        while (valueSlipMap.size() < numberOfSlips) {
            Slip slip = new Slip();
            valueSlipMap.put(slip.value(), slip);
        }
    }
}
