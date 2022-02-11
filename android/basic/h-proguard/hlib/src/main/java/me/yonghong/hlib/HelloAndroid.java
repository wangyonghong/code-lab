package me.yonghong.hlib;

import android.content.Context;
import android.util.Log;

import me.yonghong.ext.ExtTool;

public class HelloAndroid {

  private static final String TAG = "HelloAndroid";

  public static void log(Context context) {
    Log.i(TAG, String.valueOf(ExtTool.getScreenWidth(context)));
    Log.i(TAG, String.valueOf(ExtTool.getScreenHeight(context)));
  }
}
