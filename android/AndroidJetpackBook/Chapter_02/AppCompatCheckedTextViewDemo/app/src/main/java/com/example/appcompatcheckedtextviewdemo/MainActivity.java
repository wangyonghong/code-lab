package com.example.appcompatcheckedtextviewdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckedTextView;

public class MainActivity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();

    private AppCompatCheckedTextView demoCtv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        demoCtv = findViewById(R.id.activity_main_ctv);
        demoCtv.setCheckMarkDrawable(android.R.drawable.checkbox_off_background);
        demoCtv.setTextAppearance(MainActivity.this, R.style.TextAppearance_AppCompat_Caption);
        demoCtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                demoCtv.setChecked(!demoCtv.isChecked());
                Log.d(TAG, "选中状态：" + demoCtv.isChecked());
                if (demoCtv.isChecked()) {
                    demoCtv.setCheckMarkDrawable(android.R.drawable.checkbox_on_background);
                } else {
                    demoCtv.setCheckMarkDrawable(android.R.drawable.checkbox_off_background);
                }
            }
        });
    }
}