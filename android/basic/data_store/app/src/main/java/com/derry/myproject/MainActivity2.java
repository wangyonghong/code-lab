package com.derry.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    SharedPreferences sp;

    private EditText et_name;
    private EditText et_pwd;
    private CheckBox cb_remeberpwd;
    private CheckBox cb_autologin;
    private Button bt_login;
    private Button bt_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // 获取首选项 SP
        sp = getSharedPreferences("config", Context.MODE_PRIVATE);
        
        initView();

        // TODO 回显数据
        // 第二次 或者 第N次  打开的时候，从SP获取 之前保存的 数据，进行画面同步
        boolean remeberpwd = sp.getBoolean("remeberpwd", false); // 如果获取是空，就会返回默认值
        boolean autologin = sp.getBoolean("autologin", false); // 如果获取是空，就会返回默认值

        // 记住密码 之前勾上了
        if (remeberpwd) {
            // 获取 sp 里面的 name  和  pwd   并保存到 EditText
            String name = sp.getString("name", "");
            String pwd = sp.getString("pwd", "");
            et_name.setText(name);
            et_pwd.setText(pwd);

            cb_remeberpwd.setChecked(true); // 打上勾
        }

        // 自动登录 之前勾上了
        if (autologin) {
            cb_autologin.setChecked(true); // 打上勾

            // 模拟 自动登录
            Toast.makeText(this, "我自动登录了", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 初始化
     */
    private void initView() {
        // 找到控件
        et_name = findViewById(R.id.et_name);
        et_pwd = findViewById(R.id.et_pwd);
        cb_remeberpwd = findViewById(R.id.cb_remeberpwd);
        cb_autologin = findViewById(R.id.cb_autologin);
        bt_login = findViewById(R.id.bt_login);
        bt_register = findViewById(R.id.bt_register);

        // 设置监听
        MyOnClickListener l = new MyOnClickListener();
        bt_login.setOnClickListener(l);
        bt_register.setOnClickListener(l);
    }

    private class MyOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.bt_register:
                    break;

                // 登录按钮 流程
                case R.id.bt_login:
                    // 登录操作
                    String name = et_name.getText().toString().trim();
                    String pwd = et_pwd.getText().toString().trim();
                    if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)) {
                        Toast.makeText(MainActivity2.this, "用户名或者密码为空", Toast.LENGTH_SHORT).show();
                    } else {
                        // 记住密码 打勾没有
                        if (cb_remeberpwd.isChecked()) {
                            // 用户名和密码都需要保存   同时 记住密码的状态 也要保存
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString("name", name);
                            editor.putString("pwd", pwd);
                            editor.putBoolean("remeberpwd", true); // 记住密码的状态 也要保存
                            editor.apply();
                        }

                        // 自动登录 打勾没有
                        if (cb_autologin.isChecked()) {
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putBoolean("autologin", true); // 自动登录的状态 也要保存
                            editor.apply();
                        }
                    }
                    break;
            }
        }
    }
}