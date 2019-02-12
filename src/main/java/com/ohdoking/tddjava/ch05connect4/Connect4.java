package com.ohdoking.tddjava.ch05connect4;

public class Connect4 {
    public int getNumberOfDiscs() {
        return 0;
    }

    public void putDiscInColumn(int column) {
        if(column < 0){
            throw new RuntimeException("Invalid column " + column);
        }
    }
}
