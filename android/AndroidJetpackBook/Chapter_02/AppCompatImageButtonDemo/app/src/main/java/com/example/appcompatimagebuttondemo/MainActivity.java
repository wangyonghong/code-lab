package com.example.appcompatimagebuttondemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import androidx.core.widget.ImageViewCompat;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private AppCompatImageButton demoIb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        demoIb = findViewById(R.id.activity_main_ib);
        demoIb.setImageResource(R.drawable.image_adb);
        ViewCompat.setBackgroundTintList(demoIb, AppCompatResources.getColorStateList(MainActivity.this, R.color.bg_btn));
        ViewCompat.setBackgroundTintMode(demoIb, PorterDuff.Mode.SRC_IN);
        ImageViewCompat.setImageTintList(demoIb, AppCompatResources.getColorStateList(MainActivity.this, R.color.icon_btn));
        ImageViewCompat.setImageTintMode(demoIb, PorterDuff.Mode.SRC_IN);
    }
}