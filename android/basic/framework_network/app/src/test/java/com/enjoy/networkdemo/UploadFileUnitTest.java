package com.enjoy.networkdemo;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UploadFileUnitTest {

    @Test
    public void uploadFileTest() throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        File file1 = new File("C:\\Users\\Administrator\\Desktop\\1.txt");
        File file2 = new File("C:\\Users\\Administrator\\Desktop\\2.txt");
        MultipartBody multipartBody = new MultipartBody.Builder()
                .addFormDataPart("file1", file1.getName(), RequestBody.create(file1, MediaType.parse("text/plain")))
                .addFormDataPart("file2", file2.getName(), RequestBody.create(file2, MediaType.parse("text/plain")))
                .addFormDataPart("a", "1")
                .build();
        Request request = new Request.Builder().url("https://www.httpbin.org/post").post(multipartBody).build();
        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        System.out.println(response.body().string());
    }

    @Test
    public void jsonTest() throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody =
                RequestBody.create("{\"a\":1,\"b\":2}", MediaType.parse("application/json"));
        Request request = new Request.Builder().url("https://www.httpbin.org/post").post(requestBody).build();
        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        System.out.println(response.body().string());

    }
}
