package com.test.khrushch.testapplication.update_service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.test.khrushch.testapplication.MainActivity;
import com.test.khrushch.testapplication.R;
import com.test.khrushch.testapplication.new_client.client_impl.RequestManager;
import com.test.khrushch.testapplication.new_client.client_impl.ServiceNetwork;
import com.test.khrushch.testapplication.preferences.SharedPrefsUtils;
import com.test.khrushch.testapplication.structure.WeatherInfo;
import com.test.khrushch.testapplication.ui.presenters.FragmentPresenterMainImpl;

import io.reactivex.disposables.Disposable;

import static com.test.khrushch.testapplication.static_data.CitiesStaticInfo.DEFAULT_CITY_ID;

public class WeatherService extends IntentService {

    private static final String CHANNEL_ID = "channel_id";
    private RequestManager requestManager;
    private Disposable weatherResult;
    private int cityId;


    public WeatherService() {
        super("WeatherService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        initRequestManager();
        initCityId();
        weatherResult = requestManager.getWeatherInfo(cityId).subscribe(info -> {
                    sendNotification(info);
                    unsubscribe();
                });
        Log.i("WeatherService", "Service running - " + Thread.currentThread().getId());
    }


    private void sendNotification(WeatherInfo info) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_weather_cloudy_white_36dp)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),
                        R.drawable.ic_weather_cloudy_white_48dp))
                .setContentTitle(getString(R.string.notification_title) + " " + info.getName() + ":")
                .setContentText(getNotificationMessage(info))
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setDefaults(NotificationCompat.DEFAULT_VIBRATE | NotificationCompat.DEFAULT_LIGHTS)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());
    }

    private CharSequence getNotificationMessage(WeatherInfo info) {
        return FragmentPresenterMainImpl.getTemperatureWithSing(info.getMain().getTemp())
                + " " + info.getWeather().get(0).getDescription();
    }

    private void unsubscribe() {
        weatherResult.dispose();
    }

    private void initCityId() {
        cityId = SharedPrefsUtils.getLastCityId(getApplicationContext(), DEFAULT_CITY_ID);
    }

    private void initRequestManager() {
        requestManager = ServiceNetwork.getRequestManager();
    }
}