package com.derry.rxjavastudy.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.derry.rxjavastudy.R;
import com.derry.rxjavastudy.login.bean.SuccessBean;
import com.derry.rxjavastudy.login.core.CustomObserver;
import com.derry.rxjavastudy.login.core.LoginEngine;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /**
         * RX思维解决此问题，完成Derry提出的苛刻需求
         * TODO 我的需求： 如果登录成功  我只想拿到  成功Bean==SuccessBean
         *                 如果登录失败  我只想拿到  message String类型
         */

        LoginEngine.login("Derry", "123456")
            // 起点<总Bean>

        .subscribe(new CustomObserver() {
            @Override
            public void success(SuccessBean successBean) {
                Log.d("Derry", "成功的Bean 详情 success: SuccessBean: " + successBean.toString());
            }

            @Override
            public void error(String message) {
                Log.d("Derry", "String类型 error: message:" + message);
            }
        });

    }
}