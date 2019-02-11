package com.ohdoking.tddjava.ch04ship;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * ShipSpec
 *
 * @author ohdoking
 *
 * Requirement 1
 *
 * You are given the initial starting point (x, y) of a ship and the direction (N, S, E, or W) it is facing.
 *
 * Requirement 2
 *
 * Implement commands that move the ship forward and backward (f and b).
 */
public class ShipSpec {

    private Ship ship;
    private Location location;

    @Before
    public void beforeTest() {
        location = new Location(
                new Point(21, 13), Direction.NORTH);
        ship = new Ship(location);
    }

    /**
     * You are given the initial starting point (x, y) of a ship and the direction (N, S, E, or W) it is facing.
     */
    @Test
    public void whenInstantiatedThenLocationIsSet() {
        assertEquals(ship.getLocation(), location);
    }

    /**
     * Implement commands that move the ship forward and backward (f and b).
     */
    @Test
    public void givenNorthWhenMoveForwardThenYIncreases(){
        ship.moveForward();
        assertEquals(ship.getLocation().getPoint().getY(), 14);
    }

    @Test
    public void givenEastWhenMoveForwardThenXIncreases(){
        ship.getLocation().setDirection(Direction.EAST);
        ship.moveForward();
        assertEquals(ship.getLocation().getPoint().getX(), 22);
    }

    @Test
    public void givenNorthWhenMoveBackwardThenYDecreases(){
        ship.moveBackward();
        assertEquals(ship.getLocation().getPoint().getY(), 12);
    }

    @Test
    public void givenEastWhenMoveBackwardThenXDecreases(){
        ship.getLocation().setDirection(Direction.EAST);
        ship.moveBackward();
        assertEquals(ship.getLocation().getPoint().getX(), 20);
    }




}