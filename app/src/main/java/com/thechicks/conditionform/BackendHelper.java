package com.thechicks.conditionform;

import com.google.gson.JsonArray;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by opklnm102 on 2016-04-29.
 */
public class BackendHelper {

    private static BackendHelper instance;
    private BackendService service;

    // http loging
    private HttpLoggingInterceptor mLoggingInterceptor;
    private OkHttpClient mOkHttpClient;

    public static BackendHelper getInstance(){
        if(instance == null){
            synchronized (BackendHelper.class){
                if(instance == null){
                    instance = new BackendHelper();
                }
            }
        }
        return instance;
    }

    private BackendHelper(){
        mLoggingInterceptor = new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BASIC);

        mOkHttpClient = new OkHttpClient.Builder()
                .addInterceptor(mLoggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .client(mOkHttpClient)
                .build();

        service = retrofit.create(BackendService.class);
    }

    public Call<JsonArray> getPillInformationCode(String pillCode){
        return service.getPillInformationCode(pillCode);
    }

    public Call<JsonArray> getPillInformationName(String pillName){
        return service.getPillInformationCode(pillName);
    }
}
