package com.example.mvpapp.interfaces;

import com.example.mvpapp.data.model.WeatherDataResponse;

public interface HomeContract {
    /**
     * Represents the Presenter in MVP for dashboard.
     */
    interface IHomePresenter {
        void fetchWeatherDetailByLocation(String location);
    }

    /**
     * Represents the View in MVP.
     */
    interface IHomeView {
        void onWeatherDataFetchSuccessfully(WeatherDataResponse weatherDataResponse);
        void onErrorPostOfficeFetch(String errorMsg);
        void onErrorInputPin(int errorMsgResourceId);
        void onProgressStart(String messageProgress);
        void onProgressEnd();
        void onInternetInterrupt();
    }
}
