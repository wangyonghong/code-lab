package com.example.uiautomatordemo;

import android.content.Context;
import android.content.Intent;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SdkSuppress;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;
import androidx.test.uiautomator.Until;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)
public class UiAutomatorTestDemo {

    private UiDevice uiDevice;
    private static final int STEP_WAIT_TIMEOUT = 5000;
    private static final String MY_PACKAGE = "com.example.uiautomatordemo";

    @Test
    public void testTakePhoto() throws UiObjectNotFoundException {
        uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        uiDevice.pressHome();
        final String launcherPackage = uiDevice.getLauncherPackageName();
        uiDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)),
                STEP_WAIT_TIMEOUT);
        Context context = ApplicationProvider.getApplicationContext();
        Intent intent = context.getPackageManager()
                .getLaunchIntentForPackage(MY_PACKAGE);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
        uiDevice.wait(Until.hasObject(By.pkg(MY_PACKAGE).depth(0)),
                STEP_WAIT_TIMEOUT);
        UiObject takePhotoBtn = uiDevice.findObject(new UiSelector()
                .text("点击拍照")
                .className("android.widget.Button"));
        takePhotoBtn.click();
        uiDevice.wait(Until.findObject(By.res("com.android.camera2", "shutter_button")), STEP_WAIT_TIMEOUT);
        UiObject takePhotoIv = uiDevice.findObject(new UiSelector()
                .resourceId("com.android.camera2:id/shutter_button")
                .className("android.widget.ImageView"));
        takePhotoIv.click();
        uiDevice.wait(Until.findObject(By.res("com.android.camera2", "done_button")), STEP_WAIT_TIMEOUT);
        UiObject confirmPhotoIb = uiDevice.findObject(new UiSelector()
                .resourceId("com.android.camera2:id/done_button")
                .className("android.widget.ImageButton"));
        confirmPhotoIb.click();
        uiDevice.wait(Until.findObject(By.res("com.example.uiautomatordemo", "activity_main_show_photo_iv")), STEP_WAIT_TIMEOUT);
        UiObject showPhotoIv = uiDevice.findObject(new UiSelector()
                .resourceId("com.example.uiautomatordemo:id/activity_main_show_photo_iv")
                .className("android.widget.ImageView"));
        assertTrue(showPhotoIv.getBounds().width() > 0 && showPhotoIv.getBounds().height() > 0);
    }
}
