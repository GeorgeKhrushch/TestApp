package com.test.khrushch.testapplication.ui.presenters;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.gson.Gson;
import com.test.khrushch.testapplication.R;
import com.test.khrushch.testapplication.network_state.NetworkChangeReceiver;
import com.test.khrushch.testapplication.network_state.NetworkCheck;
import com.test.khrushch.testapplication.new_client.client_impl.RequestManager;
import com.test.khrushch.testapplication.preferences.SharedPrefsUtils;
import com.test.khrushch.testapplication.structure.WeatherInfo;
import com.test.khrushch.testapplication.ui.fragments.FragmentSelectCity;
import com.test.khrushch.testapplication.ui.views.FragmentViewMain;

import static com.test.khrushch.testapplication.static_data.CitiesStaticInfo.DEFAULT_CITY_ID;
import static com.test.khrushch.testapplication.ui.FragmentNamesConstants.FRAGMENT_SELECT_CITY;

public class FragmentPresenterMainImpl extends FragmentPresenterBaseImpl {
    private static final String WEATHER_BUNDLE_KEY = "weather_bundle_key";
    private BroadcastReceiver receiver;
    private WeatherInfo weatherInfo;
    private int cityId = 0;

    public FragmentPresenterMainImpl(FragmentViewMain view, ActivityPresenter activityPresenter,
                                     RequestManager requestManager) {
        super(view, activityPresenter, requestManager);
    }

    @Override
    public void loadData(Bundle savedState) {
        if(savedState != null){
            weatherInfo = getWeatherFromBundle(savedState);
            getView().setWeatherInfoToViews(weatherInfo);
        }else
            loadData();
    }

    private void loadData(){
        if(!NetworkCheck.networkChecking(view.getFragment().getContext())){
            NetworkChangeReceiver.isOn = false;
            view.makeToast(R.string.internet_problems);
            return;
        }

        int prefsId = SharedPrefsUtils.getLastCityId(view.getFragment().getContext(), DEFAULT_CITY_ID);
        if(this.cityId == 0 || this.cityId != prefsId) {
            requestManager.getWeatherInfo(prefsId).subscribe(info -> {
                getView().setWeatherInfoToViews(info);
                weatherInfo = info;
            }, getDefaultErrorConsumer());
            this.cityId = prefsId;
        }
    }

    @Override
    public FragmentViewMain getView() {
        return (FragmentViewMain) view;
    }

    public void openSelectCityFragment(){
        activityPresenter.addFragment(new FragmentSelectCity(), view.getFragment(), FRAGMENT_SELECT_CITY);
    }

    public static String getTemperatureWithSing(float temp){
        return (temp > 0 ? "+" : "") + String.valueOf(Math.round(temp));
    }

    public static String getIconUrl(String icon){
        return "http://openweathermap.org/img/w/" + icon + ".png";
    }

    public void registerNetworkReceiver() {
        receiver = new BroadcastReceiver(){
            @Override
            public void onReceive(Context context, Intent intent) {
                loadData();
            }
        };
        view.getFragment().getActivity()
                .registerReceiver(receiver, NetworkChangeReceiver.INTENT_FILTER_LOCAL);
    }

    public void unregisterReceiver(){
        view.getFragment().getActivity().unregisterReceiver(receiver);
    }

    public void putWeatherBundle(Bundle outState){
        if(weatherInfo != null)
            outState.putString(WEATHER_BUNDLE_KEY, new Gson().toJson(weatherInfo));
    }

    private WeatherInfo getWeatherFromBundle(Bundle bundle) {
        String json = bundle.getString(WEATHER_BUNDLE_KEY);
        return new Gson().fromJson(json, WeatherInfo.class);
    }
}
