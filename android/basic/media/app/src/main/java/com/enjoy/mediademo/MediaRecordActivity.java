package com.enjoy.mediademo;

import android.hardware.Camera;
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

public class MediaRecordActivity extends AppCompatActivity implements View.OnClickListener {
    private TextureView textureView;
    private Button btn_opt;
    private MediaRecorder mediaRecorder;
    private Camera camera;

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
            camera = Camera.open();
            camera.setDisplayOrientation(90);
            camera.unlock();
            mediaRecorder = new MediaRecorder();
            mediaRecorder.setCamera(camera);
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC); //设置音频源 麦克风
            mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);//设备视频源 摄像头
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);//指定视频文件格式
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
            mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
            mediaRecorder.setOrientationHint(90);
            //设置视频输出文件
            mediaRecorder.setOutputFile(new File(getExternalFilesDir(""), "a.mp4").getAbsolutePath());
            mediaRecorder.setVideoSize(640, 480);
            mediaRecorder.setPreviewDisplay(new Surface(textureView.getSurfaceTexture()));
            try {
                mediaRecorder.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaRecorder.start();
        } else {
            btn_opt.setText("开始");
            mediaRecorder.stop();
            mediaRecorder.release();
            camera.stopPreview();
            camera.release();
        }

    }
}
