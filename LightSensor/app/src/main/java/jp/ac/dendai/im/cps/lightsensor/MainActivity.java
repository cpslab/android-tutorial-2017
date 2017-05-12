package jp.ac.dendai.im.cps.lightsensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.List;

// TODO:3 SensorEventListenerインターフェースの継承
public class MainActivity extends AppCompatActivity implements SensorEventListener {

    // TODO:1 SensorManagerの宣言
    private SensorManager sensorManager;
    // TODO: 9 TextViewの宣言
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO:2 SensorManagerの取得
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        // TODO:10 TextViewの取得
        textView = (TextView) findViewById(R.id.text_view);
    }

    // TODO:4 onResumeの実装
    @Override
    protected void onResume() {
        super.onResume();

        // TODO:5 センサを指定してリストを取得
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_LIGHT);

        if (sensors.size() > 0) {
            // TODO:6 リスナーの登録
            // リスナの登録しないとアプリケーションにセンサ情報を通知できない
            // 第1引数にはリスナー、第2引数にはセンサの種類、第3引数には取得する頻度を指定する
            sensorManager.registerListener(this, sensors.get(0), SensorManager.SENSOR_DELAY_UI);
        }
    }

    // TODO:13 アプリがバックグラウンドのときはバッテリーの消費を抑えるためにリスナーを解除する
    @Override
    protected void onPause() {
        super.onPause();
        if (sensorManager != null) {
            // リスナーの登録解除
            sensorManager.unregisterListener(this);
        }
    }

    // TODO:7 onSensorChangedの実装
    // onSensorChangedはセンサの値が変わったら呼ばれる
    @Override
    public void onSensorChanged(final SensorEvent event) {
        // TODO:8 取得したいセンサイベントの取得
        // ここでは、照度センサSensor.TYPE_LIGHTを指定する
        if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            // TODO:11 センサ値の取得
            int value = (int) event.values[0];
            // TODO:12  TextViewへ表示
            textView.setText(String.valueOf(value));
        }
    }

    // onAccuracyChangedの実装
    // onAccuracyChangedはセンサの精度が変更されると呼ばれる
    // 今回は使わない
    @Override
    public void onAccuracyChanged(final Sensor sensor, final int accuracy) {
    }
}
