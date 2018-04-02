package com.test.khrushch.testapplication.network_state;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

public class NetworkChangeReceiver extends BroadcastReceiver {

    public static boolean isOn;
    public static final String BROADCAST_TAG = "broadcast_tag";
    public static final String BROADCAST_NETWORK = "broadcast_network";
    public static final String LOCAL_FILTER_TAG = "intent_filter_local";
    public static final IntentFilter INTENT_FILTER_LOCAL = new IntentFilter("intent_filter_local");

    @Override
    public void onReceive(final Context context, final Intent intent) {
        if (NetworkCheck.networkChecking(context)) {
            if(!isOn) {
                sendBroadcast(context);
                Log.d("Network Available ", "true");
                isOn = true;
            }
        }else{
            if(isOn)
                isOn = false;
        }
    }

    private void sendBroadcast(Context context){
        Intent i = new Intent();
        i.setAction(LOCAL_FILTER_TAG);
        i.putExtra(BROADCAST_TAG, BROADCAST_NETWORK);
        context.sendBroadcast(i);
    }
}