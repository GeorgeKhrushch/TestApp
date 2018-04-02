package com.test.khrushch.testapplication.di.modules;


import com.test.khrushch.testapplication.MainActivity;
import com.test.khrushch.testapplication.di.scope.MainActivityScope;
import com.test.khrushch.testapplication.ui.presenters.ActivityPresenter;
import com.test.khrushch.testapplication.ui.presenters.ActivityPresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    private MainActivity mActivity;

    public MainActivityModule(MainActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @MainActivityScope
    public MainActivity getActivity(){
        return mActivity;
    }

    @Provides
    @MainActivityScope
    public ActivityPresenter getActivityPresenter() {
        return new ActivityPresenterImpl(mActivity);
    }
}
