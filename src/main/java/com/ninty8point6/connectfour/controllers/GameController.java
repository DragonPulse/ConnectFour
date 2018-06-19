package com.ninty8point6.connectfour.controllers;

import java.util.Optional;

import com.ninty8point6.connectfour.logger.LogExecutionTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ninty8point6.connectfour.exceptions.CustomException;
import com.ninty8point6.connectfour.exceptions.CustomExceptionFactory;
import com.ninty8point6.connectfour.exceptions.CustomStatusCode;
import com.ninty8point6.connectfour.models.GameInfo;
import com.ninty8point6.connectfour.models.GameRequest;
import com.ninty8point6.connectfour.models.GameStatusView;
import com.ninty8point6.connectfour.models.GamesInServer;
import com.ninty8point6.connectfour.models.MoveCreatedResponse;
import com.ninty8point6.connectfour.models.MoveInfo;
import com.ninty8point6.connectfour.models.MoveRequest;
import com.ninty8point6.connectfour.models.MoveViewModel;
import com.ninty8point6.connectfour.service.GameService;
import com.ninty8point6.connectfour.service.MoveService;
import com.ninty8point6.connectfour.util.RequestValidator;

import io.swagger.annotations.ApiOperation;

/**
 * The type Game controller.
 */
@RestController
class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private MoveService moveService;
    
    @Autowired
    private RequestValidator requestValidator;

    /**
     * Create game game info.
     *
     * @param gameRequest the game request
     * @return the game info
     */
    @PostMapping(value = "/drop_token")
    @ApiOperation(value = "Creates the Game.", notes = "Returns Game info - unique Id .")
    @LogExecutionTime
    public GameInfo createGame(@RequestBody GameRequest gameRequest) {
    	try {
    		requestValidator.validateCreateGameRequest(gameRequest);
    		return gameService.createGame(gameRequest);
    	}catch(CustomException ce) {
    		throw ce;
    	}catch(Exception e) {
    		throw  CustomExceptionFactory.getException(CustomStatusCode.MALFORMED_REQUEST, e.getMessage());
    	}
        
    }

    /**
     * Gets list of games game.
     *
     * @return the list of games game
     */
    @GetMapping(value = "/drop_token")
    @ApiOperation(value = "Get the list of games.", notes = "Returns all Game list .")
    @LogExecutionTime
    public GamesInServer getListOfGames() {
        return gameService.getAllGames();
    }

    /**
     * Gets status of games game.
     *
     * @param gameId the game id
     * @return the status of games game
     */
    @GetMapping(value = "/drop_token/{gameId}")
    @ApiOperation(value = "Get Game status.", notes = "Returns status of game.")
    @LogExecutionTime
    public GameStatusView getStatusOfGame(@PathVariable String gameId) {
    	try {
    		requestValidator.validateRequestParameter(gameId);
    		return gameService.getGameStatus(gameId);
    	}catch(CustomException ce) {
    		throw ce;
    	}catch(Exception e) {
    		throw  CustomExceptionFactory.getException(CustomStatusCode.MALFORMED_REQUEST, e.getMessage());
    	}
        
    }


    /**
     * Gets configs test object by i.
     *
     * @param gameId the game id
     * @param start  the start
     * @param until  the until
     * @return the configs test object by i
     */
    @GetMapping(value = { "/drop_token/{gameId}/moves" })
    @ApiOperation(value = "Get All Moves by Game token.", notes = "Returns List of Moves.")
    @LogExecutionTime
    public @ResponseBody
    MoveViewModel getConfigsTestObjectByI(@PathVariable("gameId") String gameId,
    		 @RequestParam("start") Optional<Integer> start,
             @RequestParam("until") Optional<Integer> until){
    	try {
    		requestValidator.validateRequestParameter(gameId);
    		return moveService.getAllMovesByGameId(gameId,start.get(),until.get());
    	}catch(Exception e) {
    		throw  CustomExceptionFactory.getException(CustomStatusCode.MALFORMED_REQUEST, e.getMessage());
    	}
       
    }


    /**
     * Create move move created response.
     *
     * @param gameId      the game id
     * @param playerId    the player id
     * @param moveRequest the move request
     * @return the move created response
     */
    @PostMapping(value = "/drop_token/{gameId}/{playerId}")
    @ApiOperation(value = "Creates the Move.", notes = "Returns Move info.")
    @LogExecutionTime
    public MoveCreatedResponse createMove(@PathVariable("gameId") String gameId,@PathVariable("playerId") String playerId,@RequestBody MoveRequest moveRequest) {
        return moveService.createMove(gameId,playerId,moveRequest);
    }


    /**
     * Gets move by game and move id.
     *
     * @param gameId the game id
     * @param moveId the move id
     * @return the move by game and move id
     */
    @GetMapping(value = { "/drop_token/{gameId}/moves/{moveId}" })
    @ApiOperation(value = "Moves details by Game and move number.", notes = "Returns details of Move.")
    @LogExecutionTime
    public @ResponseBody
    MoveInfo getMoveByGameAndMoveId(@PathVariable("gameId") String gameId,@PathVariable("moveId") Long moveId){
        return moveService.getMoveInfoByGameAndMoveId(gameId,moveId);
    }

    /**
     * Quit game.
     *
     * @param gameId   the game id
     * @param playerId the player id
     */
    @DeleteMapping("/drop_token/{gameId}/{playerId}")
    @ApiOperation(value = "Player Quits the Game.", notes = "Player Quits the Game.")
    @LogExecutionTime
    public void quitGame(@PathVariable("gameId") String gameId,@PathVariable("playerId") String playerId){
        moveService.quitFromPlaying(gameId,playerId);
    }
}