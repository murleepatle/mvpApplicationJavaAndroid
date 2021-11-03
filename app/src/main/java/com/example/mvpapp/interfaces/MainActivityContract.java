package com.example.mvpapp.interfaces;

import com.example.mvpapp.data.model.UserDetail;

public interface MainActivityContract {
    /**
     * Represents the Presenter in MVP for dashboard.
     */
    interface IMainActivityPresenter {
        void logoutUser();
        void fetchLoginUser();
    }

    /**
     * Represents the View in MVP.
     */
    interface IMainActivityView {
        void onUserLogout();
        void onUserDetail(UserDetail userDetail);
        void onProgressStart(String messageProgress);
        void onProgressEnd();
    }
}
