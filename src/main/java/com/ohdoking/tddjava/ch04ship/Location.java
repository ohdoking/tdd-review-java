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

    public boolean forward(Point max) {
        switch (getDirection()) {
            case EAST:
                point.setX(wrap(point.getX() + 1, max.getX()));
                break;
            case WEST:
                point.setX(wrap(point.getX() - 1, max.getX()));
                break;
            case NORTH:
                point.setY(wrap(point.getY() + 1, max.getY()));
                break;
            case SOUTH:
                point.setY(wrap(point.getY() - 1, max.getY()));
                break;
            case NONE:
        }
        return true;
    }

    public boolean backward(Point max) {
        switch (getDirection()) {
            case EAST:
                point.setX(wrap(point.getX() - 1, max.getX()));
                break;
            case WEST:
                point.setX(wrap(point.getX() + 1, max.getX()));
                break;
            case NORTH:
                point.setY(wrap(point.getY() - 1, max.getY()));
                break;
            case SOUTH:
                point.setY(wrap(point.getY() + 1, max.getY()));
                break;
            case NONE:
        }
        return true;
    }

    public void turnLeft() {
        this.direction = direction.turnLeft();
    }

    public void turnRight() {
        this.direction = direction.turnRight();
    }

    public int wrap(int point, int maxPoint){
        if (maxPoint > 0) {
            if (point > maxPoint) {
                return 1;
            } else if (point == 0) {
                return maxPoint;
            }
        }
        return point;
    }
}