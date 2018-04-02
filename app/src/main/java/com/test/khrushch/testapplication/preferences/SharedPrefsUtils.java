package com.test.khrushch.testapplication.preferences;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefsUtils {

    private static final String PREF_NAME = "test_app_prefs";
    private static final String LAST_CITY_ID = "prefs_last_city_id";

    public static void saveLastCityId(Context context, int id){
        getEditor(context)
                .putInt(LAST_CITY_ID, id)
                .apply();
    }

    public static int getLastCityId(Context context, int defaultId){
        return getPreferences(context).getInt(LAST_CITY_ID, defaultId);
    }

    private static SharedPreferences.Editor getEditor (Context context){
        return getPreferences(context).edit();
    }
    private static SharedPreferences getPreferences(Context context){
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }
}
