package com.ohdoking.tddjava.ch05connect4;

import java.util.Arrays;

public class Connect4 {

    private static final int ROWS = 6;
    private static final int COLUMNS = 7;
    private static final String EMPTY = " ";

    String[][] board = new String[ROWS][COLUMNS];

    int totalNumber = 0;

    public Connect4() {
        for (String[] row : board){
            Arrays.fill(row, EMPTY);
        }

    }

    public int getNumberOfDiscs() {
        return totalNumber;
    }

    public int putDiscInColumn(int column) {
        int index = 0;
        if(column < 0){
            throw new RuntimeException("Invalid column " + column);
        }
        for(int i = 0 ; i < board.length; i++){
            if(board[i][column].equals(EMPTY)){
                board[i][column] = "0";
                index = i;
                totalNumber++;
                break;
            }
        }

        return index;
    }
}
