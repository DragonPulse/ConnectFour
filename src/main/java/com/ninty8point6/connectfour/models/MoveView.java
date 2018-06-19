package com.ninty8point6.connectfour.models;

/**
 * The type Move view.
 */
public class MoveView {

    private String type;

    private String column;

    private String player;

    /**
     * Instantiates a new Move view.
     *
     * @param type   the type
     * @param column the column
     * @param player the player
     */
    public MoveView(String type, String column, String player) {
        this.type = type;
        this.column = column;
        this.player = player;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets column.
     *
     * @return the column
     */
    public String getColumn() {
        return column;
    }

    /**
     * Sets column.
     *
     * @param column the column
     */
    public void setColumn(String column) {
        this.column = column;
    }

    /**
     * Gets player.
     *
     * @return the player
     */
    public String getPlayer() {
        return player;
    }

    /**
     * Sets player.
     *
     * @param player the player
     */
    public void setPlayer(String player) {
        this.player = player;
    }
}
