package me.yonghong.android.cube;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;

import android.os.Bundle;

public class FSDialogActivity extends AppCompatActivity {

  private AppCompatCheckBox mStatusCheckBox;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_fsdialog);
  }
}