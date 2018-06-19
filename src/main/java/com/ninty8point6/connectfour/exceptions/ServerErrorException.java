package com.ninty8point6.connectfour.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Server error exception.
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ServerErrorException extends CustomException {
    /**
     * Instantiates a new Server error exception.
     *
     * @param message the message
     */
    public ServerErrorException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Server error exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public ServerErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
