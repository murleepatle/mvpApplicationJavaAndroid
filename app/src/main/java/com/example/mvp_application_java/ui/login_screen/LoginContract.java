package com.example.mvp_application_java.ui.login_screen;

/**
 * This is Interface class all view and presenter method hold here
 */
public interface LoginContract {
    /**
     * Represents the View in MVP.
     */
    interface View {
        void onLoginSuccessfully(String userId);

        void onLoginFailed(int errorMsgResourceId);

        void onUserIdFieldError(int errorMsgResourceId);

        void onPasswordFieldError(int errorMsgResourceId);
    }

    /**
     * Represents the Presenter in MVP for login user.
     */
    interface Presenter {
        void performLoginProcess(String userId, String password);
    }
}
