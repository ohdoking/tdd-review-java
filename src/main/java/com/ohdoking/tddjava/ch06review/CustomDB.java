package com.ohdoking.tddjava.ch06review;

public class CustomDB {

    private int result = 0;

    public boolean save(int result) {
        this.result = result;
        return true;
    }

    public int getResult() {
        return result;
    }
}
