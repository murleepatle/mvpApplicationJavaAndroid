package com.example.mvpapp.ui.dashboard;

import static com.example.mvpapp.utility.ConstantValue.USER_ID_INTENT;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mvpapp.databinding.ActivityMainBinding;

/**
 * MainActivity Class is a dashboard class that will show user detail main page
 *
 */
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        String userId = getIntent().getStringExtra(USER_ID_INTENT);
        activityMainBinding.usernameTv.setText(userId);
    }
}