package com.ohdoking.tddjava.ch03tictactoe;

/**
 *
 * TicTacToe
 *
 * @author ohdoking
 *
 * Tic-tac-toe is a paper-and-pencil game for two players, X and O, who take turns marking the spaces in a 3×3 grid.
 * The player who succeeds in placing three respective marks in a horizontal, vertical, or diagonal row, wins the game.
 *
 * For more information about the game, please visit Wikipedia (http://en.wikipedia.org/wiki/Tic-tac-toe).
 *
 */
public class TicTacToe {

    private Character[][] board = {
            {'\0', '\0', '\0'},
            {'\0', '\0', '\0'},
            {'\0', '\0', '\0'}};

    public void play(int x, int y) {
        checkAxis(x);
        checkAxis(y);
        setBox(x, y);
    }

    private void checkAxis(int axis) {
        if (axis < 1 || axis > 3) {
            throw new RuntimeException("X is outside board");
        }
    }

    private void setBox(int x, int y) {
        if (board[x - 1][y - 1] != '\0') {
            throw new RuntimeException("Box is occupied");
        } else {
            board[x - 1][y - 1] = 'X';
        }
    }
}
