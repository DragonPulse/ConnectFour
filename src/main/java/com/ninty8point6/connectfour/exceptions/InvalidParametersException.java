package com.ninty8point6.connectfour.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Invalid parameters exception.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidParametersException extends CustomException {
    /**
     * Instantiates a new Invalid parameters exception.
     *
     * @param message the message
     */
    public InvalidParametersException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Invalid parameters exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public InvalidParametersException(String message, Throwable cause) {
        super(message, cause);
    }
}
