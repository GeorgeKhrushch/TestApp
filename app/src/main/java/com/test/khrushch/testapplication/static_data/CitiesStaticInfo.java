package com.test.khrushch.testapplication.static_data;

import com.annimon.stream.Stream;
import com.test.khrushch.testapplication.structure.CityInfo;

import java.util.ArrayList;
import java.util.List;

public class CitiesStaticInfo {

    public static final int DEFAULT_CITY_ID = 709930;

    /*It should be a result of cities request in real application*/
    private static List<CityInfo> getCitiesInfo(){
        List<CityInfo> cityInfoList = new ArrayList<>(5);
        cityInfoList.add(new CityInfo(709930, "Dnipropetrovsk"));
        cityInfoList.add(new CityInfo(703448, "Kiev"));
        cityInfoList.add(new CityInfo(706483, "Kharkiv"));
        cityInfoList.add(new CityInfo(702550, "Lviv"));
        return cityInfoList;
    }

    public static List<CityInfo> getCitiesInfoWithoutId(int id){
        return Stream.of(getCitiesInfo()).filter(info -> info.getId() != id).toList();
    }
}
