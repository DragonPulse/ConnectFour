package com.ninty8point6.connectfour.exceptions;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * The enum Custom status code.
 */
public enum CustomStatusCode {

    /**
     * The Successful.
     */
    SUCCESSFUL(200, "Request processed successful", StatusCode.SUCCESSFUL),

    /**
     * The Invalid parameters.
     */
    INVALID_PARAMETERS(501, "Invalid  request parameters",StatusCode.INVALID_PARAMETERS),

    /**
     * The Not found.
     */
    NOT_FOUND(404,"Game Not Found",StatusCode.SERVER_ERROR),

    /**
     * The Malformed request.
     */
    MALFORMED_REQUEST(400,"Malformed request",StatusCode.MALFORMED_REQUEST),

    /**
     * The Wrong turn.
     */
    WRONG_TURN(409,"Wrong Turn",StatusCode.WRONG_TURN),

    /**
     * The Already done.
     */
    ALREADY_DONE(410,"Game already Done",StatusCode.WRONG_TURN),

    /**
     * The Server error.
     */
    SERVER_ERROR(500, "Error in Game server", StatusCode.SERVER_ERROR);

    private int statusCode;
    private String description;
    private StatusCode statusCodeObj;

    CustomStatusCode(int statusCode, String description, StatusCode statusCodeObj) {
        this.statusCode = statusCode;
        this.description = description;
        this.statusCodeObj = statusCodeObj;
    }

    /**
     * For value custom status code.
     *
     * @param errorCode the error code
     * @return the custom status code
     */
    @JsonCreator
    public static CustomStatusCode forValue(int errorCode) {
        for (CustomStatusCode CustomStatusCode : CustomStatusCode.values()) {
            if (CustomStatusCode.statusCode == errorCode)
                return CustomStatusCode;
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

    /**
     * Gets status code obj.
     *
     * @return the status code obj
     */
    public StatusCode getStatusCodeObj() {
        return statusCodeObj;
    }

    @Override
    public String toString() {
        return statusCode + ":" + description;
    }
}



