package com.example.mvp_application_java.ui.welcome_screen;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mvp_application_java.R;
import com.example.mvp_application_java.ui.login_screen.LoginActivity;

import java.util.Objects;

/**
 * This is the Welcome screen that call when our app is open.
 * this activity show the splash on starting of the app util app is going to visible to user
 *
 */
public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Objects.requireNonNull(getSupportActionBar()).hide();
        startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
        // close splash activity
        finish();

    }
}