package com.enjoy.networkdemo;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws IOException {
        assertEquals(4, 2 + 2);
        File file = new File("C:\\Users\\Administrator\\Desktop\\derry.txt");
        RequestBody requestBody =  RequestBody.create(file,MediaType.parse("application/octet-stream"));
        MultipartBody body = new MultipartBody.Builder()
                .addFormDataPart("file","derry.txt",requestBody)
                .addFormDataPart("city","22").build();

    }
}