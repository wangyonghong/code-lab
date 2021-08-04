package com.enjoy.mynotification;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

public class NotificationActivity  extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.e("leo", "onCreate: 进入NotificationActivity" );
    }
}
