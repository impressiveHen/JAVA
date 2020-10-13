package com.test.business;

import com.test.data.TodoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TodoBusinessImplMockTest {
    @Mock
    TodoService todoServiceMock;

    @InjectMocks
    TodoBusinessImpl todoBusinessImpl;

    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;

    @Test
    public void testRetrieveTodosRelatedToSpring() {
        List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring",
            "Dancing in the rain");

        when(todoServiceMock.retrieveTodos("Henry")).thenReturn(todos);
        List<String> result = todoBusinessImpl.retrieveTodosRelatedToSpring("Henry");

        assertEquals(2, result.size());
    }

    @Test
    public void testDeleteTodosNotRelatedToSpring() {
        List<String> todos = Arrays.asList("Learn Spring MVC",
                "Learn Spring", "Learn to Dance");

        when(todoServiceMock.retrieveTodos("Henry")).thenReturn(todos);
        todoBusinessImpl.deleteTodosNotRelatedToSpring("Henry");
        /*
        Mockito Verify methods are used to check that certain behavior happened. We can use Mockito verify methods
        at the end of the testing method code to make sure that specified methods are called.

        Mockito verify() method can be used to test number of method invocations too. We can test exact number of
        times, at least once, at least, at most number of invocation times for a mocked method.
        */
        verify(todoServiceMock).deleteTodo("Learn to Dance");
        verify(todoServiceMock, Mockito.never()).deleteTodo("Learn Spring MVC");
        verify(todoServiceMock, Mockito.never()).deleteTodo("Learn Spring");
        verify(todoServiceMock, Mockito.times(1)).deleteTodo("Learn to Dance");
    }

    @Test
    public void testDeleteTodosNotRelatedToSpringCaptureArgument() {
        List<String> todos = Arrays.asList("Learn Spring MVC",
                "Learn Spring", "Learn to Dance");

        when(todoServiceMock.retrieveTodos("Henry")).thenReturn(todos);
        todoBusinessImpl.deleteTodosNotRelatedToSpring("Henry");

        verify(todoServiceMock).deleteTodo(stringArgumentCaptor.capture());

        assertEquals("Learn to Dance", stringArgumentCaptor.getValue());
    }

}