package com.test.khrushch.testapplication.new_client.client_impl;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface RequestsService {

    @GET()
    Single<ResponseBody> makeGetRequestWithUrlEndpoint(@Url String url);

}
