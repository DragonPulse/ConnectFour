package com.ninty8point6.connectfour.exceptions;

/**
 * The type Invalid object exception.
 */
public class InvalidObjectException extends RuntimeException {

    /**
     * Instantiates a new Invalid object exception.
     *
     * @param message the message
     */
    public InvalidObjectException(String message) {
        super(message);
    }

    /**
     * Gets instance.
     *
     * @param message the message
     * @return the instance
     */
    public static InvalidObjectException getInstance(String message) {
        return new InvalidObjectException(message);
    }
}