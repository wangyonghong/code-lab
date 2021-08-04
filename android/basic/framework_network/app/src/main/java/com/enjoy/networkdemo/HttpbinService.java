package com.enjoy.networkdemo;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface HttpbinService {

    @POST("post")
    @FormUrlEncoded
    Call<ResponseBody> post(@Field("username") String userName, @Field("password") String pwd);

    @GET("get")
    Call<ResponseBody> get(@Query("username") String userName, @Query("password") String pwd);

    @HTTP(method = "POST", path = "get", hasBody = true)
    Call<ResponseBody> http(@Query("username") String userName, @Query("password") String pwd);


    @POST("post")
    Call<ResponseBody> postBody(@Body RequestBody body);


    @POST("{id}")
    @FormUrlEncoded
    Call<ResponseBody> postInPath(@Path("id") String path, @Header("os") String os, @Field("username") String userName, @Field("password") String pwd);


    @Headers({"os:android", "version:1.0"})
    @POST("post")
    Call<ResponseBody> postWithHeaders();


    @POST
    Call<ResponseBody> postUrl(@Url String url);





}
