package com.example.appcompatresourcesdemo;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;

public class MainActivity extends AppCompatActivity {

    private TextView helloTv;
    private ImageView iconIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helloTv = findViewById(R.id.activity_main_hello_tv);
        iconIv = findViewById(R.id.activity_main_icon_iv);
        colorStateListDemo();
        drawableDemo();
        setListener();
        setImage(drawableDemo());
    }

    private void setImage(Drawable drawable) {
        iconIv.setImageDrawable(drawable);
    }

    private void setListener() {
        helloTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Hello!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // AppCompatResources.getDrawable();
    private Drawable drawableDemo() {
        Drawable iconDrawable = AppCompatResources.getDrawable(this, R.mipmap.ic_launcher);
        return iconDrawable;
    }

    // AppCompatResources.getColorStateListDemo();
    private void colorStateListDemo() {
        ColorStateList helloTvCsl = AppCompatResources.getColorStateList(this, R.drawable.selector_button_text);
        helloTv.setTextColor(helloTvCsl);
    }
}