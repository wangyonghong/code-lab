package com.example.instrumentedunittestdemo;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.internal.inject.InstrumentationContext;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class InstrumentedUnitTest {

    private final String STRING_DATA = "TEST_ABC";

    @Test
    public void getString() {
        Context context = ApplicationProvider.getApplicationContext();
        SharedPrefUtil.getInstance(context).setStringData(STRING_DATA);
        String str = SharedPrefUtil.getInstance(context).getStringData();
        Assert.assertEquals(STRING_DATA, str);
    }

}
