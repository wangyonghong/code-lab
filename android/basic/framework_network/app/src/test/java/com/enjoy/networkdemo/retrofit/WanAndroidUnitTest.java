package com.enjoy.networkdemo.retrofit;

import com.enjoy.networkdemo.CookieUnitTest;
import com.enjoy.networkdemo.HttpbinService;
import com.enjoy.networkdemo.WanAndroidService;
import com.enjoy.networkdemo.WanAndroidService2;
import com.enjoy.networkdemo.bean.BaseResponse;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import org.reactivestreams.Publisher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class WanAndroidUnitTest {
    Retrofit retrofit = new Retrofit.Builder().baseUrl("https://www.wanandroid.com/").build();
    WanAndroidService wanAndroidService = retrofit.create(WanAndroidService.class);

    @Test
    public void loginTest() throws IOException {
        Call<ResponseBody> call =
                wanAndroidService.login("lanceedu", "123123");
        Response<ResponseBody> response = call.execute();
        String result = response.body().string();
        System.out.println(result);
        //手动进行数据转换
        BaseResponse baseResponse = new Gson().fromJson(result, BaseResponse.class);
        System.out.println(baseResponse);
    }

    Retrofit retrofit2 = new Retrofit.Builder()
            .baseUrl("https://www.wanandroid.com/")
            .addConverterFactory(GsonConverterFactory.create()) //添加转换器
            .build();
    WanAndroidService2 wanAndroidService2 = retrofit2.create(WanAndroidService2.class);

    @Test
    public void loginConvertTest() throws IOException {
        Call<BaseResponse> call = wanAndroidService2.login("lanceedu", "123123");
        Response<BaseResponse> response = call.execute();
        BaseResponse baseResponse = response.body();
        System.out.println(baseResponse);
    }

    Map<String, List<Cookie>> cookies = new HashMap<>();

    Retrofit retrofit3 = new Retrofit.Builder()
            .baseUrl("https://www.wanandroid.com/")
            .callFactory(new OkHttpClient.Builder()
                    .cookieJar(new CookieJar() {
                        @Override
                        public void saveFromResponse(@NotNull HttpUrl httpUrl, @NotNull List<Cookie> list) {
                            cookies.put(httpUrl.host(), list);
                        }

                        @NotNull
                        @Override
                        public List<Cookie> loadForRequest(@NotNull HttpUrl httpUrl) {
                            List<Cookie> cookies = WanAndroidUnitTest.this.cookies.get(httpUrl.host());
                            return cookies == null ? new ArrayList<>() : cookies;
                        }
                    }).build())
            .addConverterFactory(GsonConverterFactory.create()) //添加转换器
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create()) // 添加适配器
            .build();
    WanAndroidService2 wanAndroidService3 = retrofit3.create(WanAndroidService2.class);

    @Test
    public void rxjavaTest() {
        wanAndroidService3.login2("lanceedu", "123123")
                .flatMap(new Function<BaseResponse, Publisher<ResponseBody>>() {
                    @Override
                    public Publisher<ResponseBody> apply(BaseResponse baseResponse) throws Throwable {
                        return wanAndroidService3.getArticle(0);
                    }
                })
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Throwable {
                        System.out.println(responseBody.string());
                    }
                });
        while (true) {
        }
    }
}
