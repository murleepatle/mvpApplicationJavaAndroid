package com.example.mvp_application_java.ui.dashboard;

import static com.example.mvp_application_java.utility_class.ConstantValue.USER_ID_INTENT;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mvp_application_java.R;

/**
 * MainActivity Class is a dashboard class that will show user detail main page
 *
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String userId = getIntent().getStringExtra(USER_ID_INTENT);


    }
}