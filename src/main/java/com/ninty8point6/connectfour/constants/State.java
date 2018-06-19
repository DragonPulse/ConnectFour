package com.ninty8point6.connectfour.constants;

/**
 * The enum State.
 */
public enum State {

    /**
     * In progress state.
     */
    IN_PROGRESS("IN_PROGRESS"),

    /**
     * Done state.
     */
    DONE("DONE"),

    /**
     * Quit state.
     */
    QUIT("QUIT");

    private String state;

    State(String state) {
        this.state = state;
    }

    /**
     * Gets state.
     *
     * @return the state
     */
    public String getState() {
        return state;
    }
}