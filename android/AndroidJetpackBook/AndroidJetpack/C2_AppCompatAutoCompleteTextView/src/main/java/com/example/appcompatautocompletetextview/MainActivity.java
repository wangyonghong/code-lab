package com.example.appcompatautocompletetextview;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.core.view.ViewCompat;

public class MainActivity extends AppCompatActivity {

    private AppCompatAutoCompleteTextView appCompatAutoCompleteTextView;

    private final String[] NAMES = new String[]{"Alice", "Bob", "Cindy", "David", "Edward"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        initData();
    }

    private void initData() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, NAMES);
        ViewCompat.setBackgroundTintList(appCompatAutoCompleteTextView, AppCompatResources.getColorStateList(MainActivity.this, R.color.act_underline));
        ViewCompat.setBackgroundTintMode(appCompatAutoCompleteTextView, PorterDuff.Mode.SRC_IN);
        appCompatAutoCompleteTextView.setAdapter(adapter);
    }

    private void findView() {
        appCompatAutoCompleteTextView = findViewById(R.id.activity_main_demo_act);
    }
}