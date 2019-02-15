package com.ohdoking.tddjava.ch06review;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Matchers.anyFloat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

/**
 *
 * MockingSpec
 *
 * reference : https://bestalign.github.io/2016/07/10/intro-mockito-2/
 * 
 */
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

    @Test
    public void stubbingWithCallbacks(){
        when(list.get(anyInt())).thenAnswer(new Answer<Integer>() {
            public Integer answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments(); // arguments
                List mock = (List)invocation.getMock(); // mock itself
                int result = (Integer)args[0] + 1;
                return result;
            }
        });

        System.out.println(list.get(1)); // called with argument: 2

        when(list.get(anyInt())).thenAnswer(new Answer<Integer>() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                return (Integer)args[0] + 10;
            }
        });

        System.out.println(list.get(100));
    }

    @Test
    public void spyingOnRealObjects(){
        List list2 = new LinkedList();
        List spy = spy(list2);

        when(spy.size()).thenReturn(100); // stubbing

        spy.add("one");
        spy.add("two");

        System.out.println(spy.get(0)); // one
        System.out.println(spy.size()); // 100

        verify(spy).add("one");
        verify(spy).add("two");

        // Wrong use case
        //when(spy.get(10)).thenReturn("foo"); // IndexOutOfBoundsException

        // use doReturn() instead
        doReturn("foo").when(spy).get(0);
    }

    @Test
    public void argumentMatcher(){
        class ListOfTwoElements extends ArgumentMatcher<List> {
            @Override
            public boolean matches(Object argument) {
                List list = (List)argument;
                return list.size() == 3;
            }
        }

        List mock = mock(List.class);
        when(mock.addAll(argThat(new ListOfTwoElements()))).thenReturn(true);

        //mock.addAll(Arrays.asList("one", "two")); // false
        mock.addAll(Arrays.asList("one", "two","three")); // true

        verify(mock).addAll(argThat(new ListOfTwoElements()));
    }

    /**
     * to capture arguments
     */
    @Test
    public void capturingArgumentsForFurtherAssertions(){
        list.addAll(Arrays.asList("one", "two", "three"));

        ArgumentCaptor<List> argument = ArgumentCaptor.forClass(List.class);
        verify(list).addAll(argument.capture());
        Assert.assertTrue(argument.getValue().size() == 3);
    }

    /**
     * reset mock
     */
    @Test
    public void resettingMocks(){
        List mock = mock(List.class);
        when(mock.size()).thenReturn(10);
        mock.add(1);

        reset(mock);
    }

    /**
     * verify time
     */
    @Test
    public void verificationWithTimeout(){
        verify(list, timeout(100)).size();
        verify(list, timeout(100).times(2)).size();
        verify(list, timeout(100).atLeast(2)).size();
    }

    /**
     * using chaining
     */
    @Test
    public void oneLinerStubs(){
        List mock = when(mock(List.class).get(0))
                .thenReturn(1)
                .thenReturn(2)
                .thenThrow(Exception.class)
                .getMock();

    }

    /**
     * check spy or mock
     */
    @Test
    public void mockingDetailsTest(){
        mockingDetails(list).isMock();
        mockingDetails(list).isSpy();

    }

}
