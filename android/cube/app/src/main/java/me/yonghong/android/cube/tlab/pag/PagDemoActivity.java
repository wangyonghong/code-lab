package me.yonghong.android.cube.tlab.pag;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import me.yonghong.android.cube.R;

public class PagDemoActivity extends AppCompatActivity implements SimpleListAdapter.ItemClickListener {

  private static final String[] items = new String[]{
      "A Simple PAG Animation",
      "Text Replacement",
      "Image Replacement",
      "Render Multiple PAG Files on A PAGView",
      "Create PAGSurface through texture ID",
      "Render an interval of the pag file"
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
    setContentView(R.layout.activity_pag_demo);
    initView();
  }

  private void initView() {
    RecyclerView rv = findViewById(R.id.rv_);
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
    rv.setLayoutManager(linearLayoutManager);
    rv.setAdapter(new SimpleListAdapter(items, this));
  }

  @Override
  public void onItemClick(int position) {
    switch (position) {
      case 0:
      case 1:
      case 2:
      case 3:
      case 5:
        goToAPIsDetail(position);
        break;
      case 4:
        goToTestDetail(position);
        break;

      default:
        break;
    }
  }

  private void goToAPIsDetail(int position) {
    Intent intent = new Intent(PagDemoActivity.this, APIsDetailActivity.class);
    intent.putExtra("API_TYPE", position);
    startActivity(intent);
  }

  private void goToTestDetail(int position) {
    Intent intent = new Intent(PagDemoActivity.this, TextureDemoActivity.class);
    intent.putExtra("API_TYPE", position);
    startActivity(intent);
  }
}
