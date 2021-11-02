package com.example.mvpapp.interfaces;

import com.example.mvpapp.data.model.WeatherDataResponse;

public interface DashboardContract {
    /**
     * Represents the Presenter in MVP for dashboard.
     */
    interface IDashboardPresenter {
        void fetchWeatherDetailByLocation(String location);
        void logoutUser();
    }

    /**
     * Represents the View in MVP.
     */
    interface IDashboardView {
        void onWeatherDataFetchSuccessfully(WeatherDataResponse weatherDataResponse);

        void onErrorPostOfficeFetch(String errorMsg);
        void onErrorInputPin(int errorMsgResourceId);
        void onProgressStart(String messageProgress);
        void onProgressEnd();
        void onInternetInterrupt();
        void onUserLogout();
    }
}
