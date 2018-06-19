package com.ninty8point6.connectfour.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ninty8point6.connectfour.constants.State;
import com.ninty8point6.connectfour.entities.Game;
import com.ninty8point6.connectfour.entities.Move;
import com.ninty8point6.connectfour.entities.Player;
import com.ninty8point6.connectfour.exceptions.CustomException;
import com.ninty8point6.connectfour.exceptions.CustomExceptionFactory;
import com.ninty8point6.connectfour.exceptions.CustomStatusCode;
import com.ninty8point6.connectfour.models.MoveCreatedResponse;
import com.ninty8point6.connectfour.models.MoveInfo;
import com.ninty8point6.connectfour.models.MoveRequest;
import com.ninty8point6.connectfour.models.MoveViewModel;
import com.ninty8point6.connectfour.repositories.GameRepo;
import com.ninty8point6.connectfour.repositories.MoveRepo;
import com.ninty8point6.connectfour.repositories.PlayerRepo;
import com.ninty8point6.connectfour.util.RequestValidator;

/**
 * The type Move service.
 */
@Service
public class MoveService {

    /**
     * The Move repo.
     */
    @Autowired
	MoveRepo moveRepo;

    /**
     * The Game repo.
     */
    @Autowired
	GameRepo gameRepo;

    /**
     * The Player repo.
     */
    @Autowired
	PlayerRepo playerRepo;

    /**
     * The Game state service.
     */
    @Autowired
	GameStateService gameStateService;

    /**
     * The Request validator.
     */
    @Autowired
	RequestValidator requestValidator;

    /**
     * Gets all moves by player.
     *
     * @param playerId the player id
     * @return the all moves by player
     */
    public List<Move> getAllMovesByPlayer(Long playerId) {
		return null;
	}

    /**
     * Gets all moves by game id.
     *
     * @param token the token
     * @param start the start
     * @param until the until
     * @return the all moves by game id
     */
    public MoveViewModel getAllMovesByGameId(String token, Integer start, Integer until) {
		try {
			Game game = gameRepo.findByToken(token);
			if (game == null) {
				throw CustomExceptionFactory.getException(CustomStatusCode.NOT_FOUND, "Game not FOund");
			}
			return MoveViewModel.createView(moveRepo.findAllByGameToken(token), start, until);
		} catch (Exception e) {
			throw CustomExceptionFactory.getException(CustomStatusCode.MALFORMED_REQUEST, e.getMessage());
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
    public MoveCreatedResponse createMove(String gameId, String playerId, MoveRequest moveRequest) {
		try {
			Game game = gameRepo.findByToken(gameId);
			Optional<Player> player = playerRepo.findById(playerId);
			requestValidator.validateGameNotNull(game);
			requestValidator.validatePlayerNotNull(player.get());
			validateMoves(game, player, moveRequest);
			Move move = Move.createMove(game, player.get(), moveRequest.getColumn());
			moveRepo.save(move);
			return move.buildCreateResponse();
		} catch (CustomException ce) {
			ce.printStackTrace();
			throw ce;
		} catch (Exception e) {
			throw CustomExceptionFactory.getException(CustomStatusCode.MALFORMED_REQUEST, e.getMessage());
		}
	}

	private Map<String, List<Move>> buildMap(List<Move> movesList) {
		Map<String, List<Move>> mapByColumn = new HashMap<>();
		for (Move move : movesList) {
			if (mapByColumn.containsKey(move.getColumn())) {
				List<Move> moves = mapByColumn.get(move.getColumn());
				moves.add(move);
				mapByColumn.put(move.getColumn(), moves);
			} else {
				List<Move> moves = new ArrayList<>();
				moves.add(move);
				mapByColumn.put(move.getColumn(), moves);
			}
		}
		return mapByColumn;
	}

    /**
     * Gets moves.
     *
     * @param col    the col
     * @param gameId the game id
     * @return the moves
     */
    public List<Move> getMoves(String col, String gameId) {
		// List<Move> movesList = moveRepo.findAllByColumnAndGameTokenOrderByIdDesc(col,
		// gameId);
		List<Move> movesList = moveRepo.findAllByGameTokenOrderByIdDesc(gameId);
		return movesList;

	}

    /**
     * Gets move info by game and move id.
     *
     * @param gameId the game id
     * @param moveId the move id
     * @return the move info by game and move id
     */
    public MoveInfo getMoveInfoByGameAndMoveId(String gameId, Long moveId) {
		try {
			requestValidator.validateGameNotNull(gameRepo.findByToken(gameId));
			requestValidator.validateMoveNotNull(moveRepo.findById(moveId));
			return MoveInfo.createMoveInfo(moveRepo.findByGameTokenAndId(gameId, moveId));
		} catch (CustomException ce) {
			throw ce;
		}catch (Exception e) {
			throw CustomExceptionFactory.getException(CustomStatusCode.MALFORMED_REQUEST, e.getMessage());
		}
	}

    /**
     * Quit from playing.
     *
     * @param gameId   the game id
     * @param playerId the player id
     */
    public void quitFromPlaying(String gameId, String playerId) {
		try {
			Game game = gameRepo.findByToken(gameId);
			Optional<Player> player = playerRepo.findById(playerId);
			requestValidator.validateGameNotNullAndGameIsDone(game);
			if(player.isPresent()) {
				requestValidator.validatePlayerNotNull(player.get());
			}else {
				throw CustomExceptionFactory.getException(CustomStatusCode.NOT_FOUND, "Player Not FOund");
			}
			
			Move move = Move.quitMove(game, player.get());
			moveRepo.save(move);
			gameStateService.updateGameState(game.getState(), State.QUIT);

		} catch (CustomException ce) {
			throw ce;
		}catch (Exception e) {
			throw CustomExceptionFactory.getException(CustomStatusCode.MALFORMED_REQUEST, e.getMessage());
		}
	}

	private void validateMoves(Game game, Optional<Player> player, MoveRequest moveRequest) {
		// TODO Auto-generated method stub
		List<Move> movesList = getMoves(moveRequest.getColumn(), game.getToken());
		Map<String, List<Move>> mapOfMovesByColumn = buildMap(movesList);
		requestValidator.validatePlayerNotNull(player.get());
		requestValidator.validateGameNotNull(game);
		// Validate if the User belongs to this game
		if (!game.getPlayers().stream().anyMatch(t -> t.getId().equals(player.get().getId()))) {
			throw CustomExceptionFactory.getException(CustomStatusCode.NOT_FOUND,
					"Game not found or Player not Part of this Game.");
		}
		// Validate if its Player Turn
		if (!movesList.isEmpty() && movesList.get(0).getPlayer().getId().equals(player.get().getId())) {
			throw CustomExceptionFactory.getException(CustomStatusCode.WRONG_TURN, "Its not Your Turn");
		}
		// Validate id the respective column is already full
		if (mapOfMovesByColumn.containsKey(moveRequest.getColumn())
				&& mapOfMovesByColumn.get(moveRequest.getColumn()).size() == game.getColumns()) {
			throw CustomExceptionFactory.getException(CustomStatusCode.MALFORMED_REQUEST,
					"Column is Full, Cannot place the coin");
		}

	}
}
