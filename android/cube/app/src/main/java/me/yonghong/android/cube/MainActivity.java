package me.yonghong.android.cube;

import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import me.yonghong.android.cube.activity.FSDialogActivity;
import me.yonghong.android.cube.activity.animation.FrameActivity;
import me.yonghong.android.cube.activity.animation.InterpolatorActivity;
import me.yonghong.android.cube.activity.animation.PropertyActivity;
import me.yonghong.android.cube.activity.animation.PropertyRotationActivity;
import me.yonghong.android.cube.activity.animation.ShakePhoneActivity;
import me.yonghong.android.cube.activity.animation.TweenActivity;
import me.yonghong.android.cube.compose.FirstComposeActivity;
import me.yonghong.android.cube.compose.animation.AnimatedVisibilityAndContentActivity;

public class MainActivity extends AppCompatActivity {

  private final List<Pair<String, Class<?>>> mExperiments = new ArrayList<>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    registerToLab();
    setContentView(createContentView());
  }

  private void registerToLab() {
    mExperiments.add(new Pair<>("补间动画 Tween Animation", TweenActivity.class));
    mExperiments.add(new Pair<>("逐帧动画 Frame Interpolator", FrameActivity.class));
    mExperiments.add(new Pair<>("属性动画 Property Interpolator", PropertyActivity.class));
    mExperiments.add(new Pair<>("属性动画翻转 Rotation Interpolator", PropertyRotationActivity.class));
    mExperiments.add(new Pair<>("摇手机动画 Shake Phone", ShakePhoneActivity.class));
    mExperiments.add(new Pair<>("动画插值器 Animation Interpolator", InterpolatorActivity.class));
    mExperiments.add(new Pair<>("弹窗 Dialog", FSDialogActivity.class));
    mExperiments.add(new Pair<>("First Compose", FirstComposeActivity.class));
    mExperiments.add(new Pair<>("Compose 高级动画", AnimatedVisibilityAndContentActivity.class));
  }

  private View createContentView() {
    LinearLayout linearLayout = new LinearLayout(this);
    linearLayout.setOrientation(LinearLayout.VERTICAL);
    for (Pair<String, Class<?>> experiment : mExperiments) {
      Button button = new Button(getBaseContext());
      button.setText(experiment.first);
      button.setAllCaps(false);
      button.setOnClickListener(v ->
          startActivity(new Intent(getBaseContext(), experiment.second)));
      linearLayout.addView(button);
    }

    ScrollView scrollView = new ScrollView(this);
    scrollView.addView(linearLayout,
        new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT));
    return scrollView;
  }
}