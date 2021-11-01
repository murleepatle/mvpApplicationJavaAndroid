package com.example.mvpapp.data.network;

import com.example.mvpapp.data.model.WeatherDataResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("v1/current.json")
    Call<WeatherDataResponse> getWeatherDetail(@Query("key") String key, @Query("q") String query);
}