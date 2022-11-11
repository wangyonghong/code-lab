package me.yonghong.android.cube.util;

import android.content.res.Resources;
import android.util.TypedValue;

import me.yonghong.android.cube.CubeApp;

public class PxUtils {

  public static float dpToPx(float dp) {
    Resources r = CubeApp.getAppContext().getApplicationContext().getResources();
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
  }
}
