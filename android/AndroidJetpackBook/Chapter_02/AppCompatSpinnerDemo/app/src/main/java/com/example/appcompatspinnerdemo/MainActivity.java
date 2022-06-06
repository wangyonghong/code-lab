package com.example.appcompatspinnerdemo;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.core.view.ViewCompat;

public class MainActivity extends AppCompatActivity {

    private AppCompatSpinner demoSp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        demoSp = findViewById(R.id.activity_main_sp);
        demoSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, getResources().getStringArray(R.array.person_name)[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ViewCompat.setBackgroundTintList(demoSp, AppCompatResources.getColorStateList(MainActivity.this, R.color.sp_bg));
        ViewCompat.setBackgroundTintMode(demoSp, PorterDuff.Mode.SRC_IN);
//        demoSp.setPopupBackgroundDrawable(AppCompatResources.getDrawable(MainActivity.this, R.color.purple_200));
        demoSp.setPopupBackgroundResource(R.color.purple_200);
    }

}