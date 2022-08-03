package me.yonghong.android.cube;

import androidx.appcompat.app.AppCompatActivity;

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

public class MainActivity extends AppCompatActivity {

  private final List<Pair<String, Class<?>>> mExperiments = new ArrayList<>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    registerToLab();
    setContentView(createContentView());
  }

  private void registerToLab() {
    mExperiments.add(new Pair<>("全屏Dialog", FSDialogActivity.class));
  }

  private View createContentView() {
    LinearLayout linearLayout = new LinearLayout(this);
    linearLayout.setOrientation(LinearLayout.VERTICAL);
    for (Pair<String, Class<?>> experiment : mExperiments) {
      Button button = new Button(getBaseContext());
      button.setText(experiment.first);
      button.setOnClickListener(v -> startActivity(new Intent(getBaseContext(), experiment.second)));
      linearLayout.addView(button);
    }

    ScrollView scrollView = new ScrollView(this);
    scrollView.addView(linearLayout,
        new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
    return scrollView;
  }
}