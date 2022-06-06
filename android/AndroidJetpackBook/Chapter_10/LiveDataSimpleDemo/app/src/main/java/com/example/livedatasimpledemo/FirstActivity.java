package com.example.livedatasimpledemo;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class FirstActivity extends AppCompatActivity {

    private Timer timer;
    private TimerTask timerTask;

    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        TextView contentTv = findViewById(R.id.activity_first_text_content_tv);
        CurrentTimeViewModel currentTimeViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(CurrentTimeViewModel.class);
        Observer<String> currentTime = str -> {
            Log.d(TAG, "UI更新");
            if (str != null) {
                contentTv.setText(str);
            } else {
                contentTv.setText(R.string.app_name);
            }
        };
        currentTimeViewModel.getCurrentTime().observe(this, currentTime);
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                currentTimeViewModel.setCurrentTime(new Date().toString());
                Log.d(TAG, "currentTimeViewModel值改变");
            }
        };
        timer.schedule(timerTask, 0, 1000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
        timer.cancel();
    }
}