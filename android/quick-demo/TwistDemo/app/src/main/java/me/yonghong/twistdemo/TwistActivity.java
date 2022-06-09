package me.yonghong.twistdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class TwistActivity extends AppCompatActivity implements SensorEventListener {

  private SensorManager mSensorManager = null;
  private LinearLayout linearLayout;
  private ImageView mImg1;
  private ImageView mImg2;
  private long lastModifiedTime;
  private int screenWidth;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    DisplayMetrics dm = new DisplayMetrics();
    Display display =
        ((WindowManager) getApplication().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
    display.getRealMetrics(dm);
    screenWidth = dm.widthPixels - 200;

    setContentView(R.layout.activity_twist);
    LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(400, 400);
    linearLayout = findViewById(R.id.container);
    ImageView imageView = new ImageView(getBaseContext());
    imageView.setImageResource(R.drawable.studio);
    imageView.setScaleType(ImageView.ScaleType.FIT_CENTER); // 默认fix_xy与ios对齐
    imageView.setLayoutParams(params1);
    linearLayout.addView(imageView);
    mImg1 = new ImageView(getBaseContext());
    mImg1.setImageResource(R.drawable.studio);
    mImg1.setScaleType(ImageView.ScaleType.FIT_CENTER); // 默认fix_xy与ios对齐
    mImg1.setLayoutParams(params1);
    linearLayout.addView(mImg1);

    LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(200, 200);
    mImg2 = new ImageView(getBaseContext());
    mImg2.setImageResource(R.drawable.studio);
    mImg2.setScaleType(ImageView.ScaleType.FIT_CENTER); // 默认fix_xy与ios对齐
    mImg2.setLayoutParams(params2);
    linearLayout.addView(mImg2);

    initSensor();
  }

  private void initSensor() {
    mSensorManager = (SensorManager) getBaseContext().getSystemService(Context.SENSOR_SERVICE);
  }

  @Override
  protected void onResume() {
//    mSensorManager.registerListener(this,
//        mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
//        SensorManager.SENSOR_DELAY_UI);
    mSensorManager.registerListener(this,
        mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
        SensorManager.SENSOR_DELAY_GAME);
    super.onResume();
  }

  @Override
  protected void onPause() {
    mSensorManager.unregisterListener(this);
    super.onPause();
  }


  @Override
  public void onSensorChanged(SensorEvent event) {
    if (Sensor.TYPE_ACCELEROMETER != event.sensor.getType()) {
      return;
    }
    float[] values = event.values;
    float ax = values[0];
    float ay = values[1];
    double g = Math.sqrt(ax * ax + ay * ay);
    double cos = ay / g;
    if (cos > 1) {
      cos = 1;
    } else if (cos < -1) {
      cos = -1;
    }
    double rad = Math.acos(cos);
    if (ax < 0) {
      rad = 2 * Math.PI - rad;
    }
    int uiRot = getWindowManager().getDefaultDisplay().getRotation();
    double uiRad = Math.PI / 2 * uiRot;
    rad -= uiRad;
    float degree = (float) (180 * rad / Math.PI);


    Log.d("red", String.valueOf(degree));
    mImg1.setRotation(degree);

    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mImg2.getLayoutParams();
    if (degree > 180) {
      degree = -(360 - degree);
    }
    float speed = -degree;
    if (speed > 90) {
      speed = 180 - speed;
    }
    if (speed < -90) {
      speed = -180 - degree;
    }
//    speed = (float) (0.8 * speed);
    if (lastModifiedTime == 0) {
      lastModifiedTime = System.currentTimeMillis();
      speed = 0;
    } else {
      speed = (float) (speed * (System.currentTimeMillis() - lastModifiedTime) * 0.03);
      lastModifiedTime = System.currentTimeMillis();
    }
    int leftMargin = (int) (layoutParams.leftMargin + speed);
    if (leftMargin < 0) {
      leftMargin = 0;
    } else if (leftMargin > screenWidth) {
      leftMargin = screenWidth;
    }
    layoutParams.leftMargin = leftMargin;
    mImg2.setLayoutParams(layoutParams);
    degree = (float) ((layoutParams.leftMargin * 360 / (2 * 100 * Math.PI) % 360));
    mImg2.setRotation(degree);
  }


  @Override
  public void onAccuracyChanged(Sensor sensor, int accuracy) {

  }
}