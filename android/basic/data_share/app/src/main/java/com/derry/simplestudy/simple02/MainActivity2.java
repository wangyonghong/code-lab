package com.derry.simplestudy.simple02;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.derry.simplestudy.R;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();

        // 自动回去拆开 Bundle对象了   获取里面的name，sex
        String name = intent.getStringExtra("name");
        char sex = intent.getCharExtra("sex", 'A');

        Toast.makeText(this, "name:" + name + " sex:" + sex, Toast.LENGTH_SHORT).show();
    }
}
