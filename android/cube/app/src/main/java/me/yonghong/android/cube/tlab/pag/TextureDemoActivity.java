package me.yonghong.android.cube.tlab.pag;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

import me.yonghong.android.cube.R;
import me.yonghong.android.cube.tlab.pag.openGL.GLRender;

public class TextureDemoActivity extends Activity {

  private GLSurfaceView glSurfaceView;
  private GLRender glRender;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_texture_demo);
    glSurfaceView = (GLSurfaceView) findViewById(R.id.surfaceView);
    glRender = new GLRender(this.getBaseContext());
    glSurfaceView.setEGLContextClientVersion(2);
    glSurfaceView.setRenderer(glRender);
    glSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);

  }

  @Override
  protected void onResume() {
    super.onResume();
    glSurfaceView.onResume();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    glSurfaceView.onPause();

  }

}