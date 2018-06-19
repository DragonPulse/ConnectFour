package com.ninty8point6.connectfour.repositories;

import com.ninty8point6.connectfour.entities.Game;
import org.springframework.data.repository.CrudRepository;

/**
 * The interface Game repo.
 */
public interface GameRepo extends CrudRepository<Game, String> {

    /**
     * Find by token game.
     *
     * @param token the token
     * @return the game
     */
    public Game findByToken(String token);
}