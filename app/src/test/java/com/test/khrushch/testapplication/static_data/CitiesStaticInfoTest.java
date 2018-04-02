package com.test.khrushch.testapplication.static_data;

import com.annimon.stream.Stream;
import com.test.khrushch.testapplication.structure.CityInfo;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by ultra on 01.04.18.
 */
public class CitiesStaticInfoTest {

    @Test
    public void shouldFilterCityInfo() throws Exception {
        final int cityId = 703448;
        List<CityInfo> list = CitiesStaticInfo.getCitiesInfoWithoutId(cityId);
        assertTrue(list.size() == 3);
        assertTrue(Stream.of(list).noneMatch(info -> info.getId() == cityId));
    }
}