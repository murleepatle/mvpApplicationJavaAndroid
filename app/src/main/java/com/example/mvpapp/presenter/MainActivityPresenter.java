package com.example.mvpapp.presenter;

import com.example.mvpapp.data.preference.SharedPreferencesManager;
import com.example.mvpapp.interfaces.MainActivityContract;

public class MainActivityPresenter implements MainActivityContract.IMainActivityPresenter {
    private final SharedPreferencesManager sharedPreferencesManager;
    MainActivityContract.IMainActivityView view;

    public MainActivityPresenter(MainActivityContract.IMainActivityView view, SharedPreferencesManager sharedPreferencesManager) {
        this.view = view;
        this.sharedPreferencesManager = sharedPreferencesManager;
    }

    @Override
    public void logoutUser() {
        view.onProgressStart("Logout");
        sharedPreferencesManager.setIsUserLogin(false);
        view.onProgressEnd();
        view.onUserLogout();
    }
}
