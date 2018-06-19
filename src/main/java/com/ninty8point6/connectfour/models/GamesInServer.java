package com.ninty8point6.connectfour.models;

import java.util.List;

/**
 * The type Games in server.
 */
public class GamesInServer {
    private List<String> games;

    /**
     * Instantiates a new Games in server.
     *
     * @param games the games
     */
    public GamesInServer(List<String> games) {
        this.games = games;
    }

    /**
     * Gets games.
     *
     * @return the games
     */
    public List<String> getGames() {
        return games;
    }

    /**
     * Sets games.
     *
     * @param games the games
     */
    public void setGames(List<String> games) {
        this.games = games;
    }
}
