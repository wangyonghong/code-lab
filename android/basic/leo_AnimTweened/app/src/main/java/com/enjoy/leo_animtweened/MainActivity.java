package com.enjoy.leo_animtweened;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = findViewById(R.id.iv);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 通过加载xml动画设置文件来创建一个 Animation 对象
//                Animation animation = AnimationUtils.loadAnimation(MainActivity.this,
//                        R.anim.alpha);

//                Animation animation = AnimationUtils.loadAnimation(MainActivity.this,
//                        R.anim.rotate);

//                Animation animation = AnimationUtils.loadAnimation(MainActivity.this,
//                        R.anim.scale);

                Animation animation = AnimationUtils.loadAnimation(MainActivity.this,
                        R.anim.translate);

                imageView.startAnimation(animation);
            }
        });
    }
}