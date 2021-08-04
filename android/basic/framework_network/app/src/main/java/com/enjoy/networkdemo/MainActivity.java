package com.enjoy.networkdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private OkHttpClient okHttpClient;
    private Retrofit retrofit;
    private HttpbinService httpbinService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        okHttpClient = new OkHttpClient();
        retrofit = new Retrofit.Builder().baseUrl("https://www.httpbin.org/").build();
        httpbinService = retrofit.create(HttpbinService.class);
    }

    public void getSync(View view) {
        new Thread() {
            @Override
            public void run() {
                Request request = new Request.Builder().url("https://www.httpbin.org/get?a=1&b=2").build();
                // 准备好请求的Call对象
                Call call = okHttpClient.newCall(request);
                try {
                    Response response = call.execute();
                    Log.i(TAG, "getSync: " + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void getAsync(View view) {
        Request request = new Request.Builder().url("https://www.httpbin.org/get?a=1&b=2")
                .build();
        // 准备好请求的Call对象
        Call call = okHttpClient.newCall(request);
        //异步请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    Log.i(TAG, "getAsync: " + response.body().string());
                }
            }
        });
        //...
    }

    public void postSync(View view) {
        new Thread() {
            @Override
            public void run() {
                FormBody formBody = new FormBody.Builder()
                        .add("a", "1").add("b", "2").build();
                Request request = new Request.Builder().url("https://www.httpbin.org/post")
                        .post(formBody).build();
                // 准备好请求的Call对象
                Call call = okHttpClient.newCall(request);
                try {
                    Response response = call.execute();
                    Log.i(TAG, "postSync: " + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void postAsync(View view) {
//        FormBody formBody = new FormBody.Builder()
//                .add("a", "1").add("b", "2").build();
//        Request request = new Request.Builder().url("https://www.httpbin.org/post")
//                .post(formBody).build();
//        // 准备好请求的Call对象
//        Call call = okHttpClient.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(@NotNull Call call, @NotNull IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
//                Log.i(TAG, "postAsync: " + response.body().string());
//            }
//        });

        retrofit2.Call<ResponseBody> call = httpbinService.post("lance", "123");
        call.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(retrofit2.Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                try {
                    Log.i(TAG, "postAsync: " + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}