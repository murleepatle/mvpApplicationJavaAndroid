package com.example.mvpapp.ui.register;

import com.example.mvpapp.R;
import com.example.mvpapp.repository.InputValidationRepository;
import com.example.mvpapp.repository.UserDataSqliteRepository;

/**
 *This class is Presenter class this class is hold all business logic for Register Operations.
 */
public class RegisterPresenter implements RegisterContract.Presenter {
    private final RegisterContract.View view;
    private final InputValidationRepository inputValidationRepository;
    private final UserDataSqliteRepository userDataSqliteRepository;

    public RegisterPresenter(RegisterContract.View view, InputValidationRepository inputValidationRepository, UserDataSqliteRepository userDataSqliteRepository) {
        this.view = view;
        this.inputValidationRepository = inputValidationRepository;
        this.userDataSqliteRepository = userDataSqliteRepository;
    }


    /**
     * This method is use for perform user registration. and then view will be update.
     *
     * @param fullName        full name of user
     * @param userId          unique user Id of user
     * @param email           email address of user
     * @param password        password of user
     * @param confirmPassword confirm password of user
     * @param mobileNo        mobile number of user
     * @param address         postal address of user
     */
    @Override
    public void performRegistration(String fullName, String userId, String email, String password,
                                    String confirmPassword, String mobileNo, String address) {
        if (isUserInputIsValidate(fullName, userId, email, password, confirmPassword, mobileNo, address)) {
            if (userDataSqliteRepository.checkUserExist(userId)) {
                view.onErrorRegister(R.string.error_user_already_exist);
            } else {
                boolean registerResponse = userDataSqliteRepository.registerUser(fullName, userId, email, password, mobileNo, address);
                if (registerResponse) {
                    view.onRegistrationComplete();
                } else {
                    view.onErrorRegister(R.string.error_something_wrong);
                }
            }
        }
    }

    /**
     * This method user for check each user input fill or not,
     *
     * @param fullName        full name of user
     * @param userId          unique user Id of user
     * @param email           email address of user
     * @param password        password of user
     * @param mobileNo        mobile number of user
     * @param address         postal address of user
     * @return true/ false
     */

    private boolean isUserInputIsValidate(String fullName, String userId, String email, String password,
                                          String confirmPassword, String mobileNo, String address) {
        if (inputValidationRepository.emptyFieldValidate(fullName)) {
            view.onFullNameFieldError(R.string.error_full_name);
            return false;
        }
        if (inputValidationRepository.emptyFieldValidate(userId)) {
            view.onUserIdFieldError(R.string.error_user_id);
            return false;
        }
        if (inputValidationRepository.emptyFieldValidate(email)) {
            view.onEmailFieldError(R.string.error_email);
            return false;
        }
        if (inputValidationRepository.emptyFieldValidate(password)) {
            view.onPasswordFieldError(R.string.error_password_empty);
            return false;
        }
        if (inputValidationRepository.emptyFieldValidate(confirmPassword)) {
            view.onConfirmPasswordFieldError(R.string.error_confirm_password_empty);
            return false;
        }

        if (!inputValidationRepository.matchPasswordWithConfirmPassword(password, confirmPassword)) {
            view.onConfirmPasswordFieldError(R.string.error_match_password);
            return false;
        }
        if (inputValidationRepository.emptyFieldValidate(mobileNo)) {
            view.onMobileNoFieldError(R.string.error_mobile_number_empty);
            return false;
        }
        if (inputValidationRepository.emptyFieldValidate(address)) {
            view.onAddressFieldError(R.string.error_address_empty);
            return false;
        }

        return true;
    }

}
