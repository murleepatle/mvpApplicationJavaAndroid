package com.example.mvpapp.presenter;

import android.os.CountDownTimer;

import com.example.mvpapp.App;
import com.example.mvpapp.R;
import com.example.mvpapp.interfaces.TimerContract;
import com.example.mvpapp.utility.CommonMethod;

public class TimerPresenter implements TimerContract.ITimerPresenter {
    CountDownTimer countDownTimer;
    TimerContract.ITimerView iTimerView;

    public TimerPresenter( TimerContract.ITimerView iTimerView) {
        this.iTimerView=iTimerView;
    }

    @Override
    public void startTimer() {
        countDownTimer =new CountDownTimer(30000, 1000) {
            public void onTick(long millisUntilFinished) {
                if (iTimerView!=null) {
                    iTimerView.timerUpdate((int) (millisUntilFinished / 1000), CommonMethod.msTimeFormatter(millisUntilFinished));
                }
            }

            public void onFinish() {
                if (iTimerView!=null) {
                    iTimerView.timerFinish(App.getContext().getString(R.string.timer_complete));
                }

            }
        }.start();
    }

    @Override
    public void endTimer() {
        iTimerView=null;
        countDownTimer.onFinish();
    }
}
