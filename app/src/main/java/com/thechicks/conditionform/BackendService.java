package com.thechicks.conditionform;

import com.google.gson.JsonArray;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by opklnm102 on 2016-04-29.
 */
public interface BackendService {

    //보험코드, 약품명 검색
    //pillInformations/search?word=타이레놀
    @Headers("Accept: application/json")
    @GET("pillInformations/search")
    Call<JsonArray> getPillInformation(@Query("word") String searchWord);

}
