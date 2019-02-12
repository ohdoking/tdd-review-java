package com.ohdoking.tddjava.ch05connect4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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

    @Before
    public void beforeEachTest(){
        connect4 = new Connect4();
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

}