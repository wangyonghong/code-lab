package com.example.lifecycledemo;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public class VideoPlayerObserver implements LifecycleObserver {

    private final String TAG = getClass().getSimpleName();

    private static class VideoPlayerObserverHolder {
        private static final VideoPlayerObserver INSTANCE = new VideoPlayerObserver();
    }

    private VideoPlayerObserver() {
    }

    public static VideoPlayerObserver getInstance() {
        return VideoPlayerObserverHolder.INSTANCE;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void createPlayer() {
        Log.d(TAG, "createPlayer");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void startPlay() {
        Log.d(TAG, "startPlay");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void resumePlay() {
        Log.d(TAG, "resumePlay");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void pausePlay() {
        Log.d(TAG, "pausePlay");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void stopPlay() {
        Log.d(TAG, "stopPlay");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void destroyPlayer() {
        Log.d(TAG, "destroyPlayer");
    }

}
