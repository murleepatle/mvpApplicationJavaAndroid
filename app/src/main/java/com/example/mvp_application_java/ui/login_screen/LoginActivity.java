package com.example.mvp_application_java.ui.login_screen;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mvp_application_java.R;
import com.example.mvp_application_java.databinding.ActivityLoginBinding;
import com.example.mvp_application_java.repository.InputValidationRepository;
import com.example.mvp_application_java.repository.UserDataSqliteRepository;
import com.example.mvp_application_java.sqlite_db.UserDbHelper;
import com.example.mvp_application_java.ui.dashboard.MainActivity;
import com.example.mvp_application_java.ui.register_screen.RegisterActivity;
import com.example.mvp_application_java.utility_class.ConstantValue;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.Objects;

/**
 * This activity class is use for perform login operation.
 */
public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    ActivityLoginBinding activityLoginBinding;
    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLoginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(activityLoginBinding.getRoot());
        setTitle(R.string.user_login);
        loginPresenter = new LoginPresenter(this, InputValidationRepository.getInstance(), UserDataSqliteRepository.getInstance(new UserDbHelper(this)));
        setupClickListener();
        setUpTextChangedListener();
    }

    /**
     * This method use for TextChangedListener for edittext.
     * this method remove the error of TextInputLayout when user change the text value of it.
     *
     */
    private void setUpTextChangedListener() {
        activityLoginBinding.username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                activityLoginBinding.userNameTextInputLayout.setError(null);
            }
        });
        activityLoginBinding.password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                activityLoginBinding.passwordTextInputLayout.setError(null);
            }
        });
    }

    /**
     * This method is handle click listener of all button .
     */
    private void setupClickListener() {
        activityLoginBinding.loginButton.setOnClickListener(v -> loginPresenter.performLoginProcess(
                Objects.requireNonNull(activityLoginBinding.username.getText()).toString(),
                Objects.requireNonNull(activityLoginBinding.password.getText()).toString()
        ));
        activityLoginBinding.buttonRegister.setOnClickListener(v -> startActivity(new Intent(this, RegisterActivity.class)));
    }

    /**
     * this  method is override method that is called when user login successfully in Sqlite DB
     * @param userId user id return that is login user identity.
     */
    @Override
    public void onLoginSuccessfully(String userId) {
        Toast.makeText(this, R.string.user_login_successfully, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(ConstantValue.USER_ID_INTENT, userId);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    /**
     * This method is override method that is call when user login gone to failed.
     * @param errorMsgResourceId this message that is send by the login response.
     */
    @Override
    public void onLoginFailed(int errorMsgResourceId) {
        new MaterialAlertDialogBuilder(this)
                .setMessage(getString(errorMsgResourceId))
                .setNeutralButton(R.string.ok, (dialog, which) -> {
                }).show();
    }

    /**
     * This method is override method that is called when user field has some wrong input.
     * @param errorMsgResourceId this is the error message.
     */
    @Override
    public void onUserIdFieldError(int errorMsgResourceId) {
        activityLoginBinding.userNameTextInputLayout.setError(getString(errorMsgResourceId));
    }

    /**
     * This method is override method that is called when password field has some wrong input.
     * @param errorMsgResourceId this is the error message.
     */
    @Override
    public void onPasswordFieldError(int errorMsgResourceId) {
        activityLoginBinding.passwordTextInputLayout.setError(getString(errorMsgResourceId));
    }
}