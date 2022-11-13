package me.yonghong.android.cube.activity.animation;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import me.yonghong.android.cube.R;

public class FrameActivity extends AppCompatActivity {

  private ImageView mLoading1, mLoading2;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_animation_frame);
    mLoading1 = findViewById(R.id.loading_anim_1);
    mLoading2 = findViewById(R.id.loading_anim_2);

    AnimationDrawable anim1 = (AnimationDrawable) mLoading1.getDrawable();
    anim1.start();

    AnimationDrawable anim2 = new AnimationDrawable();
    for (int i = 1; i <= 6; i++) {
      int id = getResources().getIdentifier(
          String.format(Locale.getDefault(), "loading%02d", i), "drawable", getPackageName());
      Drawable drawable = getResources().getDrawable(id);
      anim2.addFrame(drawable, 200);
    }
    anim2.setOneShot(false);
    mLoading2.setImageDrawable(anim2);
    anim2.start();
  }
}