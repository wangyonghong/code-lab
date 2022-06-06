package com.example.livedataswitchmapdemo;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private Timer storeAlphaTimer, storeBetaTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView amountTv = findViewById(R.id.activity_main_current_amount_tv);

        LiveData<Integer> currentAlphaAmount =
                Transformations.switchMap(StoreAlphaLiveData.getInstance(),
                        timeMillis -> {
                            WarehouseLiveData.getInstance(1000).sell(StoreAlphaLiveData.getInstance().getSellingSpeed());
                            return WarehouseLiveData.getInstance(1000);
                        });
        currentAlphaAmount.observe(this, amount -> amountTv.setText(String.valueOf(amount)));

        LiveData<Integer> currentBetaAmount =
                Transformations.switchMap(StoreBetaLiveData.getInstance(),
                        timeMillis -> {
                            WarehouseLiveData.getInstance(1000).sell(StoreBetaLiveData.getInstance().getSellingSpeed());
                            return WarehouseLiveData.getInstance(1000);
                        });
        currentBetaAmount.observe(this, amount -> amountTv.setText(String.valueOf(amount)));


        storeAlphaTimer = new Timer();
        TimerTask storeAlphaTimerTask = new TimerTask() {
            @Override
            public void run() {
                StoreAlphaLiveData.getInstance().postValue(System.currentTimeMillis());
            }
        };
        storeAlphaTimer.schedule(storeAlphaTimerTask, 0, 2000);
        storeBetaTimer = new Timer();
        TimerTask storeBetaTimerTask = new TimerTask() {
            @Override
            public void run() {
                StoreBetaLiveData.getInstance().postValue(System.currentTimeMillis());
            }
        };
        storeBetaTimer.schedule(storeBetaTimerTask, 0, 1000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        storeAlphaTimer.cancel();
        storeBetaTimer.cancel();
    }
}