package com.ohdoking.tddjava.ch06review;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.mockito.Mockito.*;

public class CalculatorTest {

    Calculator calculator;
    CustomDB db;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void before(){
        db = mock(CustomDB.class);
        calculator = new Calculator(db);
    }

    @Test
    public void givenTwoWhenAddThenVerifyValue(){
        calculator.add(3,1);
        verify(db).save(4);
    }

    @Test
    public void givenTwoIntegerWhenAddThenReturnFalseThenException(){
        doReturn(false)
                .when(db)
                .save(3);

        exception.expect(RuntimeException.class);
        calculator.add(1,2);
    }
}