package com.ohdoking.tddjava.ch06tictactoe.mongo;

import org.junit.Test;

import java.net.UnknownHostException;

import static org.junit.Assert.*;

/**
 * TicTacToeCollectionTest
 *
 * Requirement 1
 *
 * Implement an option to save a single move with the turn number, the X and Y axis positions, and the player (X or O).
 */
public class TicTacToeCollectionSpec {

    /**
     * specify what the name of the DB
     * @throws UnknownHostException
     */
    @Test
    public void whenInstantiatedThenMongoHasDbNameTicTacToe() throws UnknownHostException {
        TicTacToeCollection collection = new TicTacToeCollection();
        assertEquals( "tic-tac-toe", collection.getMongoCollection().getDBCollection().getDB().getName());
    }

    /**
     * create a specification that will define the name of the Mongo collection
     * @throws UnknownHostException
     */
    @Test
    public void whenInstantiatedThenMongoCollectionHasNameGame() throws UnknownHostException {
        TicTacToeCollection collection = new TicTacToeCollection();
        assertEquals("game", collection.getMongoCollection().getName());
    }
}