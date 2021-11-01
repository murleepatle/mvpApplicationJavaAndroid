package com.example.mvpapp.presenter;

import com.example.mvpapp.data.preference.SharedPreferencesManager;
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
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (sharedPreferencesManager.isUserLogin()){
            view.goOnDashBoard();
        }else {
            view.goOnLoginPage();
        }
    }
}
