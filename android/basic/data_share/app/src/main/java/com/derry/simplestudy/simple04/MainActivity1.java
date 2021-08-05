package com.derry.simplestudy.simple04;

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
     * 跳转到 MainActivity2 并传递 对象过去
     * @param view
     */
    public void startAction(View view) {
        Intent intent = new Intent(this, MainActivity2.class);

        Student student = new Student();
        student.name = "DerryOK";
        student.age = 33;

        // 传递对象  -- 让intent 携带过去
        intent.putExtra("student", student);

        startActivity(intent);
    }
}
