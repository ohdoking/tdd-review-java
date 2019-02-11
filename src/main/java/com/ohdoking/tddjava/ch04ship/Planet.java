package com.ohdoking.tddjava.ch04ship;

public class Planet {
    Point point;
    public Planet(Point max) {
        this.point = max;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
}
