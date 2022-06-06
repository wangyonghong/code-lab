package com.example.espressodemo;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private ListView expLv;
    private TextView showInputTv;

    private ExpListAdapter expListAdapter;
    private List<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        expLv = findViewById(R.id.activity_list_exp_lv);
        showInputTv = findViewById(R.id.activity_main_show_input_tv);
        data = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            data.add("Item" + i);
        }
        expListAdapter = new ExpListAdapter(data, ListActivity.this);
        expLv.setAdapter(expListAdapter);
        expLv.setOnItemClickListener((parent, view, position, id) -> {
            showInputTv.setText(data.get(position));
        });
    }
}