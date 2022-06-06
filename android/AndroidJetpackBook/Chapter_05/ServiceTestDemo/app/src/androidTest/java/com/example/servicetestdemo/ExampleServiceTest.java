package com.example.servicetestdemo;

import android.content.Intent;
import android.os.IBinder;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ServiceTestRule;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeoutException;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;

@RunWith(AndroidJUnit4.class)
public class ExampleServiceTest {

    @Rule
    public final ServiceTestRule serviceRule = new ServiceTestRule();

    @Test
    public void testServiceSumFunc() throws TimeoutException {
        int num_1 = 50;
        int num_2 = 150;
        Intent serviceIntent = new Intent(getApplicationContext(), ExampleService.class);
        serviceIntent.putExtra(ExampleService.NUMBER_1, num_1);
        serviceIntent.putExtra(ExampleService.NUMBER_2, num_2);
        IBinder binder = serviceRule.bindService(serviceIntent);
        ExampleService service = ((ExampleService.LocalBinder) binder).getService();
        Assert.assertEquals(service.getSumResult(), num_1 + num_2);
    }
}
