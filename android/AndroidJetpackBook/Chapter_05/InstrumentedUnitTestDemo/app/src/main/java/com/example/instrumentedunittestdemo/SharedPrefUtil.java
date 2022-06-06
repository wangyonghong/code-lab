package com.example.instrumentedunittestdemo;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefUtil {

    private static SharedPreferences sharedPreferences;
    private static SharedPrefUtil instance;

    private SharedPrefUtil(Context context) {
        sharedPreferences = context.getSharedPreferences("test", Context.MODE_PRIVATE);
    }

    public static SharedPrefUtil getInstance(Context context) {
        if (instance == null) {
            synchronized (SharedPrefUtil.class) {
                if (instance == null)
                    instance = new SharedPrefUtil(context.getApplicationContext());
            }
        }
        return instance;
    }

    public void setStringData(String str) {
        sharedPreferences.edit().putString("testStr", str).apply();
    }

    public String getStringData() {
        return sharedPreferences.getString("testStr", "");
    }

}
