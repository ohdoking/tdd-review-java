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

    private static final int SIZE = 3;

    private Character[][] board = {
            {'\0', '\0', '\0'},
            {'\0', '\0', '\0'},
            {'\0', '\0', '\0'}};

    private char lastPlayer = '\0';

    public String play(int x, int y) {
        checkAxis(x);
        checkAxis(y);
        lastPlayer = nextPlayer();
        setBox(x, y, lastPlayer);
        if (isWin()) {
            return lastPlayer + " is the winner";
        }
        return "No winner";
    }

    private void checkAxis(int axis) {
        if (axis < 1 || axis > 3) {
            throw new RuntimeException("X is outside board");
        }
    }

    private void setBox(int x, int y, char lastPlayer)
    {
        if (board[x - 1][y - 1] != '\0') {
            throw new RuntimeException("Box is occupied");
        } else {
            board[x - 1][y - 1] = lastPlayer;
        }
    }

    public char nextPlayer() {
        if (lastPlayer == 'X') {
            return 'O'; }
        return 'X';
    }

    private boolean isWin() {
        int playerTotal = lastPlayer * SIZE;
        for (int i = 0; i < SIZE; i++) {
            if (board[0][i] + board[1][i] + board[2][i] == playerTotal) {
                return true;
            }
            else if (board[i][0] + board[i][1] + board[i][2] == playerTotal){
                return true;
            }

        }
        if (board[0][0] + board[1][1] + board[2][2] == playerTotal){
            return true;
        }
        else if(board[0][2] + board[1][1] + board[2][0] == playerTotal){
            return true;
        }
        return false;
    }

}
