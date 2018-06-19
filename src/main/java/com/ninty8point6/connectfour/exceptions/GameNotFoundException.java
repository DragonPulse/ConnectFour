package com.ninty8point6.connectfour.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Game not found exception.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class GameNotFoundException extends CustomException {

    /**
     * Instantiates a new Game not found exception.
     *
     * @param errorMessage the error message
     * @param t            the t
     */
    GameNotFoundException(String errorMessage, Throwable t) {
        super(errorMessage, t);
    }

    /**
     * Instantiates a new Game not found exception.
     *
     * @param errorMessage the error message
     */
    GameNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
