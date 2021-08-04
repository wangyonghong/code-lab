package com.enjoy.mediademo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO},
                100);
    }

    public void record(View view) {
        startActivity(new Intent(this, MediaRecordActivity.class));
    }

    public void playVideo(View view) {
        startActivity(new Intent(this, VideoViewActivity.class));
    }

    public void playAudio(View view) {
        startActivity(new Intent(this, SoundActivity.class));
    }

}