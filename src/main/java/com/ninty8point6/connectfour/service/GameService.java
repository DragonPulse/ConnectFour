package com.ninty8point6.connectfour.service;

import com.ninty8point6.connectfour.entities.*;
import com.ninty8point6.connectfour.exceptions.CustomExceptionFactory;
import com.ninty8point6.connectfour.exceptions.CustomStatusCode;
import com.ninty8point6.connectfour.models.GameInfo;
import com.ninty8point6.connectfour.models.GameRequest;
import com.ninty8point6.connectfour.models.GameStatusView;
import com.ninty8point6.connectfour.models.GamesInServer;
import com.ninty8point6.connectfour.repositories.GameRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The type Game service.
 */
@Service
public class GameService {

    @Autowired
    private GameRepo gameRepo;

    /**
     * Create game game info.
     *
     * @param gameRequest the game request
     * @return the game info
     */
    public GameInfo createGame(GameRequest gameRequest) {
        Game game = Game.createGame(gameRequest);
        gameRepo.save(game);
        GameInfo info = game.createInfo();
        return info;
    }

    /**
     * Gets all games.
     *
     * @return the all games
     */
    public GamesInServer getAllGames() {
        List<String> gameIds = new ArrayList<>();

        gameRepo
                .findAll()
                .forEach(game -> gameIds.add(game.getToken()));
        return new GamesInServer(gameIds);

    }

    /**
     * Gets game status.
     *
     * @param gameId the game id
     * @return the game status
     */
    public GameStatusView getGameStatus(String gameId) {
    	try {
    		 Optional<Game> gameById = gameRepo.findById(gameId);
    	        Game game = gameById.get();
    	        GameStatusView view = GameStatusView.createView(game);
    	        return view;
    	}catch(Exception e) {
    		throw CustomExceptionFactory.getException(CustomStatusCode.NOT_FOUND, "Game not FOund");
    	}
       
    }

    /**
     * Gets game repo.
     *
     * @return the game repo
     */
    public GameRepo getGameRepo() {
        return gameRepo;
    }

    /**
     * Sets game repo.
     *
     * @param gameRepo the game repo
     */
    public void setGameRepo(GameRepo gameRepo) {
        this.gameRepo = gameRepo;
    }
}
