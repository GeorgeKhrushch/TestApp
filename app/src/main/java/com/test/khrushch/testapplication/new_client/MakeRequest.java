package com.test.khrushch.testapplication.new_client;

import io.reactivex.Single;
import okhttp3.ResponseBody;

public interface MakeRequest {

    Single<ResponseBody> makeRequest();
}
