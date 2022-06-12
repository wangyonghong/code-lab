package com.example.appbardemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private final String TAG = getClass().getSimpleName();

    private Button jumpToSubBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.activity_main_tb);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setSubtitle(R.string.app_name);
        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.activity_main_menu_item_1:
                    Toast.makeText(MainActivity.this, "Search", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.activity_main_menu_item_2:
                    Toast.makeText(MainActivity.this, "Menu 2", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.activity_main_menu_item_3:
                    Toast.makeText(MainActivity.this, "Menu 3", Toast.LENGTH_SHORT).show();
                    break;
            }
            return false;
        });
        jumpToSubBtn = findViewById(R.id.activity_main_jump_to_sub_btn);
        jumpToSubBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, SubActivity.class)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.activity_main_menu_item_1).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(MainActivity.this, "搜索：" + query, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }
}