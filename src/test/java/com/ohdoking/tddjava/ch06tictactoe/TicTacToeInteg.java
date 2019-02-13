package com.ohdoking.tddjava.ch06tictactoe;

import com.ohdoking.tddjava.ch03tictactoe.TicTacToe;
import org.junit.Test;

import java.net.UnknownHostException;

import static org.junit.Assert.assertEquals;

public class TicTacToeInteg {
       @Test
       public void givenMongoDbIsRunningWhenPlayThenNoException() throws UnknownHostException {
           TictactoeMongo ticTacToe = new TictactoeMongo();
           assertEquals("No winner", ticTacToe.play(1, 1));
       }
}