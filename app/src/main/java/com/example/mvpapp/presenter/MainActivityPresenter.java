package com.example.mvpapp.presenter;

import com.example.mvpapp.data.preference.SharedPreferencesManager;
import com.example.mvpapp.data.repository.UserDataSqliteRepository;
import com.example.mvpapp.interfaces.MainActivityContract;

public class MainActivityPresenter implements MainActivityContract.IMainActivityPresenter {
    private final SharedPreferencesManager sharedPreferencesManager;
    MainActivityContract.IMainActivityView view;
    UserDataSqliteRepository userDataSqliteRepository;

    public MainActivityPresenter(MainActivityContract.IMainActivityView view, SharedPreferencesManager sharedPreferencesManager, UserDataSqliteRepository userDataSqliteRepository) {
        this.view = view;
        this.sharedPreferencesManager = sharedPreferencesManager;
        this.userDataSqliteRepository = userDataSqliteRepository;
    }

    @Override
    public void logoutUser() {
        view.onProgressStart("Logout");
        sharedPreferencesManager.setIsUserLogin(false);
        view.onProgressEnd();
        view.onUserLogout();
    }

    @Override
    public void fetchLoginUser() {
        view.onUserDetail(userDataSqliteRepository.getUserDetail( sharedPreferencesManager.getUserID()));
    }
}
