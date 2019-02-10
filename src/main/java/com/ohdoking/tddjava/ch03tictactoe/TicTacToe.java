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
    public void play(int x, int y) {
        if (x < 1 || x > 3) {
            throw new RuntimeException("X is outside board");
        }
    }
}
