package com.ninty8point6.connectfour.exceptions;



import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * The enum Status code.
 */
public enum StatusCode {
    /**
     * The Successful.
     */
// 200 	(nonstandard success response, see rfc876)
    SUCCESSFUL(200, "Request processed successful"),


    /**
     * The Server error.
     */
// 451 	Requested action aborted: local error in processing
    SERVER_ERROR(451, "Error in Server"),

    /**
     * The Not found.
     */
    NOT_FOUND(404,"GAME not FOund"),

    /**
     * Malformed request status code.
     */
    MALFORMED_REQUEST(400,"MALFORMED_REQUEST"),

    /**
     * The Invalid parameters.
     */
// 501 	Syntax error in parameters or arguments
    INVALID_PARAMETERS(501, "Invalid  request parameters"),

    /**
     * The Already done.
     */
//410 Game Alreayd DOne
    ALREADY_DONE(410,"Game Already DOne"),

    /**
     * The Wrong turn.
     */
//WRONG_TURN
	WRONG_TURN(409,"Not a Valid Turn");


    private int statusCode;
    private String description;

    StatusCode(int statusCode, String description) {
        this.statusCode = statusCode;
        this.description = description;
    }

    /**
     * For value status code.
     *
     * @param errorCode the error code
     * @return the status code
     */
    @JsonCreator
    public static StatusCode forValue(int errorCode) {
        for (StatusCode csmHttpStatusCode : StatusCode.values()) {
            if (csmHttpStatusCode.statusCode == errorCode)
                return csmHttpStatusCode;
        }
        return SERVER_ERROR;
    }

    /**
     * Gets status code.
     *
     * @return the status code
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return statusCode + ":" + description;
    }
}


