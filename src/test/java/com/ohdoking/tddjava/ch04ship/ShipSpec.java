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
 *
 * Requirement 3
 *
 * Implement commands that turn the ship left and right (l and r).
 *
 * Requirement 4
 *
 * The ship can receive a string with commands (lrfb is equivalent to left, right, forward, and backward).
 *
 * Requirement 5
 *
 * Implement wrapping from one edge of the grid to another.
 */
public class ShipSpec {

    private Ship ship;
    private Location location;

    @Before
    public void beforeTest() {
        Point max = new Point(50, 50);
        Planet planet = new Planet(max);
        location = new Location(
                new Point(21, 13), Direction.NORTH);
        ship = new Ship(location, planet);
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

    /**
     * Implement commands that turn the ship left and right (l and r).
     */
    @Test
    public void givenNorthWhenTurnLeftThenDirectionIsWest(){
        ship.turnLeft();
        assertEquals(ship.getLocation().getDirection(), Direction.WEST);
    }

    @Test
    public void givenEastWhenTurnRightThenDirectionIsSouth(){
        ship.getLocation().setDirection(Direction.EAST);
        ship.turnRight();
        assertEquals(ship.getLocation().getDirection(), Direction.SOUTH);
    }

    /**
     * The ship can receive a string with commands (lrfb is equivalent to left, right, forward, and backward).
     */
    @Test
    public void givenNorthwhenReceiveCommandsFThenYIncreases() {
        ship.receiveCommands("f");
        assertEquals(ship.getLocation().getPoint().getY(), 14);
    }

    @Test
    public void givenNorthWhenReceiveCommandsBThenYDecrease(){
        ship.receiveCommands("b");
        assertEquals(ship.getLocation().getPoint().getY(), 12);
    }

    @Test
    public void givenNorthWhenReceiveCommandRThenDirectIsEast(){
        ship.receiveCommands("r");
        assertEquals(ship.getLocation().getDirection(), Direction.EAST);
    }

    @Test
    public void givenNorthWhenReceiveCommandLThenDirectIsWest(){
        ship.receiveCommands("l");
        assertEquals(ship.getLocation().getDirection(), Direction.WEST);
    }

    @Test
    public void whenReceiveCommandsThenAllAreExecuted() {
        ship.receiveCommands("rflb");
        assertEquals(ship.getLocation().getDirection(), Direction.NORTH);
        assertEquals(ship.getLocation().getPoint().getX(), 22);
        assertEquals(ship.getLocation().getPoint().getY(), 12);
    }

    /**
     * Implement wrapping from one edge of the grid to another.
     */
    @Test
    public void whenInstantiatedThenPlanetIsStored() {
        Point max = new Point(50, 50);
        Planet planet = new Planet(max);
        ship = new Ship(location, planet);
        assertEquals(ship.getPlanet(), planet);
    }


}