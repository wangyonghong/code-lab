package com.derry.simplestudy.simple01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.derry.simplestudy.R;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        
        // 接收携带过来的数据
        Intent intent = getIntent();

        String name = intent.getStringExtra("name");
        char sex = intent.getCharExtra("sex", 'A');
        Toast.makeText(this, "name:" + name + "  sex:" + sex, Toast.LENGTH_SHORT).show();
    }
}