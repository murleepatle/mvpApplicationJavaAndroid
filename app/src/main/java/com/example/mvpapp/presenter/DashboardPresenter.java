package com.example.mvpapp.presenter;

import com.example.mvpapp.R;
import com.example.mvpapp.data.model.PostOffice;
import com.example.mvpapp.data.network.Callback;
import com.example.mvpapp.data.repository.APICallingRepository;
import com.example.mvpapp.data.repository.InputValidationRepository;
import com.example.mvpapp.interfaces.DashboardContract;

import java.util.List;

public class DashboardPresenter implements DashboardContract.IDashboardPresenter {
    APICallingRepository apiCallingRepository;
    InputValidationRepository inputValidationRepository;
    DashboardContract.IDashboardView view;

    public DashboardPresenter(DashboardContract.IDashboardView view){
        this.view=view;
        this.apiCallingRepository=APICallingRepository.getInstance();
        this.inputValidationRepository =  InputValidationRepository.getInstance();

    }
    @Override
    public void fetchPostOfficeDetailByPinCode(String pinCode) {
        view.onProgressStart("Fetching Detail from server.");
        if (inputValidationRepository.emptyFieldValidate(pinCode)){
            view.onErrorInputPin(R.string.please_enter_pin_code);
            view.onProgressEnd();
        }else {
            apiCallingRepository.getPinCodeOffice(pinCode, new Callback<List<PostOffice>>() {
                @Override
                public void returnResult(List<PostOffice> postOffices) {
                    view.onPostOfficeFetchSuccessfully(postOffices);
                    view.onProgressEnd();
                }

                @Override
                public void returnError(String message) {
                    view.onErrorPostOfficeFetch(message);
                    view.onProgressEnd();
                }
            });
        }
    }
}
