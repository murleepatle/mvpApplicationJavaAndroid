package com.example.mvp_application_java.ui.register_screen;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mvp_application_java.R;
import com.example.mvp_application_java.databinding.ActivityRegisterBinding;
import com.example.mvp_application_java.repository.InputValidationRepository;
import com.example.mvp_application_java.repository.UserDataSqliteRepository;
import com.example.mvp_application_java.sqlite_db.UserDbHelper;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.Objects;

/**
 * This activity class is use for perform register operation.
 */
public class RegisterActivity extends AppCompatActivity implements RegisterContract.View {

    ActivityRegisterBinding activityRegisterBinding;
    RegisterPresenter registerPresenter;

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
                Objects.requireNonNull(activityRegisterBinding.fullName.getText()).toString(),
                Objects.requireNonNull(activityRegisterBinding.username.getText()).toString(),
                Objects.requireNonNull(activityRegisterBinding.email.getText()).toString(),
                Objects.requireNonNull(activityRegisterBinding.password.getText()).toString(),
                Objects.requireNonNull(activityRegisterBinding.confirmPassword.getText()).toString(),
                Objects.requireNonNull(activityRegisterBinding.mobileNo.getText()).toString(),
                Objects.requireNonNull(activityRegisterBinding.address.getText()).toString()
        ));
    }

    /**
     * This method use for TextChangedListener for edittext.
     * this method remove the error of TextInputLayout when user change the text value of it.
     *
     */
    private void setUpTextChangedListener() {
        activityRegisterBinding.fullName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                activityRegisterBinding.fullNameTextInputLayout.setError(null);
            }
        });
        activityRegisterBinding.username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                activityRegisterBinding.userNameTextInputLayout.setError(null);
            }
        });
        activityRegisterBinding.email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                activityRegisterBinding.emailTextInputLayout.setError(null);
            }
        });
        activityRegisterBinding.password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                activityRegisterBinding.passwordTextInputLayout.setError(null);
            }
        });
        activityRegisterBinding.confirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                activityRegisterBinding.confirmPasswordTextInputLayout.setError(null);
            }
        });
        activityRegisterBinding.mobileNo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                activityRegisterBinding.mobileTextInputLayout.setError(null);
            }
        });
        activityRegisterBinding.address.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                activityRegisterBinding.addressTextInputLayout.setError(null);
            }
        });

    }

    /**
     *
     * this  method is override method that is called when user register successfully in Sqlite DB
     */
    @Override
    public void onRegistrationComplete() {
        Toast.makeText(this, R.string.register_successfully_msg, Toast.LENGTH_SHORT).show();
        onBackPressed();
    }

    /**
     * This method is override method that is call when user registration gone to failed.
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