package com.example.bindingadapterdemo;

import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableLong;

public class DateTimeTextViewBindingAdapter {

    @BindingAdapter(value = {"formattedDateTime", "isOnlyDate"}, requireAll = true)
    public static void setFormattedDateTime(TextView textView, ObservableLong rawTimeMillis, boolean isOnlyDate) {
        if (isOnlyDate) {
            textView.setText(DateTimeUtil.convertLongToDate(rawTimeMillis.get()));
        } else {
            textView.setText(DateTimeUtil.convertLongToTime(rawTimeMillis.get()));
        }
    }
}
