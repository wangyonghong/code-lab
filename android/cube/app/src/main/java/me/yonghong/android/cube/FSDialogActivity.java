package me.yonghong.android.cube;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatCheckBox;

public class FSDialogActivity extends AppCompatActivity {

  private AppCompatCheckBox mStatusBarCheckBox;
  private AppCompatCheckBox mNavBarCheckBox;
  private AppCompatButton mDialogBtn;
  private Dialog mDialog;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_fsdialog);
    mStatusBarCheckBox = findViewById(R.id.status_bar_cb);
    mNavBarCheckBox = findViewById(R.id.nav_bar_cb);
    mDialogBtn = findViewById(R.id.dialog_btn);
    mDialogBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mDialog = new Dialog(FSDialogActivity.this);
        mDialog.setCanceledOnTouchOutside(false);
        if (mDialog.requestWindowFeature(Window.FEATURE_OPTIONS_PANEL)) {
          // Window.FEATURE_OPTIONS_PANEL官方文档上默认是enable的，不需要request
          // 但有些系统如果不request会抛出The feature has not been requested异常
          mDialog.setFeatureDrawableAlpha(Window.FEATURE_OPTIONS_PANEL, 0);
        }
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = mDialog.getWindow();
        if (window == null) {
          return;
        }
        window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        int width = ViewGroup.LayoutParams.WRAP_CONTENT;
        int height = ViewGroup.LayoutParams.WRAP_CONTENT;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(width, height);
        layoutParams.gravity = Gravity.CENTER;
        mDialog.setContentView(dialogView(), layoutParams);
        mDialog.getWindow().getAttributes().width = width;
        mDialog.getWindow().getAttributes().height = height;
        mDialog.getWindow().getAttributes().gravity = Gravity.CENTER;
        mDialog.show();

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT);
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        View decorView = window.getDecorView();
        if (decorView != null) {
          int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
              | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
          decorView.setSystemUiVisibility(uiOptions);
        }
      }
    });
  }

  private View dialogView() {
    FrameLayout frameLayout = new FrameLayout(getBaseContext());
    frameLayout.setBackgroundColor(Color.BLACK);
    return frameLayout;
  }
}