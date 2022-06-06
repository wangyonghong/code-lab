package com.example.navigationuidemo;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class ToolbarWithDrawerLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar_with_drawer_layout);
        Toolbar toolbar = findViewById(R.id.activity_toolbar_with_drawer_tb);
        DrawerLayout drawerLayout = findViewById(R.id.activity_toolbar_with_drawer_dl);
        NavigationView navigationView = findViewById(R.id.activity_toolbar_with_drawer_nv);
        BottomNavigationView bottomNavigationView = findViewById(R.id.activity_toolbar_with_drawer_bnv);
        setSupportActionBar(toolbar);
        NavController navController = ((NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.activity_toolbar_with_drawer_fcv)).getNavController();
        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(navController.getGraph()).setOpenableLayout(drawerLayout).build();
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }
}