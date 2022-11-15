package me.yonghong.android.cube.activity.animation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import me.yonghong.android.cube.R;
import me.yonghong.android.cube.util.DisplayUtils;
import me.yonghong.android.cube.util.PxUtils;

public class PropertyActivity extends AppCompatActivity {

  private FrameLayout mCube1;
  private FrameLayout mCube2;
  private FrameLayout mCube3;
  private FrameLayout mCube4;
  private FrameLayout mCube5;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_animation_property);

    mCube1 = findViewById(R.id.cube_1);
    mCube2 = findViewById(R.id.cube_2);
    mCube3 = findViewById(R.id.cube_3);
    mCube4 = findViewById(R.id.cube_4);
    mCube5 = findViewById(R.id.cube_5);

    ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(mCube1, "alpha", 0f, 1f);
    alphaAnimation.setDuration(1000);
    alphaAnimation.setRepeatCount(ValueAnimator.INFINITE);
    alphaAnimation.setRepeatMode(ValueAnimator.REVERSE);
    alphaAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
    alphaAnimation.start();

    AnimatorSet scaleAnimatorSet = new AnimatorSet();
    ObjectAnimator scaleXAnimation = ObjectAnimator.ofFloat(mCube2, "scaleX", 0f, 1.5f);
    scaleXAnimation.setDuration(1000);
    scaleXAnimation.setRepeatCount(ValueAnimator.INFINITE);
    scaleXAnimation.setRepeatMode(ValueAnimator.REVERSE);
    scaleXAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
    ObjectAnimator scaleYAnimation = ObjectAnimator.ofFloat(mCube2, "scaleY", 0f, 1.5f);
    scaleYAnimation.setDuration(1000);
    scaleYAnimation.setRepeatCount(ValueAnimator.INFINITE);
    scaleYAnimation.setRepeatMode(ValueAnimator.REVERSE);
    scaleYAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
    scaleAnimatorSet.playTogether(scaleXAnimation, scaleYAnimation);
    scaleAnimatorSet.start();

    ObjectAnimator rotationAnimator = ObjectAnimator.ofFloat(mCube3, "rotation", 0f, 360f);
    rotationAnimator.setDuration(2000);
    rotationAnimator.setRepeatCount(ValueAnimator.INFINITE);
    rotationAnimator.setRepeatMode(ValueAnimator.REVERSE);
    rotationAnimator.start();

    ObjectAnimator translationXAnimator = ObjectAnimator.ofFloat(mCube4, "translationX",
        0f, DisplayUtils.getRealScreenWidth() - PxUtils.dpToPx(100));
    translationXAnimator.setDuration(2000);
    translationXAnimator.setRepeatCount(ValueAnimator.INFINITE);
    translationXAnimator.setRepeatMode(ValueAnimator.REVERSE);
    translationXAnimator.start();

    PropertyValuesHolder scaleXValuesHolder = PropertyValuesHolder.ofFloat("scaleX", 1.0f, 1.5f);
    PropertyValuesHolder scaleYValuesHolder = PropertyValuesHolder.ofFloat("scaleY", 1.0f, 1.5f);
    ObjectAnimator scaleXYAnimator = ObjectAnimator.ofPropertyValuesHolder(mCube5,
        scaleXValuesHolder, scaleYValuesHolder);
    scaleXYAnimator.setDuration(1000);
    scaleXYAnimator.setRepeatCount(ValueAnimator.INFINITE);
    scaleXYAnimator.setRepeatMode(ValueAnimator.REVERSE);
    scaleXYAnimator.start();
  }
}