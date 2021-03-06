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
 *
 * Requirement 3
 *
 * A player wins by being the first to connect a line of friendly pieces from one side or corner of the board to the other.
 *
 * Requirement 4
 *
 * The result is a draw when all the boxes are filled.
 *
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
    @Test
    public void givenLastTurnWasOWhenNextPlayerThenX(){
        ticTacToe.play(1, 1);
        ticTacToe.play(1, 2);
        assertEquals('X', ticTacToe.nextPlayer());
    }

    /**
     * A player wins by being the first to connect a line of friendly pieces from one side or corner of the board to the other.
     */
    @Test
    public void whenPlayThenNoWinner()
    {
        String actual = ticTacToe.play(1,1);
        assertEquals("No winner", actual);
    }

    @Test
    public void whenPlayAndWholeHorizontalLineThenWinner() {
        ticTacToe.play(1, 1); // X
        ticTacToe.play(1, 2); // O
        ticTacToe.play(2, 1); // X
        ticTacToe.play(2, 2); // O
        String actual = ticTacToe.play(3, 1); // X
        assertEquals("X is the winner", actual);
    }

    @Test
    public void whenPlayAndWholeVerticalLineThenWinner() {
        ticTacToe.play(2, 1); // X
        ticTacToe.play(1, 1); // O
        ticTacToe.play(3, 1); // X
        ticTacToe.play(1, 2); // O
        ticTacToe.play(2, 2); // X
        String actual = ticTacToe.play(1, 3); // O
        assertEquals("O is the winner", actual);
    }

    @Test
    public void whenPlayAndTopBottomDiagonalLineThenWinner() {
        ticTacToe.play(1, 1); // X
        ticTacToe.play(1, 2); // O
        ticTacToe.play(2, 2); // X
        ticTacToe.play(1, 3); // O
        String actual = ticTacToe.play(3, 3); // O
        assertEquals("X is the winner", actual);
    }

    @Test
    public void whenPlayAndBottomTopDiagonalLineThenWinner() {
        ticTacToe.play(1, 3); // X
        ticTacToe.play(1, 1); // O
        ticTacToe.play(2, 2); // X
        ticTacToe.play(1, 2); // O
        String actual = ticTacToe.play(3, 1); // O
        assertEquals("X is the winner", actual);
    }

    /**
     * The result is a draw when all the boxes are filled.
     */
    @Test
    public void whenAllBoxesAreFilledThenDraw() {
        ticTacToe.play(1, 1);
        ticTacToe.play(1, 2);
        ticTacToe.play(1, 3);
        ticTacToe.play(2, 1);
        ticTacToe.play(2, 3);
        ticTacToe.play(2, 2);
        ticTacToe.play(3, 1);
        ticTacToe.play(3, 3);
        String actual = ticTacToe.play(3, 2);
        assertEquals("The result is draw", actual);
    }

}
