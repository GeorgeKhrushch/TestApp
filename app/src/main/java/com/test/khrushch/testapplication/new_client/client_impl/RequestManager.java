package com.test.khrushch.testapplication.new_client.client_impl;

import com.google.gson.Gson;
import com.test.khrushch.testapplication.new_client.MakeRequest;
import com.test.khrushch.testapplication.new_client.RequestManagerDAO;
import com.test.khrushch.testapplication.structure.WeatherInfo;

import org.json.JSONObject;

import java.util.List;

import io.reactivex.Single;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import static com.test.khrushch.testapplication.TestApplication.APP_ID;

public class RequestManager {

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    private RequestsService service;
    private Gson gson = new Gson();

    public RequestManager(RequestsService service) {
        this.service = service;
    }

    /* GET REQUESTS*/

    public  Single<WeatherInfo> getWeatherInfo(int cityId){
        return getRequestWithSingleObjectResult(getWeatherRequestParams(cityId), WeatherInfo.class);
    }

    private String getWeatherRequestParams(int cityId){
        StringBuilder builder = new StringBuilder("weather?");
        builder.append("id=").append(cityId)
                .append("&units=metric")
                .append("&appid=").append(APP_ID)
                .append("&lang=ru");
        return builder.toString();
    }


/*Model*/

    private <T> Single<T> getRequestWithSingleObjectResult(String endpoint, Class<T> clazz) {
        return paramsRequestWithSingleObjectResult(()->
                service.makeGetRequestWithUrlEndpoint(endpoint), clazz);
    }

    private  <T> Single<T> paramsRequestWithSingleObjectResult(MakeRequest request, Class<T> clazz){
        return RequestManagerDAO.single(request, clazz);
    }

    private <T> Single<List<T>> getRequestWithListObjectResult(String endpoint, Class<T> clazz) {
        return RequestManagerDAO.list(()->
                service.makeGetRequestWithUrlEndpoint(endpoint), clazz);
    }

    private Single<JSONObject> getJson(MakeRequest request){
        return RequestManagerDAO.json(request);
    }

    private RequestBody getBodyFromObject(Object object){
        return RequestBody.create(JSON, gson.toJson(object));
    }
}
