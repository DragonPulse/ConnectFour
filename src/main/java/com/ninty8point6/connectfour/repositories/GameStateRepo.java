package com.ninty8point6.connectfour.repositories;

import com.ninty8point6.connectfour.entities.GameState;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Game state repo.
 */
@Repository
public interface GameStateRepo extends CrudRepository<GameState,Long> {

}
