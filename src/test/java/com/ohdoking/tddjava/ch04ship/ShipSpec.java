package com.ohdoking.tddjava.ch04ship;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * ShipSpec
 *
 * @author ohdoking
 *
 * You are given the initial starting point (x, y) of a ship and the direction (N, S, E, or W) it is facing.
 *
 */
public class ShipSpec {

    @Test
    public void whenInstantiatedThenLocationIsSet() {
        Location location = new Location(
                new Point(21, 13), Direction.NORTH);
        Ship ship = new Ship(location);
        assertEquals(ship.getLocation(), location);
    }

}