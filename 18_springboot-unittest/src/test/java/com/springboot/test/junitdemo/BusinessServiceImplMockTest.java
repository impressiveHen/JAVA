package com.springboot.test.junitdemo;

import com.springboot.test.junitdemo.BusinessServiceImpl;
import com.springboot.test.junitdemo.DataService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
/*
When we write a unit test for SomeBusinessImpl, we will want to use a mock
DataService - one which does not connect to a database.
 */

public class BusinessServiceImplMockTest {
    @Test
    public void testGetMaxFromData() {
        DataService dataServiceMock = mock(DataService.class);
        when(dataServiceMock.retrieveData()).thenReturn(new int[]{24, 3, 1, -1});
        BusinessServiceImpl businessServiceImpl = new BusinessServiceImpl(dataServiceMock);
        int result = businessServiceImpl.getMaxFromData();
        assertEquals(24, result);
    }

    @Test
    public void testGetMaxFromData_ForOneValue() {
        DataService dataServiceMock = mock(DataService.class);
        when(dataServiceMock.retrieveData()).thenReturn(new int[]{-3});
        BusinessServiceImpl businessServiceImpl = new BusinessServiceImpl(dataServiceMock);
        int result = businessServiceImpl.getMaxFromData();
        assertEquals(-3, result);
    }

}