package com.example.servicetestdemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class ExampleService extends Service {
    public static final String NUMBER_1 = "num1";
    public static final String NUMBER_2 = "num2";
    private final IBinder mBinder = new LocalBinder();

    private int num_1;
    private int num_2;

    @Override
    public IBinder onBind(Intent intent) {
        if (intent.hasExtra(NUMBER_1)) {
            num_1 = intent.getIntExtra(NUMBER_1, 0);
        }
        if (intent.hasExtra(NUMBER_2)) {
            num_2 = intent.getIntExtra(NUMBER_2, 0);
        }
        return mBinder;
    }

    public int getSumResult() {
        return num_1 + num_2;
    }

    public class LocalBinder extends Binder {

        public ExampleService getService() {
            return ExampleService.this;
        }
    }
}
