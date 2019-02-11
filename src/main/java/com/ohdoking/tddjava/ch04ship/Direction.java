package com.ohdoking.tddjava.ch04ship;

public enum Direction {
    NORTH(0, 'N'),
    EAST(1, 'E'),
    SOUTH(2, 'S'),
    WEST(3, 'W'),
    NONE(4, 'X');

    private int i;
    private char cardinalPoint;

    Direction(int i, char cardinalPoint) {
        this.i = i;
        this.cardinalPoint = cardinalPoint;
    }

    public int getI() {
        return i;
    }

    public char getCardinalPoint() {
        return cardinalPoint;
    }
}
