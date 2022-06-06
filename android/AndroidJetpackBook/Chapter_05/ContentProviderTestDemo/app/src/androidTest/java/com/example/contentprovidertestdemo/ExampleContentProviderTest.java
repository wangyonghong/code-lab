package com.example.contentprovidertestdemo;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.provider.ProviderTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class ExampleContentProviderTest {

    @Rule
    public ProviderTestRule mProviderRule =
            new ProviderTestRule.Builder(DataProvider.class, "com.example.contentprovidertestdemo").build();

    @Test
    public void testInsert() {
        ContentResolver resolver = mProviderRule.getResolver();
        ContentValues values = new ContentValues();
        values.put(DataProvider.TITLE, "TimeMillis");
        values.put(DataProvider.CONTENT, System.currentTimeMillis());
        Uri uri = resolver.insert(
                DataProvider.CONTENT_URI, values);
        assertNotNull(uri);
    }
}
