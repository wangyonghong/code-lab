package me.yonghong.android.cube.tlab.tquic;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.tencent.qqlive.modules.vb.tquic.impl.TnetConfig;
import com.tencent.qqlive.modules.vb.tquic.impl.TnetQuicRequest;
import com.tencent.qqlive.modules.vb.tquic.impl.TnetStats;

import java.io.ByteArrayOutputStream;

import androidx.appcompat.app.AppCompatActivity;
import me.yonghong.android.cube.R;

public class ResultActivity extends AppCompatActivity {
  private static final String TAG = "ResultActivity";

  private TnetQuicRequest mTnetQuicRequest;

  private ByteArrayOutputStream m_body = new ByteArrayOutputStream();
  private TnetStats stats = new TnetStats();

  class SimpleCallback extends TnetQuicRequest.Callback {
    SimpleCallback(int type) {

    }

    @Override
    public void onConnect(int error_code) {
      TextView reqView = (TextView) findViewById(R.id.req_status);
      Log.i(TAG, "****** onConnected with error_code: " + error_code);
      if (error_code != 0) {
        Log.i(TAG, "****** connect failed");
        reqView.setText("connect failed");
        return;
      }
      reqView.setText("connect success");
      mTnetQuicRequest.sendRequest(new byte[]{}, 0, true);
    }

    @Override
    public void onHeaderRecv(String header) {
      Log.i(TAG, "****** Header Completed ******:" + header);
    }

    @Override
    public void onDataRecv(byte[] body) {
      if (body == null || body.length <= 0) {
        return;
      }
      String sbody = new String(body);
      if (sbody.contains("server") || sbody.contains("status")) {
        onHeaderRecv(sbody);
        return;
      }
      try {
        m_body.write(body);
      } catch (Exception e) {

      }
    }

    @Override
    public void onComplete(int stream_error) {
      Log.i(TAG, "****** onComplete ******");
      BytesToImage(m_body.toByteArray());
      mTnetQuicRequest.GetTnetStates(stats);
      setStats(stats);
      TextView reqView = (TextView) findViewById(R.id.req_status);
      if (stream_error == 0) {
        reqView.setText("request success");
      }
      reqView.setText("request finish with error_code:" + stream_error);
    }

    @Override
    public void onClose(int error_code, String error_str) {
      TextView reqView = (TextView) findViewById(R.id.req_status);
      reqView.setText("closed with error_code:" + error_str);
    }
  }

  public void BytesToImage(byte[] bytes) {
    //Drawable d = Drawable.createFromStream(new ByteArrayInputStream(bytes), null);
    final Bitmap bitImage = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    ImageView image = (ImageView) findViewById(R.id.quic_image);
    Log.i(TAG, "before setimage");
    ResultActivity.this.runOnUiThread(new Runnable() {
      @Override
      public void run() {
        image.setImageBitmap(bitImage);
        //image.getLayoutParams().height = bitImage.getHeight();
        //image.getLayoutParams().width = bitImage.getWidth();
      }
    });
    Log.i(TAG, "bytes:" + bytes.length);
  }

  public void setStats(TnetStats stats) {
    TextView connectView = (TextView) findViewById(R.id.connect_ms);
    connectView.setText("connect_ms : " + String.valueOf(stats.mConnectMillis));
    TextView ttfbView = (TextView) findViewById(R.id.ttfb_ms);
    ttfbView.setText("ttfb_ms : " + String.valueOf(stats.mTTfbMillis));
    TextView totalView = (TextView) findViewById(R.id.total_ms);
    totalView.setText("total_ms : " + String.valueOf(stats.mCompleteMillis));
  }

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.tquic_result);

    final Intent intent = getIntent();

    String url = intent.getStringExtra("url");
    String ip = intent.getStringExtra("ip");

    System.loadLibrary("tquic_jni");

    TnetConfig jTnetConfig = new TnetConfig.Builder()
        .setCongestionType(2)
        .setConnectTimeoutMillis(4 * 1000)
        .setIdleTimeoutMillis(90 * 1000)
        .setTotalTimeoutMillis(100 * 1000)
        .setQuicVersion(43)
        .build();

    SimpleCallback callback_quic = new SimpleCallback(0);
    mTnetQuicRequest = new TnetQuicRequest(callback_quic, jTnetConfig, 0);
    startWithURL(url, ip);
  }

  private void startWithURL(String url, String ip_address) {
    mTnetQuicRequest.connect(url, ip_address);
  }
}
