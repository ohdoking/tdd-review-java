package com.ohdoking.tddjava.ch06tictactoe.mongo;

import com.mongodb.MongoException;
import org.jongo.MongoCollection;
import org.junit.Before;
import org.junit.Test;

import java.net.UnknownHostException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * TicTacToeCollectionTest
 *
 * Requirement 1
 *
 * Implement an option to save a single move with the turn number, the X and Y axis positions, and the player (X or O).
 *
 * Requirement 2
 *
 * Save each turn to the database and make sure that a new session cleans the old data.
 */
public class TicTacToeCollectionSpec {

    TicTacToeCollection collection;
    TicTacToeBean bean;
    MongoCollection mongoCollection;
    @Before
    public void before() throws UnknownHostException {
        collection = spy(new TicTacToeCollection());
        bean = new TicTacToeBean(3, 2, 1, 'Y');
        mongoCollection = mock(MongoCollection.class);
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

    /**
     * Implement an option to save a single move with the turn number, the X and Y axis positions, and the player (X or O).
     */
    /**
     * Save
     */
    @Test
    public void whenSaveMoveThenInvokeMongoCollectionSave() {
        //we're telling that mocked mongoCollection should be returned whenever we call the getMongoCollection method of the collection spied class
        doReturn(mongoCollection)
                .when(collection)
                .getMongoCollection();
        collection.saveMove(bean);
        //we should verify that the correct invocation of the Jongo library is performed once
        verify(mongoCollection, times(1)).save(bean);
    }

    @Test
    public void whenSaveMoveThenReturnTrue() {
        doReturn(mongoCollection)
                .when(collection)
                .getMongoCollection();
        assertTrue(collection.saveMove(bean));
    }

    @Test
    public void givenExceptionWhenSaveMoveThenReturnFalse() {
        //It acts in a similar way to doReturn and throws an Exception when conditions set in when are fulfilled.
        doThrow(new MongoException("Bla"))
                .when(mongoCollection)
                .save(any(TicTacToeBean.class));
        doReturn(mongoCollection)
                .when(collection)
                .getMongoCollection();
        assertFalse(collection.saveMove(bean));
    }

    /**
     * Drop
     */
    @Test
    public void whenDropThenInvokeMongoCollectionDrop() {
        doReturn(mongoCollection)
                .when(collection)
                .getMongoCollection();
        collection.drop();
        verify(mongoCollection).drop();
    }

    @Test
    public void whenDropThenReturnTrue() {
        doReturn(mongoCollection)
                .when(collection)
                .getMongoCollection();
        assertTrue(collection.drop());
    }

    @Test
    public void givenExceptionWhenDropThenReturnFalse(){
        doThrow(new MongoException("Bla"))
                .when(mongoCollection)
                .drop();
        doReturn(mongoCollection)
                .when(collection)
                .getMongoCollection();
        assertFalse(collection.drop());
    }



}