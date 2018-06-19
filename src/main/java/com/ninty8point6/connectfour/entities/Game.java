package com.ninty8point6.connectfour.entities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ninty8point6.connectfour.exceptions.CustomException;
import com.ninty8point6.connectfour.exceptions.InvalidObjectException;
import com.ninty8point6.connectfour.models.GameInfo;
import com.ninty8point6.connectfour.models.GameRequest;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * The type Game.
 */
@Entity
@Table(name = "game")
public class Game extends AbstractEntity{

    @Id
    private String token;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinColumn(name = "players")
    private List<Player> players;

    @Column
    private int columns;

    @Column
    private int rows;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "state")
    private GameState state;


    @OneToMany(
            mappedBy = "game",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Move> moves = new ArrayList<>();


    /**
     * Instantiates a new Game.
     */
    public Game() {
        this.state = new GameState();
    }

    /**
     * Create game game.
     *
     * @param gameRequest the game request
     * @return the game
     */
    public static Game createGame(GameRequest gameRequest) {

        Game game = new Game();
        String uuidToken = UUID.randomUUID().toString();
        game.setToken(uuidToken);


        ArrayList<Player> players = new ArrayList<>();
        gameRequest
                .getPlayers()
                .forEach(playerName -> players.add(new Player(playerName)));

        game.setPlayers(players);

        game.setColumns(gameRequest.getColumns());
        game.setRows(gameRequest.getColumns());

        return game;
    }

    /**
     * Gets players.
     *
     * @return the players
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * Sets players.
     *
     * @param players the players
     */
    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    /**
     * Gets columns.
     *
     * @return the columns
     */
    public int getColumns() {
        return columns;
    }

    /**
     * Sets columns.
     *
     * @param columns the columns
     */
    public void setColumns(int columns) {
        this.columns = columns;
    }

    /**
     * Gets rows.
     *
     * @return the rows
     */
    public int getRows() {
        return rows;
    }

    /**
     * Sets rows.
     *
     * @param rows the rows
     */
    public void setRows(int rows) {
        this.rows = rows;
    }

    /**
     * Gets token.
     *
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets token.
     *
     * @param token the token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Gets state.
     *
     * @return the state
     */
    public GameState getState() {
        return state;
    }

    /**
     * Sets state.
     *
     * @param state the state
     */
    public void setState(GameState state) {
        this.state = state;
    }

    /**
     * Create info game info.
     *
     * @return the game info
     */
    public GameInfo createInfo() {
        return new GameInfo(getToken());
    }

    /**
     * Gets moves.
     *
     * @return the moves
     */
    public List<Move> getMoves() {
        return moves;
    }

    /**
     * Sets moves.
     *
     * @param moves the moves
     */
    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    /**
     * To json string string.
     *
     * @return the string
     */
    public String toJsonString() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw InvalidObjectException.getInstance("Problem converting Game to JSON");
        }
    }
}
