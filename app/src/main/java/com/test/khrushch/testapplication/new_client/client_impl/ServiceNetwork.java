package com.test.khrushch.testapplication.new_client.client_impl;

import com.test.khrushch.testapplication.new_client.NetClient;

import static com.test.khrushch.testapplication.new_client.client_impl.NetModule.BASE_URL;

public class ServiceNetwork {

    public static RequestManager getRequestManager(){
        return new RequestManager(NetClient.getRetrofit(BASE_URL).create(RequestsService.class));
    }
}
