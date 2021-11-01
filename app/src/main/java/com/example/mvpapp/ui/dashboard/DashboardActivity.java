package com.example.mvpapp.ui.dashboard;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mvpapp.R;
import com.example.mvpapp.data.model.WeatherDataResponse;
import com.example.mvpapp.databinding.ActivityDashboardBinding;
import com.example.mvpapp.interfaces.DashboardContract;
import com.example.mvpapp.presenter.DashboardPresenter;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.Collections;

/**
 * MainActivity Class is a dashboard class that will show user detail main page
 */
public class DashboardActivity extends AppCompatActivity implements DashboardContract.IDashboardView {

    private DashboardPresenter dashboardPresenter;
    private PostOfficeAdapter postOfficeAdapter;
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

        dashboardPresenter=new DashboardPresenter(this);
        postOfficeAdapter = new PostOfficeAdapter(new ArrayList<>());
        activityDashboardBinding.recyclerViewPostOffice.setHasFixedSize(true);
        activityDashboardBinding.recyclerViewPostOffice.setLayoutManager(new LinearLayoutManager(this));
        activityDashboardBinding.recyclerViewPostOffice.setAdapter(postOfficeAdapter);

        activityDashboardBinding.simpleSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                dashboardPresenter.fetchPostOfficeDetailByPinCode(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                postOfficeAdapter.updateList(new ArrayList<>());
                activityDashboardBinding.emptyImg.setVisibility(View.VISIBLE);

                return false;
            }
        });
        activityDashboardBinding.simpleSearchView.onActionViewExpanded();
    }

    @Override
    public void onWeatherDataFetchSuccessfully(WeatherDataResponse weatherDataResponse) {
        activityDashboardBinding.emptyImg.setVisibility(View.GONE);
        postOfficeAdapter.updateList(Collections.singletonList(weatherDataResponse));
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


}