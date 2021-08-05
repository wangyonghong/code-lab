package com.derry.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

public class MainActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        // Java 代码 来注册 刚刚的接收者即可
        // 第二步，在onCreate注册广播（订阅）
        // 动态使用Java代码注册一个广播接收者
        UpdateIpSelectCity updateIpSelectCity = new UpdateIpSelectCity();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ActionUtils.ACTION_EQUES_UPDATE_IP);
        registerReceiver(updateIpSelectCity, filter);
    }

    // 第三步
    // 发送给 动态注册的接收者
    public void sendAction1(View view) {
        Intent intent = new Intent();
        // ActionUtils.ACTION_EQUES_UPDATE_IP 与注册时保持一致
        intent.setAction(ActionUtils.ACTION_EQUES_UPDATE_IP);
        sendBroadcast(intent);
    }






    // TODO ===================== 静态发送广播区域

    // 第二步：发送给接收者
    // 静态发送广播 给 接收者
    public void sendAction2(View view) {
        Intent intent = new Intent();
        // ActionUtils.ACTION_FLAG 与注册时保持一致
        intent.setAction(ActionUtils.ACTION_FLAG);
        sendBroadcast(intent);
    }


}