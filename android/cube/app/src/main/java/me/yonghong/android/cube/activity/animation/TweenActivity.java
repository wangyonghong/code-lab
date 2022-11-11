package me.yonghong.android.cube.activity.animation;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import me.yonghong.android.cube.R;
import me.yonghong.android.cube.util.DisplayUtils;
import me.yonghong.android.cube.util.PxUtils;

/**
 * 补间动画
 */
public class TweenActivity extends AppCompatActivity {

  private FrameLayout mCube1;
  private FrameLayout mCube2;
  private FrameLayout mCube3;
  private FrameLayout mCube4;
  private FrameLayout mCube5;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_animation_tween);
    mCube1 = findViewById(R.id.cube_1);
    mCube2 = findViewById(R.id.cube_2);
    mCube3 = findViewById(R.id.cube_3);
    mCube4 = findViewById(R.id.cube_4);
    mCube5 = findViewById(R.id.cube_5);

    AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.2f);
    alphaAnimation.setDuration(1000);
    alphaAnimation.setRepeatMode(Animation.REVERSE);
    alphaAnimation.setRepeatCount(ValueAnimator.INFINITE);
    mCube1.startAnimation(alphaAnimation);

    ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f, 1.5f, 0.0f, 1.5f,
        Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
    scaleAnimation.setDuration(1000);
    scaleAnimation.setRepeatMode(Animation.REVERSE);
    scaleAnimation.setRepeatCount(ValueAnimator.INFINITE);
    mCube2.startAnimation(scaleAnimation);

    RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
        Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
    rotateAnimation.setDuration(3000);
    rotateAnimation.setRepeatMode(Animation.REVERSE);
    rotateAnimation.setRepeatCount(ValueAnimator.INFINITE);
    rotateAnimation.setInterpolator(this, android.R.anim.accelerate_decelerate_interpolator);
    mCube3.startAnimation(rotateAnimation);

    TranslateAnimation translateAnimation = new TranslateAnimation(0,
        DisplayUtils.getRealScreenWidth() - PxUtils.dpToPx(100), 0, 0);
    translateAnimation.setDuration(1000);
    translateAnimation.setRepeatMode(Animation.REVERSE);
    translateAnimation.setRepeatCount(ValueAnimator.INFINITE);
    translateAnimation.setInterpolator(this, android.R.anim.cycle_interpolator);
    mCube4.startAnimation(translateAnimation);

    AnimationSet animationSet = new AnimationSet(true);
    animationSet.addAnimation(alphaAnimation);
    animationSet.addAnimation(scaleAnimation);
    animationSet.addAnimation(rotateAnimation);
    animationSet.addAnimation(translateAnimation);
    mCube5.startAnimation(animationSet);
  }
}