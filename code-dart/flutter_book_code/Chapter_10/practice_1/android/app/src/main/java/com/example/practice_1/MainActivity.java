package com.example.practice_1;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import io.flutter.app.FlutterActivity;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugins.GeneratedPluginRegistrant;

public class MainActivity extends FlutterActivity {

    private static final String CHANNEL = "methodchannel.practice.com/calc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GeneratedPluginRegistrant.registerWith(this);
        new MethodChannel(getFlutterView(), CHANNEL).setMethodCallHandler(new MethodCallHandler() {
            @Override
            public void onMethodCall(MethodCall methodCall, Result result) {
                if (methodCall.method.equals("getCalcResult")) {
                    ArrayList num = (ArrayList) methodCall.arguments;
                    int calcResult = calc((int) num.get(0), (int) num.get(1));
                    if (calcResult == 0) {
                        result.error("计算出错", "无法计算相加结果", null);
                    } else {
                        result.success(calcResult);
                    }
                } else {
                    result.notImplemented();
                }
            }
        });
    }

    private int calc(int a, int b) {
        return a + b;
    }

}
