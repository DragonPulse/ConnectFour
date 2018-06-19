package com.ninty8point6.connectfour.models;

import java.util.ArrayList;
import java.util.List;

import com.ninty8point6.connectfour.entities.Move;

/**
 * The type Move view model.
 */
public class MoveViewModel {

     private List<MoveView> moves = new ArrayList<>();

    /**
     * Create view move view model.
     *
     * @param movesList the moves list
     * @param start     the start
     * @param until     the until
     * @return the move view model
     */
    public static MoveViewModel createView(List<Move> movesList,Integer start, Integer until) {
        MoveViewModel moveViewModel = new MoveViewModel();

        List<MoveView> moveviewsList = new ArrayList<>();
        
        if((start!=null && start<=movesList.size()) 
        		&& 
        		(until!=null && until>=start && until <=movesList.size())) {
        	movesList = movesList.subList(start, until);
        }
        
        movesList.forEach(
                move -> moveviewsList.add(
                        new MoveView(move.getActionType(),
                                move.getColumn(),
                                move.getPlayer().getName())));

        moveViewModel.setMoves(moveviewsList);
        return moveViewModel;
    }

    /**
     * Gets moves.
     *
     * @return the moves
     */
    public List<MoveView> getMoves() {
        return moves;
    }

    /**
     * Sets moves.
     *
     * @param moves the moves
     */
    public void setMoves(List<MoveView> moves) {
        this.moves = moves;
    }
}
