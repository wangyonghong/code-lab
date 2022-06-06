package com.example.navigationuidemo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.appbar.CollapsingToolbarLayout;

public class CollapsingToolbarLayoutActivity extends AppCompatActivity {

    private CollapsingToolbarLayout layout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing_toolbar_layout);
        toolbar = findViewById(R.id.activity_collapsing_toolbar_layout_tb);
        layout = findViewById(R.id.activity_collapsing_toolbar_layout_ctl);
        NavController navController = ((NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.activity_collapsing_toolbar_layout_fcv)).getNavController();
        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(navController.getGraph()).build();
        setSupportActionBar(toolbar);
        NavigationUI.setupWithNavController(layout, toolbar, navController, appBarConfiguration);
    }
}