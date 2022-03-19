package com.retrofit.apifilm.Api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApiClient {

    private RestApi mRestApi;

    public RestApiClient(String baseUrl) {
        OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30,TimeUnit.SECONDS)
                .connectTimeout(3,TimeUnit.SECONDS);

        OkHttpClient okHttpClientObj = httpBuilder.build();

        Retrofit retrofitObj = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClientObj)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mRestApi = retrofitObj.create(RestApi.class);
    }

    public RestApi getRestApiInterface() {
        return mRestApi;  }
}