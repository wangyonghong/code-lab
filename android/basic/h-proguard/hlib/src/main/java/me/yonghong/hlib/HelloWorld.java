package me.yonghong.hlib;

import android.content.Context;
import android.util.Log;

import me.yonghong.ext.ExtTool;

public class HelloWorld {
  private static final String TAG = "HelloWorld";

  public static void apple() {
    DebugWorld.debug();
    wash();
    Log.i(TAG, "apple");
  }

  public static void banana() {
    DebugWorld.debug();
    wash();
    Log.i(TAG, "banana");
  }

  private static void wash() {
    Log.i(TAG, "wash");
  }

  public static void log(Context context) {
    Log.i(TAG, String.valueOf(ExtTool.getScreenWidth(context)));
    Log.i(TAG, String.valueOf(ExtTool.getScreenHeight(context)));
  }
}
