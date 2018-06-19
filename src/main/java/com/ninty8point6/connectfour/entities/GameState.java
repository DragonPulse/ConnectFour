package com.ninty8point6.connectfour.entities;

import javax.persistence.*;

import com.ninty8point6.connectfour.constants.State;


/**
 * The type Game state.
 */
@Entity
@Table(name = "gamestate")
public class GameState extends AbstractEntity{

    @Id
    @GeneratedValue
    private Long id;

    private String state;

    private String winnerId;

    /**
     * Instantiates a new Game state.
     */
    public GameState() {
        state = State.IN_PROGRESS.getState();
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
     * Gets winner id.
     *
     * @return the winner id
     */
    public String getWinnerId() {
        return winnerId;
    }

    /**
     * Sets winner id.
     *
     * @param winnerId the winner id
     */
    public void setWinnerId(String winnerId) {
        this.winnerId = winnerId;
    }

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


}

