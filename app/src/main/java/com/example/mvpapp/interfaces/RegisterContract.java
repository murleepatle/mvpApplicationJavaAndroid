package com.example.mvpapp.interfaces;

public interface RegisterContract {
    /**
     * Represents the Presenter in MVP for register user.
     */
    interface IRegisterPresenter {
        void performRegistration(String fullName, String userId, String email, String password, String confirmPassword, String mobileNo, String address);
    }

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
}
