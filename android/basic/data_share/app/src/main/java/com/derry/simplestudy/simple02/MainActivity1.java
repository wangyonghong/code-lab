package com.derry.simplestudy.simple02;

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

        // 封装一个包裹 Bundle 对象
        Bundle bundle = new Bundle();
        bundle.putString("name", "Derry");
        bundle.putChar("sex", 'M');
        // put XXX 100 个字段

        // intent携带我们的Bundle对象
        intent.putExtras(bundle);

        startActivity(intent);

    }
}
