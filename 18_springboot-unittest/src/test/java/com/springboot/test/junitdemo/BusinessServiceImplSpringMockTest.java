package com.springboot.test.junitdemo;

import com.springboot.test.junitdemo.SpringBusinessServiceImpl;
import com.springboot.test.junitdemo.SpringDataService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/*
@SpringBootTest - This annotation indicates that the context under test is a
@SpringBootApplication. The complete SpringBootTutorialBasicsApplication is
launched up during the unit test.

Launching the entire spring context makes the unit test slower.
Unit tests will also start failing if there are errors in other beans in the
contexts. So, the MockitoJUnitRunner approach is preferred.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
class BusinessServiceImplSpringMockTest {
//    @MockBean annotation creates a mock for DataService.
    @MockBean
    SpringDataService springDataServiceMock;

//    Pick the Business Service from the Spring Context and autowire it in.
    @Autowired
    SpringBusinessServiceImpl businessServiceImpl;

    @Test
    public void testGetMaxFromData() {
        when(springDataServiceMock.retrieveData()).thenReturn(new int[]{24, 3, 1, -1});
        assertEquals(24, businessServiceImpl.getMaxFromData());
    }

    @Test
    public void testGetMaxFromData_ForOneValue() {
        when(springDataServiceMock.retrieveData()).thenReturn(new int[]{-3});
        assertEquals(-3, businessServiceImpl.getMaxFromData());
    }

    @Test
    public void testGetMaxFromData_ForNoValues() {
        when(springDataServiceMock.retrieveData()).thenReturn(new int[]{});
        assertEquals(Integer.MIN_VALUE, businessServiceImpl.getMaxFromData());
    }


}