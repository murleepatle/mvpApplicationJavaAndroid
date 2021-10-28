package com.example.mvpapp.ui.login;

/**
 * Represents the View in MVP.
 */
interface ILoginView {
    void onLoginSuccessfully(String userId);

    void onLoginFailed(int errorMsgResourceId);

    void onUserIdFieldError(int errorMsgResourceId);

    void onPasswordFieldError(int errorMsgResourceId);
}