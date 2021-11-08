package com.example.mvpapp.ui.dashboard.timer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mvpapp.databinding.FragmentTimerBinding;
import com.example.mvpapp.interfaces.TimerContract;
import com.example.mvpapp.presenter.TimerPresenter;


public class TimerFragment extends Fragment implements TimerContract.ITimerView {

    private FragmentTimerBinding binding;
    TimerPresenter presenter;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTimerBinding.inflate(inflater, container, false);
        presenter =new TimerPresenter(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.startTimer();
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.endTimer();
        binding = null;
    }

    @Override
    public void timerUpdate(int process,String timerMsg) {
        binding.progressBarCircle.setProgress(process);
        binding.textMsg.setText(timerMsg);
    }

    @Override
    public void timerFinish(String message) {
        binding.progressBarCircle.setProgress(0);
        binding.textMsg.setText(message);
    }


}