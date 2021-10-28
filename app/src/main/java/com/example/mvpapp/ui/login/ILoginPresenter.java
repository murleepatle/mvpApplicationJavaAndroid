package com.example.mvpapp.ui.login;

/**
 * Represents the Presenter in MVP for login user.
 */
interface ILoginPresenter {
    void performLoginProcess(String userId, String password);
}