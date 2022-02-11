package me.yonghong.hlib;

import android.content.Context;
import android.util.Log;

import me.yonghong.ext.ExtTool;

public class DebugWorld {
  private static final String TAG = "DebugWorld";

  static void debug() {
    Log.i(TAG, "debug");
  }

  public static void log(Context context) {
    Log.i(TAG, String.valueOf(ExtTool.getScreenWidth(context)));
    Log.i(TAG, String.valueOf(ExtTool.getScreenHeight(context)));
  }
}
