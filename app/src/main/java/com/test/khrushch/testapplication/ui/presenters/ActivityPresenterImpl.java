package com.test.khrushch.testapplication.ui.presenters;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.test.khrushch.testapplication.R;
import com.test.khrushch.testapplication.ui.FragmentNamesConstants;
import com.test.khrushch.testapplication.ui.fragments.FragmentMain;
import com.test.khrushch.testapplication.ui.views.MainActivityView;
import com.test.khrushch.testapplication.update_service.WeatherAlarmReceiver;

import static android.support.v4.app.FragmentManager.POP_BACK_STACK_INCLUSIVE;

public class ActivityPresenterImpl implements ActivityPresenter {

    private MainActivityView view;
    private int THIRTY_MINUTES = 30*60*1000;

    public ActivityPresenterImpl (MainActivityView view){
        this.view = view;
    }

    @Override
    public void openMainFragment() {
        replaceFragment(new FragmentMain(), FragmentNamesConstants.FRAGMENT_MAIN);
    }

    @Override
    public void replaceFragment(Fragment fragment, String tag){
        FragmentManager fragmentManager = view.getMainFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frameContainer, fragment, tag)
                .commitAllowingStateLoss();
    }

    @Override
    public void addFragment(Fragment newFragment, Fragment current, String newTag) {
        FragmentTransaction transaction = view.getMainFragmentManager().beginTransaction();
        transaction
                .addToBackStack(current.getTag())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .add(R.id.frameContainer, newFragment, newTag);
        transaction.hide(current);
        transaction.commit();
    }

    @Override
    public void closeFragment(Fragment fragment, String tag) {
        view.getMainFragmentManager().beginTransaction()
                .remove(fragment)
                .commitAllowingStateLoss();
        view.getMainFragmentManager().popBackStack(tag, POP_BACK_STACK_INCLUSIVE);
    }

    @Override
    public void scheduleUpdateService() throws Exception{
        Intent intent = new Intent(view.getContext(), WeatherAlarmReceiver.class);
        final PendingIntent pIntent = PendingIntent.getBroadcast(view.getContext(),
                WeatherAlarmReceiver.REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        long firstMillis = System.currentTimeMillis() + THIRTY_MINUTES;
        AlarmManager alarm = (AlarmManager) view.getContext().getSystemService(Context.ALARM_SERVICE);
        alarm.setInexactRepeating(AlarmManager.RTC, firstMillis, THIRTY_MINUTES, pIntent);
    }

    @Override
    public void cancelUpdateService() throws Exception{
        Intent intent = new Intent(view.getContext(), WeatherAlarmReceiver.class);
        final PendingIntent pIntent = PendingIntent.getBroadcast(view.getContext(),
                WeatherAlarmReceiver.REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarm = (AlarmManager) view.getContext().getSystemService(Context.ALARM_SERVICE);
        alarm.cancel(pIntent);
    }

    @Override
    public void clearNotifications() {
        ((NotificationManager) view.getContext().getSystemService(Service.NOTIFICATION_SERVICE))
                .cancelAll();
    }
}
