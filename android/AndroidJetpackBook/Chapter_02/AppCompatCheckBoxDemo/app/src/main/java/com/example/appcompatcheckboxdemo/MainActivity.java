package com.example.appcompatcheckboxdemo;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.widget.CompoundButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.core.view.ViewCompat;
import androidx.core.widget.CompoundButtonCompat;

public class MainActivity extends AppCompatActivity {

    private AppCompatCheckBox demoCb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        demoCb = findViewById(R.id.activity_main_cb);
        CompoundButtonCompat.setButtonTintList(demoCb, AppCompatResources.getColorStateList(MainActivity.this, R.color.act_underline));
        CompoundButtonCompat.setButtonTintMode(demoCb, PorterDuff.Mode.SRC_IN);
    }
}