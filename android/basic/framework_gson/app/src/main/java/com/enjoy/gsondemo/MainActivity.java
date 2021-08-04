package com.enjoy.gsondemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;

import java.util.Map;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Map<Integer,Integer> map;
//
//        //设置可容纳5个音频流
//        SoundPool soundPool = new SoundPool.Builder().setMaxStreams(5).build();
//        //加载音效，load方法返回soundId，将其记录在map集合中
//        map.put(1,soundPool.load(this,R.raw.a,1));
//        map.put(2,soundPool.load(this,R.raw.b,1));
//        map.put(3,soundPool.load(this,R.raw.c,1));
//
//
//        //音频源和音频输出管理者
//        AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
//        //获得最大音量
//        float audioMaxVolumn = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
//        //获得当前音量
//        float volumnCurrent = am.getStreamVolume(AudioManager.STREAM_MUSIC);
//        float volumnRatio = volumnCurrent / audioMaxVolumn;
//
//        soundPool.play(map.get(1),
//                volumnRatio,// 左声道音量 0.0-1.0
//                volumnRatio,// 右声道音量
//                1, // 优先级 0最低
//                0,// 循环播放次数，0为不循环，-1是一直循环
//                1);// 回放速度，该值在0.5-2.0之间 1为正常速度
//        //暂停播放
//        soundPool.pause(map.get(1));
//        //继续播放
//        soundPool.resume(map.get(1));
//        //停止播放
//        soundPool.stop(map.get(1));
//        //卸载音效
//        soundPool.unload(map.get(1));
//        //释放
//        soundPool.release();
    }
}