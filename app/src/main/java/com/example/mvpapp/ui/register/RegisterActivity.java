package com.example.mvpapp.ui.register;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mvpapp.R;
import com.example.mvpapp.data.repository.InputValidationRepository;
import com.example.mvpapp.data.repository.UserDataSqliteRepository;
import com.example.mvpapp.data.sqlite.UserDbHelper;
import com.example.mvpapp.databinding.ActivityRegisterBinding;
import com.example.mvpapp.interfaces.RegisterContract;
import com.example.mvpapp.presenter.RegisterPresenter;
import com.example.mvpapp.utility.CustomWatcher;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

/**
 * This activity class is use for perform register operation.
 */
public class RegisterActivity extends AppCompatActivity implements RegisterContract.IRegisterView {

    private ActivityRegisterBinding activityRegisterBinding;
    private RegisterPresenter registerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityRegisterBinding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(activityRegisterBinding.getRoot());
        setTitle(R.string.register);
        registerPresenter = new RegisterPresenter(this, InputValidationRepository.getInstance(), UserDataSqliteRepository.getInstance(new UserDbHelper(this)));
        setupClickListener();
        setUpTextChangedListener();
    }

    /**
     * This method is handle click listener of all button .
     */
    private void setupClickListener() {
        activityRegisterBinding.registerButton.setOnClickListener(v -> registerPresenter.performRegistration(
                activityRegisterBinding.fullName.getText().toString(),
                activityRegisterBinding.username.getText().toString(),
                activityRegisterBinding.email.getText().toString(),
                activityRegisterBinding.password.getText().toString(),
                activityRegisterBinding.confirmPassword.getText().toString(),
                activityRegisterBinding.mobileNo.getText().toString(),
                activityRegisterBinding.address.getText().toString()
        ));
    }

    /**
     * This method use for TextChangedListener for edittext.
     * this method remove the error of TextInputLayout when user change the text value of it.
     */
    private void setUpTextChangedListener() {
        activityRegisterBinding.fullName.addTextChangedListener(new CustomWatcher(activityRegisterBinding.fullNameTextInputLayout));
        activityRegisterBinding.username.addTextChangedListener(new CustomWatcher(activityRegisterBinding.userNameTextInputLayout));
        activityRegisterBinding.email.addTextChangedListener(new CustomWatcher(activityRegisterBinding.emailTextInputLayout));
        activityRegisterBinding.password.addTextChangedListener(new CustomWatcher(activityRegisterBinding.passwordTextInputLayout));
        activityRegisterBinding.confirmPassword.addTextChangedListener(new CustomWatcher(activityRegisterBinding.confirmPasswordTextInputLayout));
        activityRegisterBinding.mobileNo.addTextChangedListener(new CustomWatcher(activityRegisterBinding.mobileTextInputLayout));
        activityRegisterBinding.address.addTextChangedListener(new CustomWatcher(activityRegisterBinding.addressTextInputLayout));
    }

    /**
     * this  method is override method that is called when user register successfully in Sqlite DB
     */
    @Override
    public void onRegistrationComplete() {
        Toast.makeText(this, R.string.register_successfully_msg, Toast.LENGTH_SHORT).show();
        onBackPressed();
    }

    /**
     * This method is override method that is call when user registration gone to failed.
     *
     * @param errorMsgResourceId this message that is send by the registration response.
     */
    @Override
    public void onErrorRegister(int errorMsgResourceId) {
        new MaterialAlertDialogBuilder(this)
                .setMessage(getString(errorMsgResourceId))
                .setNeutralButton(R.string.ok, (dialog, which) -> {
                }).show();
    }

    @Override
    public void onFullNameFieldError(int errorMsgResourceId) {
        activityRegisterBinding.fullNameTextInputLayout.setError(getString(errorMsgResourceId));
    }

    @Override
    public void onUserIdFieldError(int errorMsgResourceId) {
        activityRegisterBinding.userNameTextInputLayout.setError(getString(errorMsgResourceId));
    }

    @Override
    public void onEmailFieldError(int errorMsgResourceId) {
        activityRegisterBinding.emailTextInputLayout.setError(getString(errorMsgResourceId));
    }

    @Override
    public void onPasswordFieldError(int errorMsgResourceId) {
        activityRegisterBinding.passwordTextInputLayout.setError(getString(errorMsgResourceId));
    }

    @Override
    public void onConfirmPasswordFieldError(int errorMsgResourceId) {
        activityRegisterBinding.confirmPasswordTextInputLayout.setError(getString(errorMsgResourceId));
    }

    @Override
    public void onMobileNoFieldError(int errorMsgResourceId) {
        activityRegisterBinding.mobileTextInputLayout.setError(getString(errorMsgResourceId));
    }

    @Override
    public void onAddressFieldError(int errorMsgResourceId) {
        activityRegisterBinding.addressTextInputLayout.setError(getString(errorMsgResourceId));
    }
}