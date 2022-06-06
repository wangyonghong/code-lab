package com.example.databindingdemo;

import android.util.Log;

import java.text.SimpleDateFormat;

public class DateTimeUtil {

    public static String convertLongToDate(Long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(time);
    }

    public static String convertLongToTime(Long time) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        return format.format(time);
    }
}
