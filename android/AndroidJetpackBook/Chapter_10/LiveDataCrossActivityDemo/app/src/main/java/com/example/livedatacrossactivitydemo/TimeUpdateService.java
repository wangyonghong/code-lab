package com.example.livedatacrossactivitydemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;

import java.util.Timer;
import java.util.TimerTask;

public class TimeUpdateService extends Service implements LifecycleOwner {

    private final String TAG = getClass().getSimpleName();

    private Timer timer;
    private TimerTask timerTask;

    private LifecycleRegistry mLifecycleRegistry = new LifecycleRegistry(this);

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP);
        return super.onUnbind(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE);
        ShareExampleLiveData.getInstance().observe(this, timeMillis -> Log.d(TAG, timeMillis + ""));
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                ShareExampleLiveData.getInstance().postValue(System.currentTimeMillis());
            }
        };
        timer.schedule(timerTask, 0, 1000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY);
        timer.cancel();
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return mLifecycleRegistry;
    }
}
