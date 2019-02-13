package com.ohdoking.tddjava.ch06tictactoe.mongo;

import org.junit.Before;
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

    TicTacToeCollection collection
    @Before
    public void before() throws UnknownHostException {
        collection = new TicTacToeCollection();
    }
    /**
     * specify what the name of the DB
     */
    @Test
    public void whenInstantiatedThenMongoHasDbNameTicTacToe(){
        assertEquals( "tic-tac-toe", collection.getMongoCollection().getDBCollection().getDB().getName());
    }

    /**
     * create a specification that will define the name of the Mongo collection
     */
    @Test
    public void whenInstantiatedThenMongoCollectionHasNameGame(){
        assertEquals("game", collection.getMongoCollection().getName());
    }
}