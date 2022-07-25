package com.sensorsdata.analytics.android.app;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AdapterViewTestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter_view_test);
        initListView();
        initGridView();
    }

    @SuppressWarnings("Convert2Lambda")
    private void initGridView() {
        GridView gridView = findViewById(R.id.gridView);
        BaseAdapter adapter = new SAListViewAdapter(this);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(AdapterViewTestActivity.this, "dd：" + position,
                    Toast.LENGTH_SHORT).show();
            }
        });
    }

    @SuppressWarnings("Convert2Lambda")
    private void initListView() {
        ListView listView = findViewById(R.id.listView);
        BaseAdapter adapter = new SAListViewAdapter(this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(AdapterViewTestActivity.this, "dd：" + position,
                    Toast.LENGTH_SHORT).show();
            }
        });
    }
}
