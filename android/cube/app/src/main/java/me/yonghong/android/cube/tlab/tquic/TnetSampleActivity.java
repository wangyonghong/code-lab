package me.yonghong.android.cube.tlab.tquic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import me.yonghong.android.cube.R;

public class TnetSampleActivity extends AppCompatActivity {

  private static final String TAG = TnetSampleActivity.class.getSimpleName();

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_tquic);

    final EditText urlInput = (EditText) findViewById(R.id.urlText);
    //urlInput.setText("https://iacc.stgw.qq.com");
    urlInput.setText("https://175.24.219.170/1.png");
    final EditText ipInput = (EditText) findViewById(R.id.postText);
    //ipInput.setText("121.51.90.180");
    ipInput.setText("175.24.219.170");
    Button LoadButton = (Button) findViewById(R.id.load_button);

    LoadButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String url = urlInput.getText().toString();
        String ip = ipInput.getText().toString();
        Intent resultIntent = new Intent(TnetSampleActivity.this, ResultActivity.class);
        resultIntent.putExtra("url", url);
        resultIntent.putExtra("ip", ip);
        startActivity(resultIntent);
      }
    });
  }
}