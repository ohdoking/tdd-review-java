package com.ohdoking.tddjava.ch06review;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CalculatorTest {

    Calculator calculator;
    CustomDB db;

    List list;

    @Rule
    public ExpectedException exception = ExpectedException.none();


    @Before
    public void before(){
        db = mock(CustomDB.class);
        calculator = new Calculator(db);
        list = mock(List.class);
    }

    @Test
    public void whenAddValueAndThenClear(){
        // use mock
        list.add("one");
        list.add("two");
        list.clear();

        // verification
        verify(list).add("one");
        verify(list).add("two");
        verify(list).clear();
    }

    @Test
    public void useStubbing(){
        // stubbing
        when(list.get(0)).thenReturn("first");
        when(list.get(1)).thenThrow(new RuntimeException());
        doReturn("second").when(list).get(2);

        System.out.println(list.get(2)); // second
        System.out.println(list.get(0)); // first
        System.out.println(list.get(1)); // Runtime exception 발생
        System.out.println(list.get(10)); // null

        verify(list).get(0);

    }

    @Test
    public void useArguementMatcher(){
        when(list.get(anyInt())).thenReturn("int");
        when(list.add(anyFloat())).thenReturn(true);
        when(list.add(anyString())).thenReturn(true);

        System.out.println(list.get(999)); // int
        System.out.println(list.add(3.3)); // true
        System.out.println(list.add("string")); // true

        verify(list).get(anyInt());
        verify(list).add(anyFloat());
        verify(list).add(eq("string"));
    }

   





}