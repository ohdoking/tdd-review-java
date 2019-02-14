package com.ohdoking.tddjava.ch06review;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Matchers.anyFloat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MockingSpec {

    @Mock
    List list;

    @Rule
    public ExpectedException exception = ExpectedException.none();


    @Before
    public void before(){

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

    @Test
    public void verifyingExactNumberOfInvocations(){
        list.add("once");

        list.add("twice");
        list.add("twice");

        list.add("three times");
        list.add("three times");
        list.add("three times");

        verify(list).add("once"); // times(1) 기본값
        verify(list, times(1)).add("once");

        verify(list, times(2)).add("twice");
        verify(list, times(3)).add("three times");

        verify(list, never()).add("never happened"); // 호출된 적 없음

        //verify(list, atLeastOnce()).add("three times"); // 최소 한 번
        //verify(list, atLeast(2)).add("five times"); // 최소 두 번
        //verify(list, atMost(5)).add("three times"); // 최대 다섯 번
    }

    @Test
    public void stubbingVoidMethodsWithExceptions(){
        doThrow(new RuntimeException()).when(list).clear();

        exception.expect(RuntimeException.class);
        list.clear(); // RuntimeException
    }

    @Test
    public void verificationInOrder() {
        // single mock
        List singleMock = mock(List.class);

        singleMock.add("first");
        singleMock.add("second");

        InOrder inOrder = inOrder(singleMock);

        inOrder.verify(singleMock).add("first");
        inOrder.verify(singleMock).add("second");

        // multiple mocks
        List firstMock = mock(List.class);
        List secondMock = mock(List.class);

        //using mocks
        firstMock.add("first");
        secondMock.add("second");

        inOrder = inOrder(firstMock, secondMock); // pass multiple mocks to verify

        inOrder.verify(firstMock).add("first");
        inOrder.verify(secondMock).add("second");
    }

    @Test
    public void findingRedundantInvocations(){
        list.add("one");
        list.add("two");

        verify(list).add("one");
        //verify(list).add("two");// if remove fail

        verifyNoMoreInteractions(list); // fail here
    }
}
