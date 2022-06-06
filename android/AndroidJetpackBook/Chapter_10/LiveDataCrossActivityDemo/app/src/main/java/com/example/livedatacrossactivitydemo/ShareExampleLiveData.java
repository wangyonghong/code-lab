package com.example.livedatacrossactivitydemo;

import android.util.Log;

import androidx.annotation.MainThread;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

public class ShareExampleLiveData extends LiveData<Long> {

    private final String TAG = getClass().getSimpleName();
    private long currentTimeMillis;

    private static ShareExampleLiveData instance;

    @MainThread
    public static ShareExampleLiveData getInstance() {
        if (instance == null) {
            instance = new ShareExampleLiveData();
        }
        return instance;
    }

    @Override
    protected void postValue(Long value) {
        super.postValue(value);
        currentTimeMillis = value;
    }

    @Override
    protected void setValue(Long value) {
        super.setValue(value);
        currentTimeMillis = value;
    }

    @Nullable
    @Override
    public Long getValue() {
        super.getValue();
        return currentTimeMillis;
    }

    @Override
    protected void onActive() {
        super.onActive();
        Log.d(TAG, "onActive");
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        Log.d(TAG, "onInactive");
    }
}
