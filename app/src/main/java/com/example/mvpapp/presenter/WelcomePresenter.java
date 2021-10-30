package com.example.mvpapp.presenter;

import com.example.mvpapp.data.prefrense.SharedPreferencesManager;
import com.example.mvpapp.interfaces.WelcomeContract;

public class WelcomePresenter implements WelcomeContract.IWelcomePresenter {
    WelcomeContract.IWelcomeView view;
    SharedPreferencesManager sharedPreferencesManager;

    public WelcomePresenter(WelcomeContract.IWelcomeView view, SharedPreferencesManager sharedPreferencesManager) {
        this.view=view;
        this.sharedPreferencesManager=sharedPreferencesManager;
    }

    @Override
    public void fetchNavigationState() {
        if (sharedPreferencesManager.isUserLogin()){
            view.goOnDashBoard();
        }else {
            view.goOnLoginPage();
        }
    }
}
