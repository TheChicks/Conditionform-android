package com.thechicks.conditionform;

import com.google.gson.JsonArray;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by opklnm102 on 2016-04-29.
 */
public interface BackendService {

    //보험코드 검색
    //pillInformations/insuranceCode/646900690
    @Headers("Accept: application/json")
    @GET("pillInformations/insuranceCode/{pillCode}")
    Call<JsonArray> getPillInformationCode(@Path("pillCode") String pillCode);


    //약품명 검색
    //pillInformations/name/게보린
    @Headers("Accept: application/json")
    @GET("pillInformations/name/{pillName}")
    Call<JsonArray> getPillInformationName(@Path("pillName") String pillName);
}
