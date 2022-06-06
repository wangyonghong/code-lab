package com.example.bindingadapterdemo;

import android.util.Log;

import androidx.databinding.ObservableLong;

public class ExampleDataObject {

    private ObservableLong dateTime;

    public ObservableLong getDateTime() {
        return dateTime;
    }

    public void setDateTime(ObservableLong dateTime) {
        this.dateTime = dateTime;
    }

    public void logCurrentDateTime(String dateTime) {
        Log.d(getClass().getSimpleName(), dateTime);
    }
}
