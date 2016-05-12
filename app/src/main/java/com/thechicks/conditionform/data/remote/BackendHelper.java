package com.thechicks.conditionform.data.remote;

import com.google.gson.JsonArray;
import com.thechicks.conditionform.BuildConfig;
import com.thechicks.conditionform.util.Constants;

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
        
        OkHttpClient okHttpClient = makeOkHttpClient(makeLoggingInterceptor());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        service = retrofit.create(BackendService.class);
    }

    private OkHttpClient makeOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor){
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    // http loging
    private HttpLoggingInterceptor makeLoggingInterceptor(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor()
                .setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY
                        : HttpLoggingInterceptor.Level.NONE);
        return logging;
    }

    public Call<JsonArray> getPillInformation(String searchWord){
        return service.getPillInformation(searchWord);
    }


}
