package com.example.mvpapp.interfaces;

/**
 * This is Interface class all view and presenter method hold here
 */
public interface LoginContract {
    /**
     * Represents the View in MVP.
     */
    interface ILoginView {
        void onLoginSuccessfully(String userId);

        void onLoginFailed(int errorMsgResourceId);

        void onUserIdFieldError(int errorMsgResourceId);

        void onPasswordFieldError(int errorMsgResourceId);
    }

    /**
     * Represents the Presenter in MVP for login user.
     */
    interface ILoginPresenter {
        void performLoginProcess(String userId, String password);
    }


}
