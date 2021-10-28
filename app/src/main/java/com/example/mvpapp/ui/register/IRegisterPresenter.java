package com.example.mvpapp.ui.register;

/**
 * Represents the Presenter in MVP for register user.
 */
interface IRegisterPresenter {
    void performRegistration(String fullName, String userId, String email, String password, String confirmPassword, String mobileNo, String address);
}