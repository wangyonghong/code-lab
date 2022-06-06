package com.example.livedatasimpledemo;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CurrentTimeViewModel extends ViewModel {

    private MutableLiveData<String> currentTime;

    public MutableLiveData<String> getCurrentTime() {
        if (currentTime == null) {
            currentTime = new MutableLiveData<>();
        }
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        if (currentTime == null) {
            this.currentTime = new MutableLiveData<>();
        }
        this.currentTime.postValue(currentTime);
    }
}
