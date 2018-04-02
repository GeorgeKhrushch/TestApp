package com.test.khrushch.testapplication.di.modules;

import android.util.Log;

import com.test.khrushch.testapplication.di.scope.FragmentModelScope;
import com.test.khrushch.testapplication.new_client.client_impl.RequestManager;
import com.test.khrushch.testapplication.ui.fragments.FragmentMain;
import com.test.khrushch.testapplication.ui.fragments.FragmentModel;
import com.test.khrushch.testapplication.ui.fragments.FragmentSelectCity;
import com.test.khrushch.testapplication.ui.presenters.ActivityPresenter;
import com.test.khrushch.testapplication.ui.presenters.FragmentPresenterBase;
import com.test.khrushch.testapplication.ui.presenters.FragmentPresenterMainImpl;
import com.test.khrushch.testapplication.ui.presenters.FragmentPresenterSelectCityImpl;
import com.test.khrushch.testapplication.ui.views.FragmentViewMain;
import com.test.khrushch.testapplication.ui.views.FragmentViewSelectCity;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModule {

    private FragmentModel fragmentModel;


    public FragmentModule(FragmentModel fragmentModel) {
        this.fragmentModel = fragmentModel;
    }

    @Provides
    @FragmentModelScope
    public FragmentModel getFragment(){
        return fragmentModel;
    }

    @Provides
    @FragmentModelScope
    public FragmentPresenterBase getPresenter(FragmentModel fragmentModel,
                                              ActivityPresenter activityPresenter,
                                              RequestManager requestManager){
        Log.d("DI", "model is main: " + (fragmentModel instanceof FragmentMain));
        if(fragmentModel instanceof FragmentMain)
            return new FragmentPresenterMainImpl(
                    (FragmentViewMain) fragmentModel, activityPresenter, requestManager);
        if(fragmentModel instanceof FragmentSelectCity)
            return new FragmentPresenterSelectCityImpl(
                    (FragmentViewSelectCity) fragmentModel, activityPresenter, requestManager);
        return null;
    }
}
