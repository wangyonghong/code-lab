package com.example.livedatawithdatabindingdemo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.livedatawithdatabindingdemo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private TimeViewModel timeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLifecycleOwner(this);
        timeViewModel = new TimeViewModel();
        timeViewModel.startRefresh();
        binding.setTimeViewModel(timeViewModel);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timeViewModel.stopRefresh();
    }
}