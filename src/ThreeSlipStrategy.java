/**
 * The enumerated strategies for the 3-slip game.
 *
 * @author Bennett Alex Myers
 * @version 2 October 2016
 */
public enum ThreeSlipStrategy {
    /** Discard second if smaller than first. */
    CONTINUE_IF_WORSE  ("Discard second if smaller than first"),
    /** Keep second if larger than first, otherwise keep third. */
    INFORMED_STRATEGY  ("Keep second if larger than first,"
            + " otherwise keep third"),
    /** Keep first slip chosen. */
    KEEP_ORIGINAL      (" Keep first slip chosen"),
    /** Keep second if larger than first. */
    STOP_IF_BETTER     ("    Keep second if larger than first"),
    /** Keep second slip chosen. */
    SWITCH_TO_REVEALED ("Keep second slip chosen"),
    /** Choose last slip. */
    SWITCH_TO_UNKNOWN  ("       Choose last slip");

    /** The human-readable description of this strategy. */
    private String description;

    /**
     * ThreeSlipStrategy enum constructor.
     * @param strategyDescriptor the human-readable description of this strategy
     */
    ThreeSlipStrategy(final String strategyDescriptor) {
        this.description = strategyDescriptor;
    }

    /**
     * Accesses this strategy descriptor.
     * @return the human-readable description of this strategy.
     */
    public String description() {
        return this.description;
    }
}
