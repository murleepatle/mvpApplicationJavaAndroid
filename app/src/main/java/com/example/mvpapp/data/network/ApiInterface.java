package com.example.mvpapp.data.network;

import com.example.mvpapp.data.model.PostOfficeResponse;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("pincode/{pincode}")
    Call<ResponseBody> getPostalDetail(@Path("pincode") String pincode);


}