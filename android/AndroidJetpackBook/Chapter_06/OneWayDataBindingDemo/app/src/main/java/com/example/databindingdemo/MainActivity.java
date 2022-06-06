package com.example.databindingdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableLong;

import android.os.Bundle;
import android.widget.Toast;

import com.example.databindingdemo.databinding.ActivityMainBinding;

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