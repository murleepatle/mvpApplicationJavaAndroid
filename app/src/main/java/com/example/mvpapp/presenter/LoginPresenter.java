package com.example.mvpapp.presenter;

import com.example.mvpapp.R;
import com.example.mvpapp.data.repository.InputValidationRepository;
import com.example.mvpapp.data.repository.UserDataSqliteRepository;
import com.example.mvpapp.interfaces.LoginContract;

/**
 * This class is Presenter class this class is hold all business logic for Login Operations.
 */
public class LoginPresenter implements LoginContract.ILoginPresenter {
    private final LoginContract.ILoginView view;
    private final InputValidationRepository inputValidationRepository;
    private final UserDataSqliteRepository userDataSqliteRepository;

    public LoginPresenter(LoginContract.ILoginView view, InputValidationRepository inputValidationRepository, UserDataSqliteRepository userDataSqliteRepository) {
        this.view = view;
        this.inputValidationRepository = inputValidationRepository;
        this.userDataSqliteRepository = userDataSqliteRepository;
    }


    /**
     * This method user for check each user input fill or not,
     *
     * @param userId   unique user Id of user
     * @param password password of user
     * @return true/ false
     */

    private boolean isUserInputIsValidate(String userId, String password) {

        if (inputValidationRepository.emptyFieldValidate(userId)) {
            view.onUserIdFieldError(R.string.error_user_id);
            return false;
        }

        if (inputValidationRepository.emptyFieldValidate(password)) {
            view.onPasswordFieldError(R.string.error_password_empty);
            return false;
        }

        return true;
    }


    /**
     * this method is use for login user.
     *
     * @param userId   this user unique id
     * @param password this is password of the user
     */
    @Override
    public void performLoginProcess(String userId, String password) {
        if (isUserInputIsValidate(userId, password)) {
            boolean userLoginResponse = userDataSqliteRepository.checkUserCredentials(userId, password);
            if (userLoginResponse) {
                view.onLoginSuccessfully(userId);
            } else {
                view.onLoginFailed(R.string.error_invalid_user);
            }
        }
    }
}
