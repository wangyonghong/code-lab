package com.enjoy.mediademo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class VideoViewActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videoview);
        VideoView videoView = findViewById(R.id.videoView);
        MediaController mediaController = new MediaController(this);
        mediaController.setPrevNextListeners(this, this);
        videoView.setMediaController(mediaController);
        videoView.setVideoPath(new File(getExternalFilesDir(""), "a.mp4").getAbsolutePath());
        videoView.start();
    }

    @Override
    public void onClick(View v) {
        Log.i("VideoView","====");
    }
}
