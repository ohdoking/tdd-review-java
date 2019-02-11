package com.ohdoking.tddjava.ch04ship;

public enum Direction {
    NORTH(0, 'N'),
    EAST(1, 'E'),
    SOUTH(2, 'S'),
    WEST(3, 'W'),
    NONE(4, 'X');

    private final int index;
    private final char cardinalPoint;

    Direction(int index, char cardinalPoint) {
        this.index = index;
        this.cardinalPoint = cardinalPoint;
    }

    public int getIndex() {
        return index;
    }

    public char getCardinalPoint() {
        return cardinalPoint;
    }

    public Direction turnLeft() {
        int value = (index + 3) % 4;
        return Direction.values()[value];
    }

    public Direction turnRight() {
        int value = (index + 1) % 4;
        return Direction.values()[value];
    }
}
