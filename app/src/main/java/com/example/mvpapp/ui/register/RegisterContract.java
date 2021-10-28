package com.example.mvpapp.ui.register;

/**
 * This is Interface class all view and presenter method hold here
 */
public interface RegisterContract {
    /**
     * Represents the View in MVP.
     */
    interface View {
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

    /**
     * Represents the Presenter in MVP for register user.
     */
    interface Presenter {
        void performRegistration(String fullName, String userId, String email, String password, String confirmPassword, String mobileNo, String address);
    }
}
