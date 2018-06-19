package com.ninty8point6.connectfour.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Wrong turn exception.
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class WrongTurnException extends CustomException {
    /**
     * Instantiates a new Wrong turn exception.
     *
     * @param errorMessage the error message
     * @param t            the t
     */
    WrongTurnException(String errorMessage, Throwable t) {
		super(errorMessage, t);
	}

    /**
     * Instantiates a new Wrong turn exception.
     *
     * @param errorMessage the error message
     */
    WrongTurnException(String errorMessage) {
		super(errorMessage);
	}
}
