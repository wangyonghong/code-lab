package com.derry.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

// 默认的Activity
public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, TAG + "--->onCreate()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, TAG + "--->onRestart()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, TAG + "--->onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, TAG + "--->onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, TAG + "--->onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, TAG + "--->onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, TAG + "--->onDestroy()");
    }

}