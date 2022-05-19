package me.yonghong.futuretask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MainActivity extends AppCompatActivity {

  private static final String TAG = "MainActivity";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    AppCompatButton submitTaskBtn = findViewById(R.id.submit_task_btn);
    submitTaskBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Future<?> future = TaskManager.submit(new Runnable() {
          @Override
          public void run() {
            try {
              Thread.sleep(10000);
            } catch (InterruptedException e) {
              Log.e(TAG, "sleep InterruptedException");
            }
          }
        });
        new Thread() {
          @Override
          public void run() {
            try {
              Log.e(TAG, "start get");
              Object o = future.get(5, TimeUnit.SECONDS);
            } catch (ExecutionException e) {
              Log.e(TAG, "ExecutionException");
            } catch (InterruptedException e) {
              Log.e(TAG, "InterruptedException");
            } catch (TimeoutException e) {
              Log.e(TAG, "TimeoutException");
            }
          }
        }.start();
      }
    });
    AppCompatButton finishActivityBtn = findViewById(R.id.finish_activity_btn);
    finishActivityBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        finish();
      }
    });
  }
}