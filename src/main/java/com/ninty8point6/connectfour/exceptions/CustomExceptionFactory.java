package com.ninty8point6.connectfour.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Custom exception factory.
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class CustomExceptionFactory {

    private CustomExceptionFactory() {
        // Can not create object of factory
    }

    /**
     * Gets exception.
     *
     * @param statusCode the status code
     * @param message    the message
     * @param throwable  the throwable
     * @return the exception
     */
    public static CustomException getException(CustomStatusCode statusCode, String message, Throwable throwable) {
        return createException(statusCode, message, throwable);
    }

    /**
     * Gets exception.
     *
     * @param statusCode the status code
     * @param message    the message
     * @return the exception
     */
    public static CustomException getException(CustomStatusCode statusCode, String message) {
        return createException(statusCode, message, null);
    }

    private static CustomException createException(CustomStatusCode statusCode, String message, Throwable throwable) {
        CustomStatusCode errorStatusCode = statusCode;

        if (errorStatusCode == null)
            errorStatusCode = CustomStatusCode.SERVER_ERROR;

        switch (errorStatusCode) {
            case INVALID_PARAMETERS:
                return new InvalidParametersException(errorStatusCode.getStatusCode() + " : " + message, throwable);
            case NOT_FOUND:
                return new GameNotFoundException(errorStatusCode.getStatusCode() + " : " + message, throwable);
            case MALFORMED_REQUEST:
            	return new InvalidParametersException(errorStatusCode.getStatusCode() + " : " + message, throwable);
            case WRONG_TURN:
            	return new WrongTurnException(errorStatusCode.getStatusCode() + " : " + message, throwable);
            default:
                return new ServerErrorException(errorStatusCode.getStatusCode() + " : " + message, throwable);
        }
    }
}