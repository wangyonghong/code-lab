package com.example.unittestdemo;

import android.app.Application;
import android.content.Intent;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = Build.VERSION_CODES.P)
public class RobolectricUnitTest {

    @Before
    public void setup() {
        ShadowLog.stream = System.out;
    }

    @Test
    public void functionTest() {
        ActivityScenario<MainActivity> mainActivityActivityScenario = ActivityScenario.launch(MainActivity.class);
        mainActivityActivityScenario.onActivity(activity -> {
            assertEquals(10 + 20, activity.plusCalcExample(10, 20));
        });
    }

    @Test
    public void jumpActivityTest() {
        ActivityScenario<MainActivity> mainActivityActivityScenario = ActivityScenario.launch(MainActivity.class);
        mainActivityActivityScenario.onActivity(activity -> {
            activity.jumpToSecondActivity();
            Intent expectedIntent = new Intent(activity, SecondActivity.class);
            Intent actual = shadowOf((Application) ApplicationProvider.getApplicationContext()).getNextStartedActivity();
            Assert.assertEquals(expectedIntent.getComponent(), actual.getComponent());
        });
    }

    @Test
    public void activityLifeCycleTest() {
        ActivityController<MainActivity> mainActivityActivityController = Robolectric.buildActivity(MainActivity.class);
        mainActivityActivityController.create();
        long startTime = System.currentTimeMillis();
        mainActivityActivityController.resume();
        long duration = System.currentTimeMillis() - startTime;
        Log.d(getClass().getSimpleName(), "resume方法耗时：" + duration);
    }

    @Test
    public void fileIOTest() {
        ActivityScenario<MainActivity> mainActivityActivityScenario = ActivityScenario.launch(MainActivity.class);
        mainActivityActivityScenario.onActivity(activity -> {
            FileUtil fileUtil = new FileUtil();
            String testStr_1 = "测试文本ABC";
            String testStr_2 = "XYZ测试文本";
            String path = ApplicationProvider.getApplicationContext().getExternalFilesDir(null).getPath() + File.separator + "test.txt";
            Log.d("FileIOTest 路径：",path);
            assertTrue(fileUtil.writeStringFile(testStr_1, path));
            assertTrue(new File(path).exists());
            assertEquals(testStr_1, fileUtil.readStringFile(path));
            Log.d("FileIOTest","写文本测试通过");
            assertTrue(fileUtil.writeStringFile(testStr_2, path));
            assertEquals(testStr_2, fileUtil.readStringFile(path));
            Log.d("FileIOTest","覆盖写文本测试通过");
        });
    }
}
