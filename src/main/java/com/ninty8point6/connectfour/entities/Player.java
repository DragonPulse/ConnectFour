package com.ninty8point6.connectfour.entities;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;


/**
 * The type Player.
 */
@Entity
@Table(name = "player")
public class Player extends AbstractEntity{

    @Id
    private String id;
    private String name;

    /**
     * Instantiates a new Player.
     */
    public Player() {
        this.id = getNextId();
    }

    /**
     * Instantiates a new Player.
     *
     * @param name the name
     */
    @JsonCreator
    public Player(String name) {
        this.id = getNextId();
        this.name = name;
    }

    private String getNextId() {
        return UUID.randomUUID().toString();
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
