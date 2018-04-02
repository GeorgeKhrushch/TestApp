package com.test.khrushch.testapplication.new_client.client_impl;

import com.test.khrushch.testapplication.new_client.NetClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class NetModule {

    public static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";

    @Provides
    @Singleton
    public Retrofit getRetrofit(){
        return NetClient.getRetrofit(BASE_URL);
    }

    @Provides
    @Singleton
    public RequestManager getRequestManager(Retrofit retrofit){
        RequestsService service = retrofit.create(RequestsService.class);
        return new RequestManager(service);
    }
}
