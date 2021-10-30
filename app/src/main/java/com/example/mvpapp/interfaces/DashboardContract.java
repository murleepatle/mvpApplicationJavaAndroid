package com.example.mvpapp.interfaces;

import com.example.mvpapp.data.model.PostOffice;

import java.util.List;

public interface DashboardContract {
    /**
     * Represents the Presenter in MVP for dashboard.
     */
    interface IDashboardPresenter {
        void fetchPostOfficeDetailByPinCode(String pinCode);
    }

    /**
     * Represents the View in MVP.
     */
    interface IDashboardView {
        void onPostOfficeFetchSuccessfully(List<PostOffice> postOffices);

        void onErrorPostOfficeFetch(String errorMsg);
        void onErrorInputPin(int errorMsgResourceId);
        void onProgressStart(String messageProgress);
        void onProgressEnd();
    }
}
