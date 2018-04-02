package com.test.khrushch.testapplication.update_service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class WeatherAlarmReceiver extends BroadcastReceiver {
    public static final int REQUEST_CODE = 1313;
    public static final String ACTION = "com.codepath.example.servicesdemo.alarm";

    // Triggered by the Alarm periodically (starts the service to run task)
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, WeatherService.class);
        i.putExtra("foo", "bar");
        context.startService(i);
    }
}