package com.example.viewmodeldemo;

import androidx.lifecycle.ViewModel;

public class StartTimeViewModel extends ViewModel {

    private long currentTimeMillis;

    public void fetchCurrentTimeInMillis(OnStartTimeFetchedListener onStartTimeFetchedListener) {
        if (currentTimeMillis == 0) {
            currentTimeMillis = System.currentTimeMillis();
        }
        onStartTimeFetchedListener.onStartTimeFetched(currentTimeMillis);
    }

    interface OnStartTimeFetchedListener {
        void onStartTimeFetched(long currentTimeMillis);
    }
}
