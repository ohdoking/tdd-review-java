package com.ohdoking.tddjava.ch06review;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CalculatorTest {

    Calculator calculator;
    @Mock
    CustomDB db;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void before(){
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

    @Test
    public void given4IntegerWhenAddThenReturnSum(){
        //CustomDB spy = spy(db);

        when(db.save(anyInt())).thenReturn(true);

        calculator.add(1)
                .add(4)
                .add(5)
                .add(1);

        verify(db).save(1);

    }

    @Test
    public void givenTwoIntegerWhenSubtractThenReturn0(){

        doReturn(true)
                .when(db)
                .save(anyInt());

        calculator.subtract(5,2);
    }

    @Test
    public void whenSaveThenReturnFalse(){

        doReturn(false)
                .when(db)
                .save(anyInt());
        exception.expect(RuntimeException.class);
        calculator.subtract(5,2);
    }
}