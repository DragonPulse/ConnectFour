package com.ninty8point6.connectfour.entities;

import com.ninty8point6.connectfour.constants.ActionType;
import com.ninty8point6.connectfour.models.MoveCreatedResponse;

import javax.persistence.*;

/**
 * The type Move.
 */
@Entity
public class Move extends AbstractEntity{

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="type")
    private String actionType;

    @Column(name="col")
    private String column;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "playerId")
    private Player player;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "token")
    private Game game;

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets action type.
     *
     * @return the action type
     */
    public String getActionType() {
        return actionType;
    }

    /**
     * Sets action type.
     *
     * @param actionType the action type
     */
    public void setActionType(String actionType) {
        this.actionType = actionType;
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
    public Player getPlayer() {
        return player;
    }

    /**
     * Sets player.
     *
     * @param player the player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Gets game.
     *
     * @return the game
     */
    public Game getGame() {
        return game;
    }

    /**
     * Sets game.
     *
     * @param game the game
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * Create move move.
     *
     * @param game   the game
     * @param player the player
     * @param column the column
     * @return the move
     */
    public static Move createMove(Game game, Player player, String column) {

        Move move = new Move();

        move.setActionType(ActionType.MOVE.getActionType());
        move.setColumn(column);

        move.setPlayer(player);
        move.setGame(game);


        return move;
    }

    /**
     * Quit move move.
     *
     * @param game   the game
     * @param player the player
     * @return the move
     */
    public static Move quitMove(Game game, Player player) {

        Move move = new Move();

        move.setActionType(ActionType.QUIT.getActionType());
        move.setPlayer(player);
        move.setGame(game);
        return move;
    }

    /**
     * Build create response move created response.
     *
     * @return the move created response
     */
    public MoveCreatedResponse buildCreateResponse(){
        return new MoveCreatedResponse(this.getGame().getToken()+"/moves/"+this.getId());
    }
}
