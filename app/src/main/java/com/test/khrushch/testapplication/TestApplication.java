package com.test.khrushch.testapplication;

import android.app.Application;
import android.content.Context;

import com.test.khrushch.testapplication.di.components.AppComponent;
import com.test.khrushch.testapplication.di.components.DaggerAppComponent;
import com.test.khrushch.testapplication.di.components.MainActivityComponent;
import com.test.khrushch.testapplication.di.modules.AppModule;
import com.test.khrushch.testapplication.di.modules.FragmentModule;
import com.test.khrushch.testapplication.di.modules.MainActivityModule;
import com.test.khrushch.testapplication.new_client.client_impl.NetModule;
import com.test.khrushch.testapplication.ui.fragments.FragmentModel;

public class TestApplication extends Application {

    public static final String APP_ID = "f8eae2ed5f95240cbee91e93d4f5251f";
    private AppComponent appComponent;
    private MainActivityComponent mainActivityComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initAppComponent();
    }

    private void initAppComponent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule())
                .build();
        appComponent.inject(this);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public void injectMainActivityComponent(MainActivity activity){
        mainActivityComponent =  appComponent.plus(new MainActivityModule(activity));
        mainActivityComponent.inject(activity);
    }

    public void injectFragmentModelComponent(FragmentModel fragmentModel) {
        mainActivityComponent.plusFragmentComponent(
                new FragmentModule(fragmentModel)).inject(fragmentModel);
    }

    public static TestApplication get(Context context) {
        return (TestApplication) context.getApplicationContext();
    }
}
