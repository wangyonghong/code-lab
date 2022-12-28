package me.yonghong.android.cube.activity.threed;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import me.yonghong.android.cube.R;

public class ThreeDActivity extends AppCompatActivity {

  private FrameLayout mCube1;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_animation_shake_phone);
    mCube1 = findViewById(R.id.cube_1);
    mCube1.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

    // setCameraDistance 不生效很奇怪
    // mCube1.setCameraDistance(10);
    mCube1.setRotation(45);
    mCube1.setRotationX(25);
    mCube1.setRotationY(35);

    mCube1.postDelayed(() -> mCube1.animate()
        .rotation(0)
        .rotationX(0)
        .rotationY(0)
        .start(), 3000);
  }
}