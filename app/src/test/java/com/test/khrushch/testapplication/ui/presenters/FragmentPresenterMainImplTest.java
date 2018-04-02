package com.test.khrushch.testapplication.ui.presenters;

import org.junit.Test;

import static com.test.khrushch.testapplication.ui.presenters.FragmentPresenterMainImpl.getTemperatureWithSing;
import static org.junit.Assert.assertEquals;

/**
 * Created by ultra on 02.04.18.
 */
public class FragmentPresenterMainImplTest {


    @Test
    public void getCorrectTemperature() throws Exception {
        float value1 = -1.03f;
        float value2 = 1.03f;
        float value3 = 2.5f;
        float value4 = 0f;
        assertEquals(getTemperatureWithSing(value1), "-1");
        assertEquals(getTemperatureWithSing(value2), "+1");
        assertEquals(getTemperatureWithSing(value3), "+3");
        assertEquals(getTemperatureWithSing(value4), "0");
    }
}