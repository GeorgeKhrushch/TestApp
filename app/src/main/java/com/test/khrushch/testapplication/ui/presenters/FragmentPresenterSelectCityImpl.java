package com.test.khrushch.testapplication.ui.presenters;

import android.os.Bundle;

import com.test.khrushch.testapplication.new_client.client_impl.RequestManager;
import com.test.khrushch.testapplication.preferences.SharedPrefsUtils;
import com.test.khrushch.testapplication.structure.CityInfo;
import com.test.khrushch.testapplication.ui.FragmentNamesConstants;
import com.test.khrushch.testapplication.ui.views.FragmentViewSelectCity;

import java.util.ArrayList;
import java.util.List;

import static com.test.khrushch.testapplication.static_data.CitiesStaticInfo.DEFAULT_CITY_ID;
import static com.test.khrushch.testapplication.static_data.CitiesStaticInfo.getCitiesInfoWithoutId;

public class FragmentPresenterSelectCityImpl extends FragmentPresenterBaseImpl {
    private static final String CITIES_BUNDLE_KEY = "cities_bundle_key";
    private List<CityInfo> cityInfoList;

    public FragmentPresenterSelectCityImpl(FragmentViewSelectCity view, ActivityPresenter activityPresenter,
                                           RequestManager requestManager) {
        super(view, activityPresenter, requestManager);
    }

    @Override
    public void loadData(Bundle bundle) {
        if(bundle != null){
            cityInfoList = getCityInfoFromBundle(bundle);
            getView().setInfoToAdapter(cityInfoList, getCloseFragmentImpl());
        }else
            loadData();
    }

    private void loadData(){
        int cityId = SharedPrefsUtils.getLastCityId(view.getFragment().getContext(), DEFAULT_CITY_ID);
        cityInfoList = getCitiesInfoWithoutId(cityId);
        getView().setInfoToAdapter(cityInfoList, getCloseFragmentImpl());
    }

    private CloseFragment getCloseFragmentImpl() {
        return () ->
            activityPresenter
                    .closeFragment(view.getFragment(), FragmentNamesConstants.FRAGMENT_MAIN);
    }

    @Override
    public FragmentViewSelectCity getView() {
        return (FragmentViewSelectCity) view;
    }

    public Bundle getCitiesBundle(Bundle outState){
        if(cityInfoList != null)
            outState.putParcelableArrayList(CITIES_BUNDLE_KEY, new ArrayList<>(cityInfoList));
        return outState;
    }

    private List<CityInfo> getCityInfoFromBundle(Bundle bundle){
        return bundle.getParcelableArrayList(CITIES_BUNDLE_KEY);
    }
}
