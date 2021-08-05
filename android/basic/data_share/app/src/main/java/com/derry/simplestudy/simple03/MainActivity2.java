package com.derry.simplestudy.simple03;

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

        Student student = (Student) intent.getSerializableExtra("Student");

        // 提示 显示
        Toast.makeText(this, "student.id:" + student.id
                + " student.name:" + student.name
                + " student.age:" + student.age,
                Toast.LENGTH_SHORT).show();
    }
}
