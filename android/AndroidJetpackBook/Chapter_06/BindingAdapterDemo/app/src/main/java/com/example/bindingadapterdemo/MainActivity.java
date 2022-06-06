package com.example.bindingadapterdemo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableLong;

import com.example.bindingadapterdemo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private boolean allowRun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        allowRun = true;
        ExampleDataObject exampleDataObject = new ExampleDataObject();
        exampleDataObject.setDateTime(new ObservableLong());
        binding.setCurrentDateTime(exampleDataObject);
        exampleDataObject.getDateTime().set(System.currentTimeMillis());
        new Thread(() -> {
            while (allowRun)
                try {
                    exampleDataObject.getDateTime().set(System.currentTimeMillis());
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        allowRun = false;
    }
}