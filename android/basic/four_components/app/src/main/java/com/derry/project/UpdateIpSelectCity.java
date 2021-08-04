package com.derry.project;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

// 第一步，定义广播接收者（Receiver）
public class UpdateIpSelectCity extends BroadcastReceiver {

    private static final String TAG = UpdateIpSelectCity.class.getSimpleName();

    @Override
    public void onReceive(Context arg0, Intent arg1) {
        Log.e(TAG, "UpdateIpSelectCity onReceive 广播接受者");
    }
}
