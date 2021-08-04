package com.enjoy.leo_viewpager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LayoutInflater lf = getLayoutInflater().from(this);
        View view1 = lf.inflate(R.layout.layout1, null);
        View view2 = lf.inflate(R.layout.layout2, null);
        View view3 = lf.inflate(R.layout.layout3, null);

        List<View> viewList = new ArrayList<>();
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);

        ViewPager viewPager = findViewById(R.id.vp);
        MyAdapter myAdapter = new MyAdapter(viewList);
        viewPager.setAdapter(myAdapter);
    }
}