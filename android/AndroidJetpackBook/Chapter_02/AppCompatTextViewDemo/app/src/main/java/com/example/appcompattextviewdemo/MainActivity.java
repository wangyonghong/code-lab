package com.example.appcompattextviewdemo;

import android.os.Bundle;
import android.util.TypedValue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.widget.TextViewCompat;

public class MainActivity extends AppCompatActivity {

    private AppCompatTextView demoTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        demoTv = findViewById(R.id.activity_main_tv);
        TextViewCompat.setAutoSizeTextTypeWithDefaults(demoTv, TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);
//        TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(demoTv, 4, 15, 2, TypedValue.COMPLEX_UNIT_SP);
//        TextViewCompat.setAutoSizeTextTypeUniformWithPresetSizes(demoTv, new int[]{9, 11, 13, 15}, TypedValue.COMPLEX_UNIT_SP);
    }
}