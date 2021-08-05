package com.derry.simplestudy.simple04;

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

        Student student = intent.getParcelableExtra("student");

        // 显示 MainActivity1 传递过来的对象 里面的数据
        Toast.makeText(this, "student.name:" + student.name
                + " student.age:" + student.age , Toast.LENGTH_SHORT).show();
    }
}
