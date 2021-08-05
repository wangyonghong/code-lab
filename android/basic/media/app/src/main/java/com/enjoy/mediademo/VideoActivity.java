package com.enjoy.mediademo;

import android.hardware.Camera;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.IOException;

public class VideoActivity extends AppCompatActivity implements View.OnClickListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {
    private TextureView textureView;
    private Button btn_opt;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        textureView = findViewById(R.id.textureView);
        btn_opt = findViewById(R.id.btn_opt);

        btn_opt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        CharSequence text = btn_opt.getText();
        if (TextUtils.equals(text, "开始")) {
            btn_opt.setText("结束");
            mediaPlayer = new MediaPlayer();
            //设置准备监听
            mediaPlayer.setOnPreparedListener(this);
            mediaPlayer.setOnCompletionListener(this);
            try {
                //指定视频源
                mediaPlayer.setDataSource(new File(getExternalFilesDir(""), "a.mp4").getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.setSurface(new Surface(textureView.getSurfaceTexture()));
            mediaPlayer.prepareAsync();
        } else {
            btn_opt.setText("开始");
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mediaPlayer.start();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        btn_opt.setText("开始");
        mediaPlayer.release();
    }
}
