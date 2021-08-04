package com.derry.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     *
     *
     *
     *     // 参数1：SP的名字
     *     // 参数2：SP保存的时候用的模式  常规(每次保存都会更新)BBB == Context.MODE_PRIVATE，
     *                                 追加(每次保存都会追加到后面) DerryAAABBB==Context.MODE_APPEND
     *     @Override
     *     public SharedPreferences getSharedPreferences(String name, int mode) {
     *         return mBase.getSharedPreferences(name, mode);
     *     }
     *
     */

    /**
     * 保存到SP
     * @param view
     */
    public void saveToSP(View view) {
        SharedPreferences sp = getSharedPreferences("SPDerryName", Context.MODE_PRIVATE);
        sp.edit().putString("DerryKey", "九阳神功").apply(); // apply才会写入到 xml配置文件里面去
    }

    /**
     * 获取SP的数据
     * @param view
     */
    public void getSpData(View view) {

        SharedPreferences sp = getSharedPreferences("SPDerryName", Context.MODE_PRIVATE);

        String value = sp.getString("DerryKey", "默认值");// 假设 DerryKey 获取的值是空的，那么就会使用 默认值

        Toast.makeText(this, "" + value, Toast.LENGTH_SHORT).show();

    }
}