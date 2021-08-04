package com.derry.project;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

// 接收者
public class CustomReceiver extends BroadcastReceiver {

    private static final String TAG = CustomReceiver.class.getSimpleName();
    
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(TAG, "CustomReceiver onReceive 广播接受者");
    }
}
