package com.test.khrushch.testapplication.new_client;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class NetClient {

    private static OkHttpClient getOkhttpClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);
        httpClient.networkInterceptors().add(chain -> {
            Request.Builder requestBuilder = chain.request().newBuilder();
            requestBuilder.header("Content-Type", "application/json");
            return chain.proceed(requestBuilder.build());
        });
        httpClient.connectTimeout(20, TimeUnit.SECONDS);
        httpClient.readTimeout(20, TimeUnit.SECONDS);
        return httpClient.build();
    }

    public static Retrofit getRetrofit(String baseUrl){
        RxJava2CallAdapterFactory rxAdapter = RxJava2CallAdapterFactory.create();
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                //.addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(rxAdapter)
                .client(getOkhttpClient())
                .build();
    }

}
