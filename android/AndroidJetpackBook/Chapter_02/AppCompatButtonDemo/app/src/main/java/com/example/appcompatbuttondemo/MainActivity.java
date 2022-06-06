package com.example.appcompatbuttondemo;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.view.ViewCompat;

public class MainActivity extends AppCompatActivity {

    private AppCompatButton appCompatButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        initData();
    }

    private void initData() {
        ViewCompat.setBackgroundTintList(appCompatButton, AppCompatResources.getColorStateList(MainActivity.this, R.color.act_underline));
        ViewCompat.setBackgroundTintMode(appCompatButton, PorterDuff.Mode.SRC_IN);
        appCompatButton.setTextAppearance(MainActivity.this, R.style.TextAppearance_AppCompat_Large);
        appCompatButton.setAllCaps(false);
    }

    private void findView() {
        appCompatButton = findViewById(R.id.activity_main_demo_acb);
    }
}