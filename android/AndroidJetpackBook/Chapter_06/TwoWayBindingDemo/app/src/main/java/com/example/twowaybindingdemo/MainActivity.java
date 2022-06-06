package com.example.twowaybindingdemo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.twowaybindingdemo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private InputViewModel inputViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        inputViewModel = new InputViewModel();
        activityMainBinding.setInputText(inputViewModel);
    }
}