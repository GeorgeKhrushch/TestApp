package com.test.khrushch.testapplication.network_state;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkCheck {

    public static boolean networkChecking(Context sContext){
        ConnectivityManager cm =
                (ConnectivityManager) sContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(cm != null) {
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            return netInfo != null && netInfo.isConnectedOrConnecting();
        }else
            return false;
    }
}