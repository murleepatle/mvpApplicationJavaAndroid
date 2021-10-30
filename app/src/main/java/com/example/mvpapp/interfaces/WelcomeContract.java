package com.example.mvpapp.interfaces;

public interface WelcomeContract {
    /**
     * Represents the Presenter in MVP for welcome.
     */
    interface IWelcomePresenter {
        void fetchNavigationState();
    }

    /**
     * Represents the View in MVP.
     */
    interface IWelcomeView {
        void goOnDashBoard();
        void goOnLoginPage();

    }
}
