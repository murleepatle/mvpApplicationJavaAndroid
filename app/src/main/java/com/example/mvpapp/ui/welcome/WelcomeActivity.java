package com.example.mvpapp.ui.welcome;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mvpapp.R;
import com.example.mvpapp.data.preference.SharedPreferencesManager;
import com.example.mvpapp.interfaces.WelcomeContract;
import com.example.mvpapp.presenter.WelcomePresenter;
import com.example.mvpapp.ui.dashboard.MainActivity;
import com.example.mvpapp.ui.login.LoginActivity;

/**
 * This is the Welcome screen that call when our app is open.
 * this activity show the splash on starting of the app util app is going to visible to user
 */
public class WelcomeActivity extends AppCompatActivity implements WelcomeContract.IWelcomeView {
    WelcomePresenter welcomePresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        welcomePresenter=new WelcomePresenter(this,SharedPreferencesManager.getInstance(this));
        welcomePresenter.fetchNavigationState();
    }

    @Override
    public void goOnDashBoard() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public void goOnLoginPage() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}