package com.example.mvpapp.interfaces;

public interface MainActivityContract {
    /**
     * Represents the Presenter in MVP for dashboard.
     */
    interface IMainActivityPresenter {
        void logoutUser();
    }

    /**
     * Represents the View in MVP.
     */
    interface IMainActivityView {
        void onUserLogout();
        void onProgressStart(String messageProgress);
        void onProgressEnd();
    }
}
