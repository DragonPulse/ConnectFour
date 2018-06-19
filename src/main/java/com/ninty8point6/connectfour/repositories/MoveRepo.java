package com.ninty8point6.connectfour.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ninty8point6.connectfour.entities.Move;

/**
 * The interface Move repo.
 */
public interface MoveRepo extends JpaRepository<Move,Long> {

    /**
     * Find all by game token list.
     *
     * @param token the token
     * @return the list
     */
    public List<Move> findAllByGameToken(String token);

    /**
     * Find by game token and id move.
     *
     * @param gameId the game id
     * @param moveId the move id
     * @return the move
     */
    public Move findByGameTokenAndId(String gameId,Long moveId);

    /**
     * Find all by column and game token order by id desc list.
     *
     * @param column the column
     * @param game   the game
     * @return the list
     */
    public List<Move> findAllByColumnAndGameTokenOrderByIdDesc(String column,String game);

    /**
     * Find all by game token order by id desc list.
     *
     * @param game the game
     * @return the list
     */
    public List<Move> findAllByGameTokenOrderByIdDesc(String game);

    /**
     * Find by id and game token move.
     *
     * @param id     the id
     * @param gameId the game id
     * @return the move
     */
    public Move findByIdAndGameToken(Long id,String gameId);

}
