package com.derry.rxjavastudy.simple01;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.derry.rxjavastudy.R;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    // 打印logcat日志的标签
    private final String TAG = MainActivity.class.getSimpleName();

    // 网络图片的链接地址
    private final static String PATH = "http://pic1.win4000.com/wallpaper/c/53cdd1f7c1f21.jpg";

    // 弹出加载框（正在加载中...）
    private ProgressDialog progressDialog;

    // ImageView控件，用来显示结果图像
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = findViewById(R.id.image);
    }

    /**
     * 图片显示加载功能
     *
     * @param view
     */
    public void showImageAction(View view) {

        /**
         * 如果我们采用传统方式 完成此功能，每位开发者的思想都不一样 （思维不同 ）
         * A同学 线程池
         * B同学 new Thread + Handler
         * C同学 xxx
         * D同学 古老的方式
         * ....
         *
         * 如果采用传统开发方式，我们后面的开发者接手前面开发者的代码，就很痛苦（弊端）
         */


        /**
         * TODO　RX思维
         *
         * 起点 和 终点
         *
         * RxJava RXJS RxXXX  RX系列框架 为什么把所有函数都成为操作符 因为我们的函数要去操作  从起点 流向 终点7
         *
         */

        // TODO 第二步
        // 起点
        Observable.just(PATH)

                // TODO 第三步
                // 需求：001 图片下载需求  PATH ---》 Bitmap
                .map(new Function<String, Bitmap>() {
                    @NonNull
                    @Override
                    public Bitmap apply(@NonNull String path) throws Exception {
                        try {
                            Thread.sleep(2000); // 睡眠2秒钟

                            URL url = new URL(path);
                            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                            httpURLConnection.setConnectTimeout(5000); // 设置请求连接时长 5秒
                            int responseCode = httpURLConnection.getResponseCode(); // 才开始 request    拿到服务器的响应  200成功  404有问题 ...
                            if (responseCode == HttpURLConnection.HTTP_OK) {
                                InputStream inputStream = httpURLConnection.getInputStream();
                                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                                return bitmap;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                })

                // 需求：002 加水印
                .map(new Function<Bitmap, Bitmap>() {
                    @NonNull
                    @Override
                    public Bitmap apply(@NonNull Bitmap bitmap) throws Exception {
                        Paint paint = new Paint();
                        paint.setColor(Color.RED);
                        paint.setTextSize(88);
                        Bitmap shuiyingBitmap = drawTextToBitmap(bitmap, "我是Derry，同学们大家好", paint, 88, 88);
                        return shuiyingBitmap;
                    }
                })

                // 需求：003 日志记录需求
                .map(new Function<Bitmap, Bitmap>() {
                    @NonNull
                    @Override
                    public Bitmap apply(@NonNull Bitmap bitmap) throws Exception {
                        Log.e(TAG, "什么时候下载了图片 apply: " + System.currentTimeMillis() );
                        return bitmap;
                    }
                })


                // 给上面的分配异步线程（图片下载操作）
                .subscribeOn(Schedulers.io())


                // 终点分配 Android主线程
                .observeOn(AndroidSchedulers.mainThread())

                // TODO 导火索 点燃了  开始执行
                // 关联：观察者设计模式   关联 起点 和 终点  ==  订阅
                .subscribe(

                        // 终点
                        new Observer<Bitmap>() {

                            // TODO 第一步
                            // 订阅成功
                            @Override
                            public void onSubscribe(Disposable d) {
                                // 显示加载框
                                progressDialog = new ProgressDialog(MainActivity.this);
                                progressDialog.setTitle("RXJava Derry run 正在加载中..");
                                progressDialog.show();
                            }

                            // TODO 第四步 显示图片   水印的Bitmap
                            // 上一层给我的响应
                            @Override
                            public void onNext(Bitmap bitmap) {
                                image.setImageBitmap(bitmap); // 显示到控件上
                            }

                            // 链条思维发生了异常
                            @Override
                            public void onError(Throwable e) {

                            }

                            // TODO 第五步 整个链条思维全部结束
                            // 整个链条全部结束
                            @Override
                            public void onComplete() {
                                // 隐藏加载框
                                if (progressDialog != null)
                                    progressDialog.dismiss();
                            }
                        });

    }

    // 图片上绘制文字 加水印
    private final Bitmap drawTextToBitmap(Bitmap bitmap, String text, Paint paint, int paddingLeft, int paddingTop) {
        Bitmap.Config bitmapConfig = bitmap.getConfig();

        paint.setDither(true); // 获取跟清晰的图像采样
        paint.setFilterBitmap(true);// 过滤一些
        if (bitmapConfig == null) {
            bitmapConfig = Bitmap.Config.ARGB_8888;
        }
        bitmap = bitmap.copy(bitmapConfig, true);
        Canvas canvas = new Canvas(bitmap);

        canvas.drawText(text, paddingLeft, paddingTop, paint);
        return bitmap;
    }

    /**
     * 常用操作符
     * @param view
     */
    public void action(View view) {

        String[] strings = {"AAA", "BBB", "CCC"};

        // for
        /*for (String string : strings) {

        }*/

        // 起点
        Observable.fromArray(strings)

                // 订阅：起点 和 终点
                .subscribe(new Consumer<String>() {

                    // 终点
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        Log.d(TAG, "accept: " + s);
                    }
                });

    }
}