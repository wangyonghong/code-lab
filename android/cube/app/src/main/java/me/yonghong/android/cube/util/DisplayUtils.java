package me.yonghong.android.cube.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import me.yonghong.android.cube.CubeApp;

public class DisplayUtils {

  public static int getRealScreenWidth() {
    DisplayMetrics dm = getRealScreenDisplayMetrics();
    return dm.widthPixels;
  }

  public static int getRealScreenHeight() {
    DisplayMetrics dm = getRealScreenDisplayMetrics();
    return dm.heightPixels;
  }

  private static DisplayMetrics getRealScreenDisplayMetrics() {
    Context context = CubeApp.appContext;
    DisplayMetrics dm = new DisplayMetrics();
    Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
    display.getRealMetrics(dm);
    return dm;
  }

  public static int getStatusBarHeight() {
    Context context = CubeApp.appContext;
    int statusBarHeight = getInternalDimensionSize(context, "status_bar_height");
    return statusBarHeight;
  }

  private static int getInternalDimensionSize(Context context, String key) {
    int result = 0;
    try {
      int resourceId = context.getResources().getIdentifier(key, "dimen", "android");
      if (resourceId > 0) {
        int sizeOne = context.getResources().getDimensionPixelSize(resourceId);
        int sizeTwo = Resources.getSystem().getDimensionPixelSize(resourceId);

        if (sizeTwo >= sizeOne) {
          return sizeTwo;
        } else {
          float densityOne = context.getResources().getDisplayMetrics().density;
          float densityTwo = Resources.getSystem().getDisplayMetrics().density;
          float f = sizeOne * densityTwo / densityOne;
          return (int) ((f >= 0) ? (f + 0.5f) : (f - 0.5f));
        }
      }
    } catch (Exception e) {
    }
    return result;
  }
}
