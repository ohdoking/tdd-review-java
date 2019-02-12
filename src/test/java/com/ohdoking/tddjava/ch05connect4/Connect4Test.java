package com.ohdoking.tddjava.ch05connect4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.Timeout;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 *
 * Requirements
 *
 * 1. The board is composed of seven columns and six rows, all positions are emptys.
 *
 * 2. Players introduce discs on the top of the columns. The introduced disc drops down the board if the column is empty. Future discs introduced in the same column will stack over the previous ones.
 * We can split this requirement into the following tests:
 * • When a disc is inserted into an empty column, its position is 0
 * • When a second disc is inserted into the same column, its position is 1
 * • When a disc is inserted into the board, the total number of discs increases
 * • When a disc is put outside the boundaries, a Runtime Exception is thrown
 * • When a disc is inserted in to a column and there's no room available for it, then a Runtime Exception is thrown
 *
 * 3. It is a two-person game, so there is one color for each player. One player uses red ('R') and the other one uses green ('G'). Players alternate turns, inserting one disc every time.
 *
 * 4. We want feedback when either an event or an error occurs within the game. The output shows the status of the board after every move.
 *
 * 5. When no more discs can be inserted, the game finishes, and it is considered a draw.
 *
 * 6. If a player inserts a disc and connects more than three discs of his color in a straight vertical line, then that player wins.
 *
 * 7. The same happens in a horizontal line direction.
 *
 * 8. The same happens in a diagonal line direction.
 *
 */
public class Connect4Test {

    private Connect4 connect4;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Rule
    public Timeout testRule = new Timeout(1000, TimeUnit.MILLISECONDS);

    private OutputStream output;

    @Before
    public void beforeEachTest(){
        output = new ByteArrayOutputStream();
        connect4 = new Connect4(new PrintStream(output));
    }

    /**
     * The board is composed of seven columns and six rows, all positions are emptys.
     */
    @Test
    public void whenTheGameIsStartedTheBoardIsEmpyt(){
        assertThat(connect4.getNumberOfDiscs(), is(0));
    }

    /**
     * When a disc is put outside the boundaries, a Runtime Exception is thrown
     */
    @Test
    public void whenDiscOutsideBoardThenRuntimeException(){

        int column = -1;
        exception.expect(RuntimeException.class);
        exception.expectMessage("Invalid column " + column);
        connect4.putDiscInColumn(column);
    }

    /**
     * When a disc is inserted into an empty column, its position is 0
     */
    @Test
    public void whenFirstDiscInsertedInColumnThenPositionIsZero() {
        int column = 1;
        assertThat(connect4.putDiscInColumn(column), is(0));
    }

    /**
     * When a second disc is inserted into the same column, its position is 1
     */
    @Test
    public void whenSecondDiscInsertedInColumnThenPositionIsOne() {
        int column = 1;
        connect4.putDiscInColumn(column);
        assertThat(connect4.putDiscInColumn(column), is(1));
    }

    /**
     * When a disc is inserted into the board, the total number of discs increases
     */
    @Test
    public void whenDiskInsertedThenNumberOfDiscsIncreases(){
        int column = 1;
        connect4.putDiscInColumn(column);
        assertThat(connect4.getNumberOfDiscs(), is(1));
    }

    /**
     * When a disc is inserted in to a column and there's no room available for it,
     * then a Runtime Exception is thrown
     */
    @Test
    public void whenNoMoreRoomInColumnThenRuntimeException(){
        int column = 1;
        int maxDiscsInColumn = 6; // the number of rows
        for (int times = 0 ; times < maxDiscsInColumn ; ++times) {
            connect4.putDiscInColumn(column);
        }
        exception.expect(RuntimeException.class);
        exception.expectMessage("No more room in column " +
                        column);
        connect4.putDiscInColumn(column);
    }

    /**
     * It is a two-person game, so there is one colour for each player.
     * One player uses red ('R') and the other one uses green ('G').
     * Players alternate turns, inserting one disc every time.
     */

    @Test
    public void whenFirstPlayerPlaysThenDiscColorIsRed() {
        assertThat(connect4.getCurrentPlayer(), is("R"));
    }

    @Test
    public void whenSecondPlayerPlaysThenDiscColorIsRed() {
        int column = 1;
        connect4.putDiscInColumn(column);
        assertThat(connect4.getCurrentPlayer(), is("G"));
    }

    /**
     * We want feedback when either an event or an error occurs within the game.
     * The output shows the status of the board on every move.
     */
    @Test
    public void whenAskedForCurrentPlayerTheOutputNotice() {
        connect4.getCurrentPlayer();
        assertThat(output.toString(), containsString("Player R turn"));
    }

    @Test
    public void whenADiscIsIntroducedTheBoardIsPrinted() {
        int column = 1;
        connect4.putDiscInColumn(column);
        assertThat(output.toString(), containsString("| |R| | | | | |"));
    }

    /**
     * When no more discs can be inserted, the game finishes and it is considered a draw.
     */
    @Test
    public void whenTheGameStartsItIsNotFinished() {
        assertFalse("The game must not be finished", connect4.isFinished());
    }

    @Test
    public void whenNoDiscCanBeIntroducedTheGamesIsFinished() {
        for (int row = 0; row < 6; row++){
            for (int column = 0; column < 7; column++){
                connect4.putDiscInColumn(column);
            }
        }
        assertTrue("The game must be finished", connect4.isFinished());
    }

    /**
     * If a player inserts a disc and connects more than three discs of his color in a straight vertical line,
     * then that player wins.
     */
    @Test
    public void when4VerticalDiscsAreConnectedThenPlayerWins() {
        for (int row = 0; row < 3; row++) {
            connect4.putDiscInColumn(1); // R
            connect4.putDiscInColumn(2); // G
        }
        assertThat(connect4.getWinner(), is(""));
        connect4.putDiscInColumn(1); // R
        assertThat(connect4.getWinner(), is("R"));
    }

    /**
     * If a player inserts a disc and connects more than three discs of his color in a straight horizontal line, then that player wins.
     */
    @Test
    public void when4HorizontalDiscsAreConnectedThenPlayerWins() {
        int column;
        for (column = 0; column < 3; column++) {
            connect4.putDiscInColumn(column); // R
            connect4.putDiscInColumn(column); // G
        }
        assertThat(connect4.getWinner(), is(""));
        connect4.putDiscInColumn(column); // R
        assertThat(connect4.getWinner(), is("R"));
    }

    /**
     * If a player inserts a disc and connects more than three discs of his colour in a straight diagonal line, then that player wins.
     */
    @Test
    public void when4Diagonal1DiscsAreConnectedThenThatPlayerWins()
    {
        int[] gameplay = new int[] {1, 2, 2, 3, 4, 3, 3, 4, 4, 5, 4};
        for (int column : gameplay) {
            connect4.putDiscInColumn(column);
        }
        assertThat(connect4.getWinner(), is("R"));
    }

    @Test
    public void when4Diagonal2DiscsAreConnectedThenThatPlayerWins()
    {
        int[] gameplay = new int[] {3, 4, 2, 3, 2, 2, 1, 1, 1, 1};
        for (int column : gameplay) {
            connect4.putDiscInColumn(column);
        }
        assertThat(connect4.getWinner(), is("G"));
    }

}