package com.ninty8point6.connectfour.models;

import java.util.List;

import com.google.common.base.Preconditions;
import com.ninty8point6.connectfour.exceptions.InvalidParametersException;

/**
 * The type Game request.
 */
public class GameRequest {


    private List<String> players;
    private Integer columns;
    private Integer rows;

    /**
     * Instantiates a new Game request.
     */
    public GameRequest() {
    }

    /**
     * Instantiates a new Game request.
     *
     * @param players the players
     * @param columns the columns
     * @param rows    the rows
     */
    public GameRequest(List<String> players, Integer columns, Integer rows) {
        this.players = players;
        this.columns = columns;
        this.rows = rows;
    }

    /**
     * Gets players.
     *
     * @return the players
     */
    public List<String> getPlayers() {
        return players;
    }

    /**
     * Sets players.
     *
     * @param players the players
     */
    public void setPlayers(List<String> players) {
        this.players = players;
    }

    /**
     * Gets columns.
     *
     * @return the columns
     */
    public Integer getColumns() {
        return columns;
    }

    /**
     * Sets columns.
     *
     * @param columns the columns
     */
    public void setColumns(Integer columns) {
        this.columns = columns;
    }

    /**
     * Gets rows.
     *
     * @return the rows
     */
    public Integer getRows() {
        return rows;
    }

    /**
     * Sets rows.
     *
     * @param rows the rows
     */
    public void setRows(Integer rows) {
        this.rows = rows;
    }
}
