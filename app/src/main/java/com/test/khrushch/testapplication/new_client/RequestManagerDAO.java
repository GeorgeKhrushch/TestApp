package com.test.khrushch.testapplication.new_client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.test.khrushch.testapplication.new_client.exception.JsonException;
import com.test.khrushch.testapplication.new_client.exception.ParseException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class RequestManagerDAO {

    private static final Gson gson = new GsonBuilder().create();

    public static  <T> Single<T> single(MakeRequest request, Class<T> clazz){
        return makeSingleObjectRequest(request, clazz);
    }

    public static <T> Single<List<T>> list(MakeRequest request, Class<T> clazz){
        return makeListRequest(request, clazz);
    }

    public static Single<JSONObject> json(MakeRequest request){
        return makeJsonResultRequest(request);
    }

    private static <L> Single<List<L>> makeListRequest(MakeRequest request, Class<L> lClass){
        return makeRequest(request).map(responseBody ->
                remapListResponse(responseBody.string(), lClass));
    }

    static <L> List<L> remapListResponse(String response, Class<L> lClass) throws Exception {
        try {
            JSONArray array = new JSONArray(response);
            List<L> list = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                String object = array.get(i).toString();
                System.out.println("JSON " + object);
                list.add(gson.fromJson(object, lClass));
            }

            return list;
        }catch (JSONException e){
            e.printStackTrace();
            throw new ParseException(response);
        }
    }

    private static  <L> Single<L> makeSingleObjectRequest(MakeRequest request, Class<L> lClass){
        return makeRequest(request).map(responseBody ->{
                String response = responseBody.string();
                return remapSingleResponse(response, lClass);
        });
    }

    static <L> L remapSingleResponse(String response, Class<L> lClass) throws ParseException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        try {
            JSONObject object = new JSONObject(response);
            return gson.fromJson(object.toString(), lClass);
        }catch (JSONException e){
            e.printStackTrace();
            throw new ParseException(response);
        }
    }

    private static Single<JSONObject> makeJsonResultRequest(MakeRequest request){
        return makeRequest(request).map(RequestManagerDAO::responseBodyToJson);
    }

    static JSONObject responseBodyToJson(ResponseBody responseBody) throws JsonException {
        try {
            return new JSONObject(responseBody.string());
        }catch (Exception e){
            e.printStackTrace();
            throw new JsonException(e.getCause());
        }
    }

    private static Single<ResponseBody> makeRequest(MakeRequest request){
        return request
                .makeRequest()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
