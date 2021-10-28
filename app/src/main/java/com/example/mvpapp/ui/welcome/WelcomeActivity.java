package com.example.mvpapp.ui.welcome;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mvpapp.R;
import com.example.mvpapp.ui.login.LoginActivity;

/**
 * This is the Welcome screen that call when our app is open.
 * this activity show the splash on starting of the app util app is going to visible to user
 */
public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
        // close splash activity
        finish();

    }
}