package com.enjoy.networkdemo;

import java.io.File;

import io.reactivex.rxjava3.core.Flowable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface UploadService {

    @POST("post")
    @Multipart
    Call<ResponseBody> upload(@Part MultipartBody.Part file);


    @Streaming
    @GET
    Call<ResponseBody> download(@Url String url);

    @Streaming
    @GET
    Flowable<ResponseBody> downloadRxJava(@Url String url);
}
