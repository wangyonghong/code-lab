package com.example.appcompatdelegatedemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatCallback;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;

public class MainActivity extends Activity implements AppCompatCallback {

    private AppCompatDelegate appCompatDelegate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        appCompatDelegate = AppCompatDelegate.create(this, this);
        appCompatDelegate.onCreate(savedInstanceState);
        appCompatDelegate.setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_main_tb);
        appCompatDelegate.setSupportActionBar(toolbar);
    }

    @Override
    public void onSupportActionModeStarted(ActionMode mode) {

    }

    @Override
    public void onSupportActionModeFinished(ActionMode mode) {

    }

    @Nullable
    @Override
    public ActionMode onWindowStartingSupportActionMode(ActionMode.Callback callback) {
        return null;
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        appCompatDelegate.onPostCreate(savedInstanceState);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        appCompatDelegate.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onStart() {
        super.onStart();
        appCompatDelegate.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        appCompatDelegate.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        appCompatDelegate.onDestroy();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        appCompatDelegate.onPostResume();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        appCompatDelegate.onSaveInstanceState(outState);
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        appCompatDelegate.setTitle(title);
    }
}