package com.example.viewmodeldemo;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView centerTv = findViewById(R.id.activity_main_tv);
        StartTimeViewModel startTimeViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(StartTimeViewModel.class);
        startTimeViewModel.fetchCurrentTimeInMillis(currentTimeMillis -> centerTv.setText(String.valueOf(currentTimeMillis)));
    }

}