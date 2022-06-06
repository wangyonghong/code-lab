package com.example.actionmenuviewdemo;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ActionMenuView;

public class MainActivity extends AppCompatActivity {

    private ActionMenuView actionMenuView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionMenuView = findViewById(R.id.activity_main_amv);
        actionMenuView.setOnMenuItemClickListener(item -> {
            Toast.makeText(MainActivity.this, "点击了：" + item.getTitle(), Toast.LENGTH_SHORT).show();
            return true;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu, actionMenuView.getMenu());
        return super.onCreateOptionsMenu(menu);
    }

}