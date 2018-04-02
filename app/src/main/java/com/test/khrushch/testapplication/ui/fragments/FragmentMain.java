package com.test.khrushch.testapplication.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.khrushch.testapplication.R;
import com.test.khrushch.testapplication.structure.WeatherInfo;
import com.test.khrushch.testapplication.ui.presenters.FragmentPresenterMainImpl;
import com.test.khrushch.testapplication.ui.views.FragmentViewMain;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.test.khrushch.testapplication.ui.presenters.FragmentPresenterMainImpl.getIconUrl;
import static com.test.khrushch.testapplication.ui.presenters.FragmentPresenterMainImpl.getTemperatureWithSing;

public class FragmentMain extends FragmentModel implements FragmentViewMain{

    @BindView(R.id.tvFragmentMainCityName) TextView tvCityName;
    @BindView(R.id.tvFragmentMainCurrentTemp) TextView tvCurrentTemp;
    @BindView(R.id.tvFragmentMainMinAndMaxTemp) TextView tvMinAndMaxTemp;
    @BindView(R.id.tvFragmentMainWeatherDescription) TextView tvWeatherDescription;
    @BindView(R.id.ivFragmentMainWeatherIcon) ImageView ivWeatherIcon;

    private View mainView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, mainView);
        return mainView;
    }

    @Override
    protected String getTitle() {
        return getString(R.string.title_main);
    }


    @Override
    public View getMainView() {
        return mainView;
    }

    @Override
    public void setWeatherInfoToViews(WeatherInfo weatherInfo) {
        tvCityName.setText(weatherInfo.getName());
        tvCurrentTemp.setText(getTemperatureWithSing(weatherInfo.getMain().getTemp()));
        tvMinAndMaxTemp.setText(getTemperatureWithSing(weatherInfo.getMain().getTempMin()) + "   "
                + getTemperatureWithSing(weatherInfo.getMain().getTempMax()));
        tvWeatherDescription.setText(weatherInfo.getWeather().get(0).getDescription());
        presenter.loadImage(ivWeatherIcon, getIconUrl(weatherInfo.getWeather().get(0).getIcon()));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_settings) {
            getPresenter().openSelectCityFragment();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private FragmentPresenterMainImpl getPresenter(){
        return (FragmentPresenterMainImpl) presenter;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden)
            presenter.loadData(null);
    }

    /*Throws  java.lang.IllegalStateException: Can not perform this action after onSaveInstanceState
        * when trying to .commit() after rotate - API 11+ bug:
        * https://stackoverflow.com/a/10261449 or similar doesn't work properly*/
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        getPresenter().putWeatherBundle(outState);
    }

    @Override
    public void onStart() {
        super.onStart();
        getPresenter().registerNetworkReceiver();
    }

    @Override
    public void onStop() {
        super.onStop();
        getPresenter().unregisterReceiver();
    }
}
