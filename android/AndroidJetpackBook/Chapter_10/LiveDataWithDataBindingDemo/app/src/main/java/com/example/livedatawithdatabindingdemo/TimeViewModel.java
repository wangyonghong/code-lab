package com.example.livedatawithdatabindingdemo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimeViewModel extends ViewModel {

    private final MediatorLiveData<Long> currentDateTimeRaw;
    private final LiveData<String> currentDateTime;

    private Timer timer;

    public TimeViewModel() {
        this.currentDateTimeRaw = new MediatorLiveData<>();
        this.currentDateTime = Transformations.map(currentDateTimeRaw, timeMillis -> new Date(timeMillis).toString());
    }

    public LiveData<String> getCurrentDateTime() {
        return currentDateTime;
    }

    public void startRefresh() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                currentDateTimeRaw.postValue(System.currentTimeMillis());
            }
        }, 0, 1000);
    }

    public void stopRefresh() {
        if (timer != null) {
            timer.cancel();
        }
    }
}
