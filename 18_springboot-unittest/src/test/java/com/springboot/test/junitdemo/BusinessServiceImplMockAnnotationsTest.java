package com.springboot.test.junitdemo;

import com.springboot.test.junitdemo.BusinessServiceImpl;
import com.springboot.test.junitdemo.DataService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/*
Causes all the initialization magic with @Mock and @InjectMocks to
happen before the tests are run. otherwise the annotations will be ignored.
 */
@ExtendWith(MockitoExtension.class)
class BusinessServiceImplMockAnnotationsTest {
//    Create a mock for DataService.
    @Mock
DataService dataServiceMock;

//    Inject the mocks as dependencies into businessImpl.
    @InjectMocks
BusinessServiceImpl businessServiceImpl;

    @Test
    public void testGetMaxFromData() {
        when(dataServiceMock.retrieveData()).thenReturn(new int[]{24, 3, 1, -1});
        assertEquals(24, businessServiceImpl.getMaxFromData());
    }

    @Test
    public void testGetMaxFromData_ForOneValue() {
        when(dataServiceMock.retrieveData()).thenReturn(new int[]{-3});
        assertEquals(-3, businessServiceImpl.getMaxFromData());
    }

    @Test
    public void testGetMaxFromData_ForNoValues() {
        when(dataServiceMock.retrieveData()).thenReturn(new int[]{});
        assertEquals(Integer.MIN_VALUE, businessServiceImpl.getMaxFromData());
    }
}