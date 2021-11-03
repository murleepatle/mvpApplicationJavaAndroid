package com.example.mvpapp.presenter;

import com.example.mvpapp.R;
import com.example.mvpapp.data.model.WeatherDataResponse;
import com.example.mvpapp.data.network.Callback;
import com.example.mvpapp.data.preference.SharedPreferencesManager;
import com.example.mvpapp.data.repository.APICallingRepository;
import com.example.mvpapp.data.repository.InputValidationRepository;
import com.example.mvpapp.interfaces.HomeContract;
import com.example.mvpapp.utility.InternetUtils;

public class HomePresenter implements HomeContract.IHomePresenter {
    APICallingRepository apiCallingRepository;
    InputValidationRepository inputValidationRepository;
    HomeContract.IHomeView view;
    InternetUtils internetUtils;
    public HomePresenter(HomeContract.IHomeView view, InternetUtils internetUtils){
        this.view=view;
        this.apiCallingRepository=APICallingRepository.getInstance();
        this.inputValidationRepository =  InputValidationRepository.getInstance();
        this.internetUtils=internetUtils;

    }
    @Override
    public void fetchWeatherDetailByLocation(String location) {
        view.onProgressStart("Fetching Detail from server.");
        if (inputValidationRepository.emptyFieldValidate(location)){
            view.onErrorInputPin(R.string.please_enter_location);
            view.onProgressEnd();
        }else {
            if (internetUtils.haveNetworkConnection()) {
                apiCallingRepository.getWeatherDetail(location, new Callback<WeatherDataResponse>() {
                    @Override
                    public void returnResult(WeatherDataResponse weatherDataResponse) {
                        view.onWeatherDataFetchSuccessfully(weatherDataResponse);
                        view.onProgressEnd();
                    }

                    @Override
                    public void returnError(String message) {
                        view.onErrorPostOfficeFetch(message);
                        view.onProgressEnd();
                    }
                });
            }else {
                view.onInternetInterrupt();
                view.onProgressEnd();
            }
        }
    }




}
