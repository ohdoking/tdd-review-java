package com.ohdoking.tddjava.ch04ship;

import static com.ohdoking.tddjava.ch04ship.Direction.EAST;

public class Location {
    private Point point;
    private Direction direction;

    public Location(Point point, Direction direction) {
        this.point = point;
        this.direction = direction;
    }

    public Point getPoint() {
        return point;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public boolean forward() {
        switch (getDirection()) {
            case EAST:
                point.setX(point.getX() + 1);
                break;
            case WEST:
                point.setX(point.getX() - 1);
                break;
            case NORTH:
                point.setY(point.getY() + 1);
                break;
            case SOUTH:
                point.setY(point.getY() - 1);
                break;
            case NONE:
        }
        return true;
    }

    public boolean backward() {
        switch (getDirection()) {
            case EAST:
                point.setX(point.getX() - 1);
                break;
            case WEST:
                point.setX(point.getX() + 1);
                break;
            case NORTH:
                point.setY(point.getY() - 1);
                break;
            case SOUTH:
                point.setY(point.getY() + 1);
                break;
            case NONE:
        }
        return true;
    }
}