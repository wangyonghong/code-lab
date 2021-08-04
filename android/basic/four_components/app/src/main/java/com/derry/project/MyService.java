package com.derry.project;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    private final static String TAG = MyService.class.getSimpleName();

    // =========================== startService 对应 stopService 区域

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate()...");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.d(TAG, "onStart()...");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand()...");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()...");
    }





















    // =========================== bindService 对应 unBindService 区域

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind()...");
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind()...");
        return super.onUnbind(intent);
    }


}
