package me.yonghong.ext;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

public class ExtTool {

  private static final String TAG = "ExtTool";

  /**
   * 屏幕物理宽度，单位 px
   */
  public static int getScreenWidth(Context context) {
    return getRealScreenWidth(context);
  }

  /**
   * 屏幕物理高度，单位 px
   */
  public static int getScreenHeight(Context context) {
    return getRealScreenHeight(context);
  }

  /**
   * 屏幕物理宽度，单位 px
   */
  private static int getRealScreenWidth(Context context) {
    DisplayMetrics dm = getRealScreenDisplayMetrics(context);
    return dm.widthPixels;
  }

  /**
   * 屏幕物理高度，单位 px
   */
  private static int getRealScreenHeight(Context context) {
    DisplayMetrics dm = getRealScreenDisplayMetrics(context);
    return dm.heightPixels;
  }

  private static DisplayMetrics getRealScreenDisplayMetrics(Context context) {
    DisplayMetrics dm = new DisplayMetrics();
    Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
    display.getRealMetrics(dm);
    Log.i(TAG, "getRealScreenDisplayMetrics: " + dm.heightPixels + ", " + dm.widthPixels);
    return dm;
  }
}
