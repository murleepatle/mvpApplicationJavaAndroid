package com.example.mvpapp.ui.dashboard;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.mvpapp.R;
import com.example.mvpapp.data.model.UserDetail;
import com.example.mvpapp.data.preference.SharedPreferencesManager;
import com.example.mvpapp.data.repository.UserDataSqliteRepository;
import com.example.mvpapp.data.sqlite.UserDbHelper;
import com.example.mvpapp.databinding.ActivityMainBinding;
import com.example.mvpapp.interfaces.MainActivityContract;
import com.example.mvpapp.presenter.MainActivityPresenter;
import com.example.mvpapp.ui.welcome.WelcomeActivity;
import com.example.mvpapp.utility.ConstantValue;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity implements MainActivityContract.IMainActivityView {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private MainActivityPresenter presenter;
    private ProgressDialog progressBar;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        progressBar = new ProgressDialog(this);
        progressBar.setCancelable(false);
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        setSupportActionBar(binding.appBarMain.toolbar);
        presenter = new MainActivityPresenter(this, SharedPreferencesManager.getInstance(this), UserDataSqliteRepository.getInstance(new UserDbHelper(this)));
        DrawerLayout drawer = binding.drawerLayout;
        navigationView = binding.navView;
        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        NavigationUI.setupWithNavController(binding.appBarMain.bottomNavigationView, navController);

        presenter.fetchLoginUser();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration) || super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.logout_btn) {
            new MaterialAlertDialogBuilder(this)
                    .setTitle(R.string.warning)
                    .setMessage(R.string.are_you_sure)
                    .setPositiveButton(R.string.yes, (dialog, which) -> {
                        presenter.logoutUser();
                    }).setNegativeButton(R.string.no, (dialog, which) -> {
            }).show();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onProgressStart(String messageProgress) {
        progressBar.setMessage(messageProgress);
        progressBar.show();
    }

    @Override
    public void onProgressEnd() {
        progressBar.hide();
    }

    @Override
    public void onUserLogout() {
        Intent intent = new Intent(this, WelcomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public void onUserDetail(UserDetail userDetail) {
        if (userDetail != null) {
            View hView = navigationView.getHeaderView(0);
            TextView navUser = hView.findViewById(R.id.user_name_tv);
            TextView navEmail = hView.findViewById(R.id.email_tv);
            navUser.setText(userDetail.getFullName());
            navEmail.setText(userDetail.getEmail());
        }else {
            presenter.logoutUser();
        }
    }
}