package com.example.mvpapp.data.repository;

import android.content.res.Resources;

import androidx.annotation.NonNull;

import com.example.mvpapp.R;
import com.example.mvpapp.data.model.PostOffice;
import com.example.mvpapp.data.model.PostOfficeResponse;
import com.example.mvpapp.data.network.Callback;
import com.example.mvpapp.data.network.RetrofitClient;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
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
     * This method is use for retrieve post office detail from post office API.
     *
     * @param pinCode  this is postal code of that particular area.
     * @param callback this is callback the response after analyse the api response.
     */
    public void getPinCodeOffice(String pinCode, Callback<List<PostOffice>> callback) {
        Call<ResponseBody> call = RetrofitClient.getInstance().getApiInterface().getPostalDetail(pinCode);
        call.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        PostOfficeResponse postOfficeResponse;
                        String responseStr;
                        try {
                            responseStr = response.body().string();
                            responseStr = responseStr.substring(1, responseStr.length() - 1);

                            postOfficeResponse = new Gson().fromJson(responseStr, PostOfficeResponse.class);

                            if (postOfficeResponse != null) {
                                if (postOfficeResponse.getStatus().equals("Success")) {
                                    if (postOfficeResponse.getPostOffice().size() > 0) {
                                        callback.returnResult(postOfficeResponse.getPostOffice());
                                    } else {
                                        callback.returnError("Post Office List is empty.");
                                    }
                                } else {
                                    callback.returnError(postOfficeResponse.getMessage());
                                }
                            } else {
                                callback.returnError(Resources.getSystem().getString(R.string.error_something_wrong));
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            callback.returnError(e.getMessage());
                        }
                    } else {
                        callback.returnError(Resources.getSystem().getString(R.string.error) + response.code());
                    }
                } else {
                    try {
                        if (response.errorBody() != null) {
                            callback.returnError(response.errorBody().string());
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
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                callback.returnError(t.getMessage());
            }
        });
    }
}
