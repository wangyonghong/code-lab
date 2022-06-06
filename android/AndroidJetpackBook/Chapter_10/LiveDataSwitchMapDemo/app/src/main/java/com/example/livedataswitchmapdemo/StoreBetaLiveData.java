package com.example.livedataswitchmapdemo;

import android.util.Log;

import androidx.annotation.MainThread;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

public class StoreBetaLiveData extends LiveData<Long> {
    private final String TAG = getClass().getSimpleName();
    private long currentTimeMillis;

    private static StoreBetaLiveData instance;

    @MainThread
    public static StoreBetaLiveData getInstance() {
        if (instance == null) {
            instance = new StoreBetaLiveData();
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

    /**
     * 获取本店销售速度
     *
     * @return 销售速度值
     */
    public int getSellingSpeed() {
        return 2;
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
