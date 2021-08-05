package com.derry.simplestudy.simple03;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.derry.simplestudy.R;

public class MainActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main1);
    }

    /**
     * 跳转到 MainActivity2
     * @param view
     */
    public void startAction(View view) {

        Intent intent = new Intent(this, MainActivity2.class);

        // 传递对象 到 MainActivity2
        Student student = new Student();
        student.id = 9;
        student.name = "Derry";
        student.age = 33;

        intent.putExtra("Student", student);

        startActivity(intent);

    }
}
