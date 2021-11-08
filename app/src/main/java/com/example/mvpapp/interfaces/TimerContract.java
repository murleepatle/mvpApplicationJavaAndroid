package com.example.mvpapp.interfaces;

import com.example.mvpapp.data.model.WeatherDataResponse;

public interface TimerContract {
    /**
     * Represents the Presenter in MVP for Timer fragment.
     */
    interface ITimerPresenter {
        void startTimer();
        void endTimer();
    }

    /**
     * Represents the View in MVP.
     */
    interface ITimerView {
        void timerUpdate(int process,String timerMsg);
        void timerFinish(String message);
    }
}
