package com.retrofit.apifilm.Api;

import com.retrofit.apifilm.Model.FilmMainResponseItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestApi {
    @GET("/shows")
    Call<List<FilmMainResponseItem>> getData();

}
