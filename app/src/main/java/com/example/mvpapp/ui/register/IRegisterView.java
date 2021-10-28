package com.example.mvpapp.ui.register;

/**
 * Represents the View in MVP.
 */
interface IRegisterView {
    void onRegistrationComplete();

    void onErrorRegister(int errorMsgResourceId);

    void onFullNameFieldError(int errorMsgResourceId);

    void onUserIdFieldError(int errorMsgResourceId);

    void onEmailFieldError(int errorMsgResourceId);

    void onPasswordFieldError(int errorMsgResourceId);

    void onConfirmPasswordFieldError(int errorMsgResourceId);

    void onMobileNoFieldError(int errorMsgResourceId);

    void onAddressFieldError(int errorMsgResourceId);
}