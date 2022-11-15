package me.yonghong.android.cube.activity.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import me.yonghong.android.cube.R;

public class PropertyRotationActivity extends AppCompatActivity {

  private FrameLayout mCube1;
  private FrameLayout mCube2;
  private FrameLayout mCube3;
  private FrameLayout mCube4;
  private FrameLayout mCube5;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_animation_property_rotation);

    mCube1 = findViewById(R.id.cube_1);
    mCube2 = findViewById(R.id.cube_2);
    mCube3 = findViewById(R.id.cube_3);
    mCube4 = findViewById(R.id.cube_4);
    mCube5 = findViewById(R.id.cube_5);

    ObjectAnimator anim1 = ObjectAnimator.ofFloat(mCube1, "rotationX", 0f, 360f);
    anim1.setDuration(2000);
    anim1.setRepeatCount(ValueAnimator.INFINITE);
    anim1.setRepeatMode(ValueAnimator.REVERSE);
    anim1.start();

    ObjectAnimator anim2 = ObjectAnimator.ofFloat(mCube2, "rotationY", 0f, 360f);
    anim2.setDuration(2000);
    anim2.setRepeatCount(ValueAnimator.INFINITE);
    anim2.setRepeatMode(ValueAnimator.REVERSE);
    anim2.start();

    ObjectAnimator anim31 = ObjectAnimator.ofFloat(mCube3, "rotationX", 0f, 360f);
    anim31.setDuration(2000);
    anim31.setRepeatCount(ValueAnimator.INFINITE);
    anim31.setRepeatMode(ValueAnimator.REVERSE);

    ObjectAnimator anim32 = ObjectAnimator.ofFloat(mCube3, "rotationY", 0f, 360f);
    anim32.setDuration(2000);
    anim32.setRepeatCount(ValueAnimator.INFINITE);
    anim32.setRepeatMode(ValueAnimator.REVERSE);

    AnimatorSet anim3 = new AnimatorSet();
    anim3.playTogether(anim31, anim32);
    anim3.start();

    ObjectAnimator anim41 = ObjectAnimator.ofFloat(mCube4, "rotationX", 0f, 360f);
    anim41.setDuration(2000);

    ObjectAnimator anim42 = ObjectAnimator.ofFloat(mCube4, "rotationY", 0f, 360f);
    anim42.setDuration(2000);

    AnimatorSet anim4 = new AnimatorSet();
    anim4.playSequentially(anim41, anim42);
    anim4.addListener(new ReplayAnimatorListener());
    anim4.start();

    ObjectAnimator anim511 = ObjectAnimator.ofFloat(mCube5, "rotationX", 0f, 45f);
    anim511.setDuration(400);

    ObjectAnimator anim512 = ObjectAnimator.ofFloat(mCube5, "rotationX", 45f, -5f);
    anim512.setDuration(200);

    ObjectAnimator anim513 = ObjectAnimator.ofFloat(mCube5, "rotationX", -5f, 5f);
    anim513.setDuration(200);

    ObjectAnimator anim514 = ObjectAnimator.ofFloat(mCube5, "rotationX", 5f, 0f);
    anim514.setDuration(200);

    AnimatorSet anim51 = new AnimatorSet();
    anim51.playSequentially(anim511, anim512, anim513, anim514);
    anim51.setStartDelay(400);

    ObjectAnimator anim521 = ObjectAnimator.ofFloat(mCube5, "rotationY", 0f, -45f);
    anim521.setDuration(400);

    ObjectAnimator anim522 = ObjectAnimator.ofFloat(mCube5, "rotationY", -45f, 5f);
    anim522.setDuration(200);

    ObjectAnimator anim523 = ObjectAnimator.ofFloat(mCube5, "rotationY", 5f, -5f);
    anim523.setDuration(200);

    ObjectAnimator anim524 = ObjectAnimator.ofFloat(mCube5, "rotationY", -5f, 0f);
    anim524.setDuration(200);

    AnimatorSet anim52 = new AnimatorSet();
    anim52.playSequentially(anim521, anim522, anim523, anim524);
    anim52.setStartDelay(400);

    ObjectAnimator anim531 = ObjectAnimator.ofFloat(mCube5, "rotation", 0f, -45f);
    anim531.setDuration(400);

    ObjectAnimator anim532 = ObjectAnimator.ofFloat(mCube5, "rotation", -45f, 5f);
    anim532.setDuration(200);

    ObjectAnimator anim533 = ObjectAnimator.ofFloat(mCube5, "rotation", 5f, -5f);
    anim533.setDuration(200);

    ObjectAnimator anim534 = ObjectAnimator.ofFloat(mCube5, "rotation", -5f, 0f);
    anim534.setDuration(200);

    AnimatorSet anim53 = new AnimatorSet();
    anim53.playSequentially(anim531, anim532, anim533, anim534);
    anim53.setStartDelay(400);

    AnimatorSet anim5 = new AnimatorSet();
    anim5.playSequentially(anim51, anim52, anim53);
    anim5.addListener(new ReplayAnimatorListener());
    anim5.start();
  }

  private static class ReplayAnimatorListener extends AnimatorListenerAdapter {
    /**
     * 调用 {@link Animator#cancel()}
     * 先回调 {@link android.animation.Animator.AnimatorListener#onAnimationCancel(Animator)}
     * 再回调 {@link android.animation.Animator.AnimatorListener#onAnimationEnd(Animator)}
     * 所以这里需要记录是否继续 repeat
     */
    private boolean canRepeat = true;

    @Override
    public void onAnimationEnd(Animator animation) {
      if (canRepeat) {
        animation.start();
      }
    }

    @Override
    public void onAnimationCancel(Animator animation) {
      canRepeat = false;
    }
  }
}