/**
 * The enumerated strategies for the 3-slip game.
 *
 * @author Bennett Alex Myers
 * @version 2 October 2016
 */
public enum ThreeSlipStrategy {
    CONTINUE_IF_WORSE  ("Discard second if smaller than first"),
    INFORMED_STRATEGY  ("Keep second if larger than first," +
                               " otherwise keep third"),
    KEEP_ORIGINAL      (" Keep first slip chosen"),
    STOP_IF_BETTER     ("    Keep second if larger than first"),
    SWITCH_TO_REVEALED ("Keep second slip chosen"),
    SWITCH_TO_UNKNOWN  ("       Choose last slip");

    private String description;

    ThreeSlipStrategy(String description) {
        this.description = description;
    }

    /**
     * Accesses this strategy descriptor.
     * @return the human-readable description of this strategy.
     */
    public String description() {
        return this.description;
    }
}
