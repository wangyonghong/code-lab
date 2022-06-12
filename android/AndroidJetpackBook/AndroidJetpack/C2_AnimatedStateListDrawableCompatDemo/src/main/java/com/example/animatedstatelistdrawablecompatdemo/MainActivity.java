package com.example.animatedstatelistdrawablecompatdemo;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.graphics.drawable.AnimatedStateListDrawableCompat;

public class MainActivity extends AppCompatActivity {

    private ImageView animatedIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        setListener();
        initData();
    }

    private void initData() {
        AnimatedStateListDrawableCompat animatedStateListDrawableCompat = AnimatedStateListDrawableCompat.create(MainActivity.this, R.drawable.anim_image, null);
        animatedIv.setImageDrawable(animatedStateListDrawableCompat);
        animatedIv.setClickable(true);
    }

    private void setListener() {
    }

    private void findView() {
        animatedIv = findViewById(R.id.activity_main_animation_iv);
    }
}