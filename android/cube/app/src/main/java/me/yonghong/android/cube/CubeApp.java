package me.yonghong.android.cube;

import android.app.Application;

public class CubeApp extends Application {

  public static Application appContext;

  @Override
  public void onCreate() {
    super.onCreate();
    appContext = this;
  }
}
