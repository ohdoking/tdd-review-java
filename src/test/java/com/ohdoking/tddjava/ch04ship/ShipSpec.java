package com.ohdoking.tddjava.ch04ship;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
 *
 * Requirement 6
 *
 * Implement surface detection before each move to a new position.
 * If a command encounters a surface, the ship aborts the move, stays on the current position, and reports the obstacle.
 *
 * • The Planet object has the constructor that accepts a list of obstacles. Each obstacle is an instance of the Point class.
 * • The Location.foward and Location.backward methods have overloaded versions that accept a list of obstacles. They return true if a move was successful and false if it failed. Use this Boolean to construct a status report required for the Ship.receiveCommands method.
 * • The receiveCommands method should return a string with the status of each command. 0 can represent OK and X can be for a failure to move (OOXO = OK, OK, Failure, OK).
 *
 *
 */
public class ShipSpec {

    private Ship ship;
    private Location location;
    private Planet planet;

    @Before
    public void beforeTest() {
        Point max = new Point(50, 50);
        location = new Location(new Point(21, 13), Direction.NORTH);
        List<Point> obstacles = new ArrayList<>();
        obstacles.add(new Point(44, 44));
        obstacles.add(new Point(45, 46));
        planet = new Planet(max, obstacles);
//        ship = new Ship(location);
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
        assertEquals(ship.getLocation().getPoint().getY(), 12);
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
        assertEquals(ship.getLocation().getPoint().getY(), 14);
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
        assertEquals(ship.getLocation().getPoint().getY(), 12);
    }

    @Test
    public void givenNorthWhenReceiveCommandsBThenYDecrease(){
        ship.receiveCommands("b");
        assertEquals(ship.getLocation().getPoint().getY(), 14);
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
        assertEquals(ship.getLocation().getPoint().getY(), 14);
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

    /**
     * The name of this method has been shortened due to line's length restrictions.
     * The aim of this test is to check the behavior of ship when it is told to overpass the right boundary.
     */
    @Test
    public void overpassEastBoundary() {
        location.setDirection(Direction.EAST);
        location.getPoint().setX(planet.getMax().getX());
        ship.receiveCommands("f");
        assertEquals(location.getPoint().getX(), 1);
    }

    /**
     * Implement surface detection before each move to a new position.
     * If a command encounters a surface, the ship aborts the move, stays on the current position, and reports the obstacle.
     */
    @Test
    public void whenReceiveCommandsThenStopOnObstacle() {
        List<Point> obstacles = new ArrayList<>();
        obstacles.add(new Point(location.getPoint().getX() + 1, location.getPoint().getY()));
        ship.getPlanet().setObstacles(obstacles);
        // Moving forward would encounter an obstacle
        // expected.forward(new Point(0, 0), new ArrayList<Point>());
        ship.receiveCommands("rflb");
        assertEquals(ship.getLocation().getDirection(), Direction.NORTH);
        assertEquals(ship.getLocation().getPoint().getX(), 21);
        assertEquals(ship.getLocation().getPoint().getY(), 14);
    }

    @Test
    public void whenReceiveCommandsThenOForOkAndXForObstacle() {
        List<Point> obstacles = new ArrayList<>();
        obstacles.add(new Point(location.getPoint().getX() + 1, location.getPoint().getY()));
        ship.getPlanet().setObstacles(obstacles);
        String status = ship.receiveCommands("rflb");
        assertEquals(status, "OXOO");
    }



}