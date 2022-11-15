package me.yonghong.android.cube.activity.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import me.yonghong.android.cube.R;

public class ShakePhoneActivity extends AppCompatActivity {

  private FrameLayout mCube1;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_animation_shake_phone);
    mCube1 = findViewById(R.id.cube_1);

    ObjectAnimator anim1 = ObjectAnimator.ofFloat(mCube1, "rotation", 0f, -45f);
    anim1.setDuration(400);

    ObjectAnimator anim2 = ObjectAnimator.ofFloat(mCube1, "rotation", -45f, 5f);
    anim2.setDuration(200);

    ObjectAnimator anim3 = ObjectAnimator.ofFloat(mCube1, "rotation", 5f, -5f);
    anim3.setDuration(200);

    ObjectAnimator anim4 = ObjectAnimator.ofFloat(mCube1, "rotation", -5f, 0f);
    anim4.setDuration(200);

    AnimatorSet animatorSet = new AnimatorSet();
    animatorSet.playSequentially(anim1, anim2, anim3, anim4);
    animatorSet.addListener(new AnimatorListenerAdapter() {
      @Override
      public void onAnimationEnd(Animator animation) {
        animatorSet.start();
      }
    });
    animatorSet.start();
  }
}