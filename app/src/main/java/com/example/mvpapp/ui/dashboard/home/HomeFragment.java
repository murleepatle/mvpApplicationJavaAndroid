package com.example.mvpapp.ui.dashboard.home;

import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mvpapp.R;
import com.example.mvpapp.data.model.WeatherDataResponse;
import com.example.mvpapp.databinding.FragmentHomeBinding;
import com.example.mvpapp.interfaces.HomeContract;
import com.example.mvpapp.presenter.HomePresenter;
import com.example.mvpapp.ui.dashboard.WeatherAdapter;
import com.example.mvpapp.utility.InternetUtils;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.Collections;

public class HomeFragment extends Fragment implements HomeContract.IHomeView {

    private HomePresenter homePresenter;
    private WeatherAdapter weatherAdapter;
    private ProgressDialog progressBar ;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar = new ProgressDialog(requireContext());
        progressBar.setCancelable(false);
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);


        homePresenter =new HomePresenter(this, new InternetUtils(requireContext()));
        weatherAdapter = new WeatherAdapter(new ArrayList<>());
        binding.recyclerViewPostOffice.setHasFixedSize(true);
        binding.recyclerViewPostOffice.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerViewPostOffice.setAdapter(weatherAdapter);

        binding.locationSearchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                weatherAdapter.updateList(new ArrayList<>());
                binding.emptyImg.setVisibility(View.VISIBLE);
               // binding.locationSearchView.setError(null);
            }
        });
        binding.locationSearchView.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                homePresenter.fetchWeatherDetailByLocation(binding.locationSearchView.getText().toString());
                return true;
            }
            return false;
        });
        binding.searchImg.setOnClickListener(v -> homePresenter.fetchWeatherDetailByLocation(binding.locationSearchView.getText().toString()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    @Override
    public void onWeatherDataFetchSuccessfully(WeatherDataResponse weatherDataResponse) {
        binding.emptyImg.setVisibility(View.GONE);
        weatherAdapter.updateList(Collections.singletonList(weatherDataResponse));
    }

    @Override
    public void onErrorPostOfficeFetch(String errorMsg) {
        new MaterialAlertDialogBuilder(requireContext())
                .setMessage(errorMsg)
                .setNeutralButton(R.string.ok, (dialog, which) -> {
                }).show();
    }

    @Override
    public void onErrorInputPin(int errorMsgResourceId) {
        binding.locationSearchView.setError(getString(errorMsgResourceId));
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
        new MaterialAlertDialogBuilder(requireContext())
                .setTitle(R.string.internet_title)
                .setMessage(R.string.internet_msg)
                .setNeutralButton(R.string.ok, (dialog, which) -> {
                }).show();
    }

}