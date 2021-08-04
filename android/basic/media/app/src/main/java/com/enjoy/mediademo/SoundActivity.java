package com.enjoy.mediademo;

import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SoundActivity extends AppCompatActivity implements MyAdapter.OnItemClickListener {

    private SoundPool soundPool;

    static class Sound {
        String name;
        int soundId;

        public Sound(String name, int soundId) {
            this.name = name;
            this.soundId = soundId;
        }

        public int getSoundId() {
            return soundId;
        }

        public String getName() {
            return name;
        }
    }

    List<Sound> data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        soundPool = new SoundPool.Builder().setMaxStreams(6).build();

        data = new ArrayList<>();
        data.add(new Sound("a4", soundPool.load(this, R.raw.a4, 1)));
        data.add(new Sound("a5", soundPool.load(this, R.raw.a5, 1)));
        data.add(new Sound("a6", soundPool.load(this, R.raw.a6, 1)));
        data.add(new Sound("a7", soundPool.load(this, R.raw.a7, 1)));
        data.add(new Sound("a8", soundPool.load(this, R.raw.a8, 1)));
        data.add(new Sound("a9", soundPool.load(this, R.raw.a9, 1)));
        MyAdapter myAdapter = new MyAdapter(data, recyclerView, this);
        myAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(myAdapter);

    }

    @Override
    public void onItemClick(int position) {
        Sound sound = data.get(position);
        soundPool.play(sound.getSoundId(),
                1.0f, 1.0f, 1, 0, 1.0f);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        for (Sound datum : data) {
            soundPool.unload(datum.getSoundId());
        }
        soundPool.release();
    }
}
