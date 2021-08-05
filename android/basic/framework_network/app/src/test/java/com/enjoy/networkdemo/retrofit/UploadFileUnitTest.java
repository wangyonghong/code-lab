package com.enjoy.networkdemo.retrofit;

import com.enjoy.networkdemo.HttpbinService;
import com.enjoy.networkdemo.UploadService;

import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UploadFileUnitTest {
    Retrofit retrofit = new Retrofit.Builder().baseUrl("https://www.httpbin.org/").build();
    UploadService uploadService = retrofit.create(UploadService.class);

    @Test
    public void uploadFileTest() throws IOException {
        File file1 = new File("C:\\Users\\Administrator\\Desktop\\1.txt");
        MultipartBody.Part part = MultipartBody.Part.createFormData("file1",
                "1.txt", RequestBody.create(file1, MediaType.parse("text/plain")));

        Call<ResponseBody> call =
                uploadService.upload(part);
        System.out.println(call.execute().body().string());
    }

    @Test
    public void downloadTest() throws IOException {
        Response<ResponseBody> response = uploadService.download(
                "https://fga1.market.xiaomi.com/download/AppStore/07adf043b0b2c40371abc6c685363e83d27f3efd7/com.sdu.didi.psnger.apk")
                .execute();
//        response.isSuccessful()
        InputStream inputStream = response.body().byteStream();
        FileOutputStream fos = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\a.apk");
        int len;
        byte[] buffer = new byte[4096];

        while ((len = inputStream.read(buffer)) != -1) {
            fos.write(buffer, 0, len);
        }
        fos.close();
        inputStream.close();
    }

    @Test
    public void downloadRxjavaTest() {
        uploadService.downloadRxJava("https://fga1.market.xiaomi.com/download/AppStore/07adf043b0b2c40371abc6c685363e83d27f3efd7/com.sdu.didi.psnger.apk")
                .map(new Function<ResponseBody, File>() {
                    @Override
                    public File apply(ResponseBody responseBody) throws Throwable {
                        InputStream inputStream = responseBody.byteStream();
                        File file = new File("C:\\Users\\Administrator\\Desktop\\a.apk");
                        FileOutputStream fos = new FileOutputStream(file);
                        int len;
                        byte[] buffer = new byte[4096];

                        while ((len = inputStream.read(buffer)) != -1) {
                            fos.write(buffer, 0, len);
                        }
                        fos.close();
                        inputStream.close();
                        return file;
                    }
                }).subscribe(new Consumer<File>() {
            @Override
            public void accept(File file) throws Throwable {

            }
        });
        while (true) {
        }
    }
}
