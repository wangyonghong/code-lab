package com.example.tooltipcompatdemo;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.TooltipCompat;

public class MainActivity extends AppCompatActivity {

    private TextView demoTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        demoTv = findViewById(R.id.activity_main_tv);
        TooltipCompat.setTooltipText(demoTv, getResources().getText(R.string.tool_tip_str));
    }
}