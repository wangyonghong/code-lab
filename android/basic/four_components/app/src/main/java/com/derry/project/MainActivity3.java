package com.derry.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }

    /**
     * 跳转到第二个Activity 的事件函数
     * @param view
     */
    public void startActivity2(View view) {
        startActivity(new Intent(this, MainActivity2.class));
    }

    // =========================== startService 对应 stopService 区域
    // 启动服务
    public void startServce(View view) {
        startService(new Intent(this, MyService.class));
    }

    // 停止服务
    public void stopServce(View view) {
        stopService(new Intent(this, MyService.class));
    }




    // =========================== bindService 对应 unBindService 区域

    public void bindServce(View view) {
        bindService(new Intent(this, MyService.class), connecton, Context.BIND_AUTO_CREATE);
    }

    public void unBindServce(View view) {
        unbindService(connecton);
    }

    // MainActivity 与 MyService 的桥梁
    private ServiceConnection connecton = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    // 一般的写法，当此Activity被销毁的时候，自动解绑服务
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connecton);
    }
}