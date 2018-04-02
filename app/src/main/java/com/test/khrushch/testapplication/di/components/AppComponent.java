package com.test.khrushch.testapplication.di.components;

import com.test.khrushch.testapplication.TestApplication;
import com.test.khrushch.testapplication.di.modules.AppModule;
import com.test.khrushch.testapplication.di.modules.MainActivityModule;
import com.test.khrushch.testapplication.new_client.client_impl.NetModule;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class, NetModule.class})
@Singleton
public interface AppComponent {

    MainActivityComponent plus(MainActivityModule mainActivityModule);
    void inject(TestApplication application);
}
