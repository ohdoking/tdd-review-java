package com.ohdoking.tddjava.ch03tictactoe;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;


/**
 * TicTacToeSpec
 *
 * @author ohdoking
 *
 * Requirement 1
 *
 * A piece can be placed on any empty space of a 3×3 board.
 *
 * We can split this requirement into three tests:
 * • When a piece is placed anywhere outside the X axis, then RuntimeException is thrown.
 * • When a piece is placed anywhere outside the Y axis, then RuntimeException is thrown.
 * • When a piece is placed on an occupied space, then RuntimeException is thrown.
 *
 * Requirement 2
 *
 * There should be a way to find out which player should play next.
 *
 * We can split this requirement into three tests:
 * • The first turn should be played by played X
 * • If the last turn was played by X, then the next turn should be played by O
 * • If the last turn was played by O, then the next turn should be played by X
 */
public class TicTacToeSpec {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private TicTacToe ticTacToe;

    @Before
    public final void before(){
        ticTacToe = new TicTacToe();
    }

    /**
     * When a piece is placed anywhere outside the X axis, then RuntimeException is thrown.
     */
    @Test
    public void whenXOutsideBoardThenRuntimeException()
    {
        exception.expect(RuntimeException.class);
        ticTacToe.play(5, 2);
    }

    /**
     * When a piece is placed anywhere outside the Y axis, then RuntimeException is thrown.
     */
    @Test
    public void whenYOutsideBoardThenRuntimeException() {
        exception.expect(RuntimeException.class);
        ticTacToe.play(2, 5);
    }

    /**
     * When a piece is placed on an occupied space, then RuntimeException is thrown.
     */
    @Test
    public void whenOccupiedThenRuntimeException() {
        ticTacToe.play(2, 1);
        exception.expect(RuntimeException.class);
        ticTacToe.play(2, 1);
    }

    /**
     * The first turn should be played by played X
     */
    @Test
    public void givenFirstTurnWhenNextPlayerThenX() {
        assertEquals('X', ticTacToe.nextPlayer());
    }

    /**
     * If the last turn was played by X, then the next turn should be played by O
     */
    @Test
    public void givenLastTurnWasXWhenNextPlayerThenO()
    {
        ticTacToe.play(1, 1);
        assertEquals('O', ticTacToe.nextPlayer());
    }

    /**
     * If the last turn was played by O, then the next turn should be played by X
     */


}
