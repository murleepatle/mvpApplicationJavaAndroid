package com.example.mvpapp.repository;

/**
 *
 * This class use for input validation.
 *
 */
public class InputValidationRepository {

    private static InputValidationRepository instance;

    public static InputValidationRepository getInstance() {
        if (instance == null) {
            instance = new InputValidationRepository();
        }
        return instance;
    }

    /**
     * this method is use for validate empty or null value of string
     * @param fieldValue value of field
     * @return true/false
     */
    public boolean emptyFieldValidate(String fieldValue) {
        return fieldValue == null || fieldValue.isEmpty() || fieldValue.equals("null");
    }


    /**
     * this method is use for check two string are equal or not.
     * if both string is null or empty it will return false.
     * when string match it will return true.
     * @param password this is the 1st param like password
     * @param confirmPassword this is the 2nd param like confirm password
     * @return true or false
     */
    public boolean matchPasswordWithConfirmPassword(String password, String confirmPassword) {
        if (emptyFieldValidate(password)) {
            return false;
        }
        if (emptyFieldValidate(confirmPassword)) {
            return false;
        }
        return password.equals(confirmPassword);

    }

}
