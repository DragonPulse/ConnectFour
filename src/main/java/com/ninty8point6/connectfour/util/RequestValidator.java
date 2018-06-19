package com.ninty8point6.connectfour.util;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.ninty8point6.connectfour.constants.AppConstant;
import com.ninty8point6.connectfour.constants.State;
import com.ninty8point6.connectfour.entities.Game;
import com.ninty8point6.connectfour.entities.Move;
import com.ninty8point6.connectfour.entities.Player;
import com.ninty8point6.connectfour.exceptions.CustomException;
import com.ninty8point6.connectfour.exceptions.CustomExceptionFactory;
import com.ninty8point6.connectfour.exceptions.CustomStatusCode;
import com.ninty8point6.connectfour.models.GameRequest;

/**
 * The type Request validator.
 */
@Component
public class RequestValidator {


    /**
     * Validate game not null.
     *
     * @param game the game
     */
    public void validateGameNotNull(Game game) {
		if (game != null) {
			return;
		}
		throw CustomExceptionFactory.getException(CustomStatusCode.NOT_FOUND, "Game not found.");
	}

    /**
     * Validate player not null.
     *
     * @param player the player
     */
    public void validatePlayerNotNull(Player player) {
		if (player != null) {
			return;
		}
		throw CustomExceptionFactory.getException(CustomStatusCode.NOT_FOUND, "Player not found.");
	}

    /**
     * Validate game not null and game is done.
     *
     * @param game the game
     */
    public void validateGameNotNullAndGameIsDone(Game game) {
		if ((game != null) && (!game.getState().getState().equals(State.DONE.getState()) || !game.getState().getState().equals(State.QUIT.getState()))) {
			return;
		}
		throw CustomExceptionFactory.getException(CustomStatusCode.ALREADY_DONE, "GAME Not Found or Already in Done Status.");
	}

    /**
     * Validate request parameter.
     *
     * @param param the param
     */
    public void validateRequestParameter(String param) {
		if (!StringUtils.isEmpty(param)) {
			return;
		}
		throw CustomExceptionFactory.getException(CustomStatusCode.MALFORMED_REQUEST, "Malformed Request");
	}

    /**
     * Validate create game request.
     *
     * @param gameRequest the game request
     */
    public void validateCreateGameRequest(GameRequest gameRequest) {
		try {
			validatePlayerNames(gameRequest.getPlayers());
			validateColumnCount(gameRequest.getColumns());
			validateRowCount(gameRequest.getRows());
		} catch (CustomException ce) {
			throw ce;
		} catch (Exception e) {
			throw CustomExceptionFactory.getException(CustomStatusCode.INVALID_PARAMETERS, e.getMessage(), e);
		}

	}

    /**
     * Validate game moves not null.
     *
     * @param obj the obj
     */
    public void validateGameMovesNotNull(Object obj) {
		try {
			if (obj != null) {
				return;
			}
			throw CustomExceptionFactory.getException(CustomStatusCode.NOT_FOUND, "Game/Moves Found Exception");
		} catch (CustomException ce) {

		}
	}

	private void validateColumnCount(Integer column) {
		if (column != null && AppConstant.COLUMN_VALUE == column) {
			return;
		}
		throw CustomExceptionFactory.getException(CustomStatusCode.MALFORMED_REQUEST, "Malformed Request");

	}

	private void validateRowCount(Integer row) {
		if (row != null && AppConstant.ROW_VALUE == row) {
			return;
		}
		throw CustomExceptionFactory.getException(CustomStatusCode.MALFORMED_REQUEST, "Malformed Request");

	}

	private void validatePlayerNames(List<String> playerNames) {
		if (playerNames.size() != 2) {
			throw CustomExceptionFactory.getException(CustomStatusCode.MALFORMED_REQUEST, "Malformed Request");
		}
		for (String player : playerNames) {
			if (!StringUtils.isEmpty(player)) {
				return;
			}
		}
		throw CustomExceptionFactory.getException(CustomStatusCode.MALFORMED_REQUEST, "Malformed Request");

	}

    /**
     * Validate move not null.
     *
     * @param move the move
     */
    public void validateMoveNotNull(Optional<Move> move) {
		if(move.isPresent()) {
			return;
		}
		throw CustomExceptionFactory.getException(CustomStatusCode.NOT_FOUND, "Moves Not Found ");
	}

}
