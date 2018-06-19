package com.ninty8point6.connectfour.models;

/**
 * The type Move created response.
 */
public class MoveCreatedResponse {
    private String move;

    /**
     * Instantiates a new Move created response.
     *
     * @param move the move
     */
    public MoveCreatedResponse(String move) {
        this.move = move;
    }

    /**
     * Gets move.
     *
     * @return the move
     */
    public String getMove() {
        return move;
    }

    /**
     * Sets move.
     *
     * @param move the move
     */
    public void setMove(String move) {
        this.move = move;
    }
}
