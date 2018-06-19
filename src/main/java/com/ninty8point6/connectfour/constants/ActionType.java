package com.ninty8point6.connectfour.constants;

/**
 * The enum Action type.
 */
public enum ActionType {


    /**
     * Move action type.
     */
    MOVE("MOVE"),

    /**
     * Quit action type.
     */
    QUIT("QUIT");

        private String type;

        ActionType(String type) {
            this.type = type;
        }

    /**
     * Gets action type.
     *
     * @return the action type
     */
    public String getActionType() {
            return type;
        }


}
