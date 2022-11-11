package me.yonghong.android.cube;

import android.app.Application;

public class CubeApp extends Application {

  private static Application sAppContext;

  @Override
  public void onCreate() {
    super.onCreate();
    sAppContext = this;
  }

  public static Application getAppContext() {
    return sAppContext;
  }
}
