package com.example.mvpapp.ui.dashboard;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mvpapp.R;
import com.example.mvpapp.data.model.PostOffice;
import com.example.mvpapp.databinding.ActivityDashboardBinding;
import com.example.mvpapp.interfaces.DashboardContract;
import com.example.mvpapp.presenter.DashboardPresenter;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * MainActivity Class is a dashboard class that will show user detail main page
 */
public class DashboardActivity extends AppCompatActivity implements DashboardContract.IDashboardView {
    SearchView searchView;

    private DashboardPresenter dashboardPresenter;
    private PostOfficeAdapter postOfficeAdapter;
    private ActivityDashboardBinding activityDashboardBinding;
    private ProgressDialog progressBar ;

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
    }

    @Override
    public void onPostOfficeFetchSuccessfully(List<PostOffice> postOffices) {
        postOfficeAdapter.updateList(postOffices);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the options menu from XML
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
         searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(true); // Do not iconify the widget; expand it by default
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                dashboardPresenter.fetchPostOfficeDetailByPinCode(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                postOfficeAdapter.updateList(new ArrayList<>());
                activityDashboardBinding.statusTv.setText((getString(R.string.pin_code)+ newText));
                return false;
            }
        });
        return true;
    }

}