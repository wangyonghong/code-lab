package com.example.livedataswitchmapdemo;

import android.util.Log;

import androidx.annotation.MainThread;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

public class WarehouseLiveData extends LiveData<Integer> {
    private final String TAG = getClass().getSimpleName();
    private int amount;

    private static WarehouseLiveData instance;

    @MainThread
    public static WarehouseLiveData getInstance(int initialAmount) {
        if (instance == null) {
            instance = new WarehouseLiveData(initialAmount);
        }
        return instance;
    }

    public WarehouseLiveData(int initialAmount) {
        this.amount = initialAmount;
    }

    @Override
    protected void postValue(Integer value) {
        super.postValue(value);
        amount = value;
    }

    @Override
    protected void setValue(Integer value) {
        super.setValue(value);
        amount = value;
    }

    @Nullable
    @Override
    public Integer getValue() {
        super.getValue();
        return amount;
    }

    public void sell(int goodCount) {
        amount -= goodCount;
        if (amount < 0) {
            amount = 0;
        }
        postValue(amount);
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
