package com.ninty8point6.connectfour.exceptions;


/**
 * The type Custom exception.
 */
public abstract class CustomException extends RuntimeException {

    /**
     * Instantiates a new Custom exception.
     *
     * @param errorMessage the error message
     * @param t            the t
     */
    CustomException(String errorMessage, Throwable t) {
        super(errorMessage, t);
    }

    /**
     * Instantiates a new Custom exception.
     *
     * @param errorMessage the error message
     */
    CustomException(String errorMessage) {
        super(errorMessage);
    }
}