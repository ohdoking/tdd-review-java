package com.ohdoking.tddjava.ch04ship;

import java.util.ArrayList;
import java.util.List;

import static com.ohdoking.tddjava.ch04ship.Direction.EAST;

public class Location {

    private static final int FORWARD = 1;
    private static final int BACKWARD = -1;

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
        return move(FORWARD, new Point(100, 100), new ArrayList<>());
    }
    public boolean forward(Point max) {
        return move(FORWARD, max, new ArrayList<>());
    }
    public boolean forward(Point max, List<Point> obstacles) {
        return move(FORWARD, max, obstacles);
    }

    public boolean backward() {
        return move(BACKWARD, new Point(100, 100), new ArrayList<>());
    }
    public boolean backward(Point max) {
        return move(BACKWARD, max, new ArrayList<>());
    }
    public boolean backward(Point max, List<Point> obstacles) {
        return move(BACKWARD, max, obstacles);
    }

    private boolean move(int fw, Point max, List<Point> obstacles) {
        int x = point.getX();
        int y = point.getY();
        switch(getDirection()) {
            case NORTH:
                y = wrap(point.getY() - fw, max.getY());
                break;
            case SOUTH:
                y = wrap(point.getY() + fw, max.getY());
                break;
            case EAST:
                x = wrap(point.getX() + fw, max.getX());
                break;
            case WEST:
                x = wrap(point.getX() - fw, max.getX());
                break;
        }
        if (isObstacle(new Point(x, y), obstacles)) {
            return false;
        } else {
            point = new Point(x, y);
            return true;
        }
    }

    private boolean isObstacle(Point point, List<Point> obstacles) {
        for (Point obstacle : obstacles) {
            if (obstacle.getX() == point.getX() && obstacle.getY() == point.getY()) {
                return true;
            }
        }
        return false;
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