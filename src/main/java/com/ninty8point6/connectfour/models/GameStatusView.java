package com.ninty8point6.connectfour.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ninty8point6.connectfour.entities.Game;
import com.ninty8point6.connectfour.entities.GameState;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Game status view.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GameStatusView {

    private List<String> players;
    private String state;
    private String winner;

    /**
     * Create view game status view.
     *
     * @param game the game
     * @return the game status view
     */
    public static GameStatusView createView(Game game) {
        GameStatusView gameStatusView = new GameStatusView();


        List<String> players = new ArrayList<>();
        game
                .getPlayers()
                .forEach(player -> players.add(player.getName()));

        gameStatusView.setPlayers(players);

        GameState state = game.getState();

        if (null != state) {
            gameStatusView.setState(state.getState());
            gameStatusView.setWinner(state.getWinnerId());
        }

        return gameStatusView;
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
     * Gets state.
     *
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * Sets state.
     *
     * @param state the state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Gets winner.
     *
     * @return the winner
     */
    public String getWinner() {
        return winner;
    }

    /**
     * Sets winner.
     *
     * @param winner the winner
     */
    public void setWinner(String winner) {
        this.winner = winner;
    }
}
