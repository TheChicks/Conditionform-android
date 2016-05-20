package com.thechicks.conditionform.data.remote;

import com.google.gson.JsonArray;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by opklnm102 on 2016-04-29.
 */
public interface BackendService {

    //보험코드, 약품명 검색
    //ex) pills/search?word=아프릴정
    //    pills/search?word=651501090
    @Headers("Accept: application/json")
    @GET("pills/search")
    Call<JsonArray> getPillInformation(@Query(value = "word", encoded = true) String searchWord);

    //처방전 사진 보내서 Ocr Result 결과 받기
    @Multipart
    @POST("ocr")
    Call<JsonArray> getOcrResult(@Part MultipartBody.Part file);

}
