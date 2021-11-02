package com.example.mvpapp.ui.login;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.mvpapp.R;
import com.example.mvpapp.data.preference.SharedPreferencesManager;
import com.example.mvpapp.data.repository.UserDataSqliteRepository;
import com.example.mvpapp.data.sqlite.UserDbHelper;
import com.example.mvpapp.databinding.ActivityLoginBinding;
import com.example.mvpapp.interfaces.LoginContract;
import com.example.mvpapp.presenter.LoginPresenter;
import com.example.mvpapp.ui.dashboard.DashboardActivity;
import com.example.mvpapp.ui.dashboard.MainActivity;
import com.example.mvpapp.ui.register.RegisterActivity;
import com.example.mvpapp.utility.ConstantValue;
import com.example.mvpapp.utility.CustomWatcher;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

/**
 * This activity class is use for perform login operation.
 */
public class LoginActivity extends AppCompatActivity implements LoginContract.ILoginView {

    private ActivityLoginBinding activityLoginBinding;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimary));

        }
        activityLoginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(activityLoginBinding.getRoot());
        loginPresenter = new LoginPresenter(this, UserDataSqliteRepository.getInstance(new UserDbHelper(this)), SharedPreferencesManager.getInstance(this));
        setupClickListener();
        setUpTextChangedListener();
    }

    /**
     * This method use for TextChangedListener for edittext.
     * this method remove the error of TextInputLayout when user change the text value of it.
     */
    private void setUpTextChangedListener() {
        activityLoginBinding.userNameTv.addTextChangedListener(new CustomWatcher(activityLoginBinding.userNameTextInputLayout));
        activityLoginBinding.password.addTextChangedListener(new CustomWatcher(activityLoginBinding.passwordTextInputLayout));
    }

    /**
     * This method is handle click listener of all button .
     */
    private void setupClickListener() {
        activityLoginBinding.loginButton.setOnClickListener(v ->
                loginPresenter.performLoginProcess(
                activityLoginBinding.userNameTv.getText().toString(),
                activityLoginBinding.password.getText().toString()
        ));
        activityLoginBinding.registerSlideImg.setOnClickListener(v -> navigateToSignUp());
        activityLoginBinding.registerTv.setOnClickListener(v -> navigateToSignUp());
    }

    public void navigateToSignUp() {
        startActivity(new Intent(this, RegisterActivity.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
    }

    /**
     * this  method is override method that is called when user login successfully in Sqlite DB
     *
     * @param userId user id return that is login user identity.
     */
    @Override
    public void onLoginSuccessfully(String userId) {
        Toast.makeText(this, R.string.user_login_successfully, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    /**
     * This method is override method that is call when user login gone to failed.
     *
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
     *
     * @param errorMsgResourceId this is the error message.
     */
    @Override
    public void onUserIdFieldError(int errorMsgResourceId) {
        activityLoginBinding.userNameTextInputLayout.setError(getString(errorMsgResourceId));
    }

    /**
     * This method is override method that is called when password field has some wrong input.
     *
     * @param errorMsgResourceId this is the error message.
     */
    @Override
    public void onPasswordFieldError(int errorMsgResourceId) {
        activityLoginBinding.passwordTextInputLayout.setError(getString(errorMsgResourceId));
    }
}