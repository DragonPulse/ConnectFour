package com.ninty8point6.connectfour.service;

import com.ninty8point6.connectfour.constants.State;
import com.ninty8point6.connectfour.entities.GameState;
import com.ninty8point6.connectfour.repositories.GameStateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Game state service.
 */
@Service
public class GameStateService {

    /**
     * The State repo.
     */
    @Autowired
    GameStateRepo stateRepo;

    /**
     * Update game state.
     *
     * @param gameState the game state
     * @param state     the state
     */
    public void updateGameState(GameState gameState, State state){

        gameState.setState(state.getState());
        stateRepo.save(gameState);
    }
}
