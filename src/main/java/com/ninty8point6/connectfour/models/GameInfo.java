package com.ninty8point6.connectfour.models;

/**
 * The type Game info.
 */
public class GameInfo {
    private String gameId;

    /**
     * Instantiates a new Game info.
     *
     * @param gameId the game id
     */
    public GameInfo(String gameId) {
        this.gameId = gameId;
    }

    /**
     * Gets game id.
     *
     * @return the game id
     */
    public String getGameId() {
        return gameId;
    }

    /**
     * Sets game id.
     *
     * @param gameId the game id
     */
    public void setGameId(String gameId) {
        this.gameId = gameId;
    }
}
