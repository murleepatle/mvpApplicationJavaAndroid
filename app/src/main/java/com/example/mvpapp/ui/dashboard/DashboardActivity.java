package com.example.mvpapp.ui.dashboard;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mvpapp.R;
import com.example.mvpapp.data.model.WeatherDataResponse;
import com.example.mvpapp.data.preference.SharedPreferencesManager;
import com.example.mvpapp.databinding.ActivityDashboardBinding;
import com.example.mvpapp.interfaces.DashboardContract;
import com.example.mvpapp.presenter.DashboardPresenter;
import com.example.mvpapp.ui.welcome.WelcomeActivity;
import com.example.mvpapp.utility.InternetUtils;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.Collections;

/**
 * MainActivity Class is a dashboard class that will show user detail main page
 */
public class DashboardActivity extends AppCompatActivity implements DashboardContract.IDashboardView {

    private DashboardPresenter dashboardPresenter;
    private WeatherAdapter weatherAdapter;
    private ProgressDialog progressBar ;
    ActivityDashboardBinding activityDashboardBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       activityDashboardBinding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(activityDashboardBinding.getRoot());
        setTitle(getString(R.string.dashboard));
        progressBar = new ProgressDialog(this);
        progressBar.setCancelable(false);
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        int id =  activityDashboardBinding.simpleSearchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        TextView textView = (TextView) activityDashboardBinding.simpleSearchView.findViewById(id);
        final Typeface typeface = ResourcesCompat.getFont(this, R.font.alike);
        textView.setTypeface(typeface);

        dashboardPresenter=new DashboardPresenter(this, SharedPreferencesManager.getInstance(this),new InternetUtils(this));
        weatherAdapter = new WeatherAdapter(new ArrayList<>());
        activityDashboardBinding.recyclerViewPostOffice.setHasFixedSize(true);
        activityDashboardBinding.recyclerViewPostOffice.setLayoutManager(new LinearLayoutManager(this));
        activityDashboardBinding.recyclerViewPostOffice.setAdapter(weatherAdapter);

        activityDashboardBinding.simpleSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                dashboardPresenter.fetchWeatherDetailByLocation(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                weatherAdapter.updateList(new ArrayList<>());
                activityDashboardBinding.emptyImg.setVisibility(View.VISIBLE);

                return false;
            }
        });
        activityDashboardBinding.simpleSearchView.onActionViewExpanded();
    }

    @Override
    public void onWeatherDataFetchSuccessfully(WeatherDataResponse weatherDataResponse) {
        activityDashboardBinding.emptyImg.setVisibility(View.GONE);
        weatherAdapter.updateList(Collections.singletonList(weatherDataResponse));
    }

    @Override
    public void onErrorPostOfficeFetch(String errorMsg) {
        new MaterialAlertDialogBuilder(this)
                .setMessage(errorMsg)
                .setNeutralButton(R.string.ok, (dialog, which) -> {
                }).show();
    }

    @Override
    public void onErrorInputPin(int errorMsgResourceId) {
        Toast.makeText(this, getString(errorMsgResourceId), Toast.LENGTH_SHORT).show();
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
    public void onInternetInterrupt() {
        new MaterialAlertDialogBuilder(this)
                .setTitle(R.string.internet_title)
                .setMessage(R.string.internet_msg)
                .setNeutralButton(R.string.ok, (dialog, which) -> {
                }).show();
    }

    @Override
    public void onUserLogout() {
        Intent intent = new Intent(this, WelcomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.logout_btn){
            dashboardPresenter.logoutUser();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}