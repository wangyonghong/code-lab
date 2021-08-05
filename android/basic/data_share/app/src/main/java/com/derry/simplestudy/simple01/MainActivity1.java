package com.derry.simplestudy.simple01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.derry.simplestudy.R;

public class MainActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
    }

    /**
     * 跳转
     * @param view
     */
    public void startAction(View view) {
        Intent intent = new Intent(this, MainActivity2.class);
        // 跳转到MainActivity2时携带数据
        intent.putExtra("name", "Derry");
        intent.putExtra("sex", 'M');
        startActivity(intent);
    }
}