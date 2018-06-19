package com.ninty8point6.connectfour.repositories;

import com.ninty8point6.connectfour.entities.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Player repo.
 */
@Repository
public interface PlayerRepo extends CrudRepository<Player,String> {

}
