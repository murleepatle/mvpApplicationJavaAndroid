package com.example.mvpapp.data.repository;

import android.content.res.Resources;

import androidx.annotation.NonNull;

import com.example.mvpapp.R;
import com.example.mvpapp.data.model.WeatherDataResponse;
import com.example.mvpapp.data.network.Callback;
import com.example.mvpapp.data.network.RetrofitClient;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

/**
 * This class is use for Rest Api Calling.
 */
public class APICallingRepository {
    private static APICallingRepository instance;

    public static APICallingRepository getInstance() {
        if (instance == null) {
            instance = new APICallingRepository();
        }
        return instance;
    }

    /**
     * This method is use for retrieve weather detail from post office API.
     *
     * @param query  this is query of that particular area.
     * @param callback this is callback the response after analyse the api response.
     */
    public void getWeatherDetail(String query, Callback<WeatherDataResponse> callback) {
        Call<WeatherDataResponse> call = RetrofitClient.getInstance().getApiInterface().getWeatherDetail("1d2143b52d61412080f72448210111",query);
        call.enqueue(new retrofit2.Callback<WeatherDataResponse>() {
            @Override
            public void onResponse(@NonNull Call<WeatherDataResponse> call, @NonNull Response<WeatherDataResponse> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        WeatherDataResponse weatherDataResponse = response.body();
                            if (weatherDataResponse != null) {
                                callback.returnResult(weatherDataResponse);
                            } else {
                                callback.returnError(Resources.getSystem().getString(R.string.error_something_wrong));
                            }

                    } else if (response.code() == 400){
                        WeatherDataResponse weatherDataResponse = response.body();
                        if (weatherDataResponse != null) {
                            callback.returnError(weatherDataResponse.getError().getMessage());
                        }else {
                            callback.returnError(Resources.getSystem().getString(R.string.error_something_wrong));
                        }

                    }else {
                        callback.returnError(Resources.getSystem().getString(R.string.error) + response.code());
                    }
                } else {
                    try {
                        if (response.errorBody() != null) {
                            WeatherDataResponse weatherDataResponse = new Gson().fromJson(response.errorBody().string(),WeatherDataResponse.class);

                            callback.returnError(weatherDataResponse.getError().getMessage());
                        } else {
                            callback.returnError(Resources.getSystem().getString(R.string.error_something_wrong));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        callback.returnError(e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<WeatherDataResponse> call, @NonNull Throwable t) {
                callback.returnError(t.getMessage());
            }
        });
    }
}
