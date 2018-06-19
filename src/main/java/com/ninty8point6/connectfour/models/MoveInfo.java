package com.ninty8point6.connectfour.models;

import com.ninty8point6.connectfour.entities.Game;
import com.ninty8point6.connectfour.entities.GameState;
import com.ninty8point6.connectfour.entities.Move;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Move info.
 */
public class MoveInfo {

    private String type;
    private String player;
    private String column;


    /**
     * Instantiates a new Move info.
     *
     * @param type   the type
     * @param player the player
     * @param column the column
     */
    public MoveInfo(String type, String player, String column) {
        this.type = type;
        this.player = player;
        this.column = column;
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
     * Create move info move info.
     *
     * @param move the move
     * @return the move info
     */
    public static MoveInfo createMoveInfo(Move move) {
        MoveInfo moveInfo = new MoveInfo(move.getActionType(),move.getPlayer().getName(),move.getColumn());
        return moveInfo;
    }
}
