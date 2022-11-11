package me.yonghong.android.cube.activity.animation;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import me.yonghong.android.cube.R;
import me.yonghong.android.cube.util.DisplayUtils;
import me.yonghong.android.cube.util.PxUtils;

/**
 * 插值器
 */
public class InterpolatorActivity extends AppCompatActivity {

  private FrameLayout mCube1, mCube2, mCube3, mCube4, mCube5, mCube6, mCube9, mCube10;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_animation_interpolator);
    mCube1 = findViewById(R.id.cube_1);
    mCube2 = findViewById(R.id.cube_2);
    mCube3 = findViewById(R.id.cube_3);
    mCube4 = findViewById(R.id.cube_4);
    mCube5 = findViewById(R.id.cube_5);
    mCube6 = findViewById(R.id.cube_6);
    mCube9 = findViewById(R.id.cube_9);
    mCube10 = findViewById(R.id.cube_10);

    float toXDelta = DisplayUtils.getRealScreenWidth() - PxUtils.dpToPx(40);
    TranslateAnimation animation1 = new TranslateAnimation(0, toXDelta, 0, 0);
    animation1.setDuration(2000);
    animation1.setRepeatMode(Animation.REVERSE);
    animation1.setRepeatCount(ValueAnimator.INFINITE);
    animation1.setInterpolator(this, android.R.anim.accelerate_decelerate_interpolator);
    mCube1.startAnimation(animation1);

    TranslateAnimation animation2 = new TranslateAnimation(0, toXDelta, 0, 0);
    animation2.setDuration(2000);
    animation2.setRepeatMode(Animation.REVERSE);
    animation2.setRepeatCount(ValueAnimator.INFINITE);
    animation2.setInterpolator(this, android.R.anim.accelerate_interpolator);
    mCube2.startAnimation(animation2);

    TranslateAnimation animation3 = new TranslateAnimation(0, toXDelta, 0, 0);
    animation3.setDuration(2000);
    animation3.setRepeatMode(Animation.REVERSE);
    animation3.setRepeatCount(ValueAnimator.INFINITE);
    animation3.setInterpolator(this, android.R.anim.anticipate_interpolator);
    mCube3.startAnimation(animation3);

    TranslateAnimation animation4 = new TranslateAnimation(0, toXDelta, 0, 0);
    animation4.setDuration(2000);
    animation4.setRepeatMode(Animation.REVERSE);
    animation4.setRepeatCount(ValueAnimator.INFINITE);
    animation4.setInterpolator(this, android.R.anim.anticipate_overshoot_interpolator);
    mCube4.startAnimation(animation4);

    TranslateAnimation animation5 = new TranslateAnimation(0, toXDelta, 0, 0);
    animation5.setDuration(2000);
    animation5.setRepeatMode(Animation.REVERSE);
    animation5.setRepeatCount(ValueAnimator.INFINITE);
    animation5.setInterpolator(this, android.R.anim.bounce_interpolator);
    mCube5.startAnimation(animation5);


    TranslateAnimation animation6 = new TranslateAnimation(0, toXDelta, 0, 0);
    animation6.setDuration(2000);
    animation6.setRepeatMode(Animation.REVERSE);
    animation6.setRepeatCount(ValueAnimator.INFINITE);
    animation6.setInterpolator(this, android.R.anim.decelerate_interpolator);
    mCube6.startAnimation(animation6);

    TranslateAnimation animation9 = new TranslateAnimation(0, toXDelta, 0, 0);
    animation9.setDuration(2000);
    animation9.setRepeatMode(Animation.REVERSE);
    animation9.setRepeatCount(ValueAnimator.INFINITE);
    animation9.setInterpolator(this, android.R.anim.linear_interpolator);
    mCube9.startAnimation(animation9);

    TranslateAnimation animation10 = new TranslateAnimation(0, toXDelta, 0, 0);
    animation10.setDuration(2000);
    animation10.setRepeatMode(Animation.REVERSE);
    animation10.setRepeatCount(ValueAnimator.INFINITE);
    animation10.setInterpolator(this, android.R.anim.overshoot_interpolator);
    mCube10.startAnimation(animation10);
  }
}