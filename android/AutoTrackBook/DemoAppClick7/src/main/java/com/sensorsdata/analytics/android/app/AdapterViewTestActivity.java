package com.sensorsdata.analytics.android.app;

import android.os.Bundle;
import android.view.MenuItem;
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

        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void initGridView() {
        GridView gridView = findViewById(R.id.gridView);
        BaseAdapter adapter = new SAListViewAdapter(this);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener((adapterView, view, position, l) ->
                Toast.makeText(AdapterViewTestActivity.this, "dd：" + position, Toast.LENGTH_SHORT).show()
        );
    }

    private void initListView() {
        ListView listView = findViewById(R.id.listView);
        BaseAdapter adapter = new SAListViewAdapter(this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((adapterView, view, position, l) ->
                Toast.makeText(AdapterViewTestActivity.this, "dd：" + position, Toast.LENGTH_SHORT).show()
        );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
