package com.example.livedatacrossactivitydemo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(MainActivity.this, TimeUpdateService.class));
        ShareExampleLiveData.getInstance().observe(this, timeMillis -> ((TextView) findViewById(R.id.activity_main_time_tv)).setText(new Date(timeMillis).toString()));
    }
}