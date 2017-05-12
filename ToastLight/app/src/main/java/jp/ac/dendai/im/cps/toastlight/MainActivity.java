package jp.ac.dendai.im.cps.toastlight;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

// 画像の表示については以下のサイトが参考になります
// https://akira-watson.com/android/imageview.html

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private TextView textView;
    private ImageView imageView;
    private SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.text_view);
        imageView = (ImageView) findViewById(R.id.image_view);

        //  SensorManagerの取得
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();

        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_LIGHT);
        if (sensors.size() > 0) {
            // リスナーを登録
            sensorManager.registerListener(this, sensors.get(0), SensorManager.SENSOR_DELAY_UI);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (sensorManager != null) {
            sensorManager.unregisterListener(this);
        }
    }

    @Override
    public void onSensorChanged(final SensorEvent event) {
        // TYPE_LIGHTでない場合は何もしない
        if (event.sensor.getType() != Sensor.TYPE_LIGHT) {
            return;
        }

        int value = (int) event.values[0];
        // TextViewへ現在の明るさを表示します
        textView.setText("現在の明るさは" + String.valueOf(value));

        // 200より大きかったらToastで通知
        if (value > 200) {
            // Toastと呼ばれる機能です。
            // ダウンロード完了などの通知に便利です
            Toast toast = Toast.makeText(this, "うぉ！まぶしっ", Toast.LENGTH_SHORT);
            toast.show();
        }

        // 画像のスケールを変更します
        imageView.setScaleX(1 + value / 100);
        imageView.setScaleY(1 + value / 100);
    }

    @Override
    public void onAccuracyChanged(final Sensor sensor, final int accuracy) {

    }
}
