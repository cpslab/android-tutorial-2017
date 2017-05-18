package jp.ac.dendai.im.cps.lightsensor

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView

// TODO:3 SensorEventListenerインターフェースの継承
class MainActivity : AppCompatActivity(), SensorEventListener {

    // TODO:1 SensorManagerの宣言
    private val sensorManager: SensorManager by lazy {
        // TODO:2 SensorManagerの取得
        getSystemService(Context.SENSOR_SERVICE) as SensorManager }

    // TODO: 9 TextViewの宣言とlayoutの変更
    // 以下のリンクを参考にTextViewを配置してください。
    // https://github.com/cpslab/android-tutorial-2017/blob/master/LightSensor/app/src/main/res/layout/activity_main.xml
    private val textView: TextView by lazy {
        // TODO:10 TextViewの取得
        findViewById(R.id.text_view) as TextView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // TODO:4 onResumeの実装
    override fun onResume() {
        super.onResume()

        // TODO:5 センサを指定してリストを取得
        val sensors = sensorManager.getSensorList(Sensor.TYPE_LIGHT)

        if (sensors.size > 0) {
            // TODO:6 リスナーの登録
            // リスナの登録しないとアプリケーションにセンサ情報を通知できない
            // 第1引数にはリスナー、第2引数にはセンサの種類、第3引数には取得する頻度を指定する
            sensorManager.registerListener(this, sensors[0], SensorManager.SENSOR_DELAY_UI)
        }
    }

    // TODO:13 アプリがバックグラウンドのときはバッテリーの消費を抑えるためにリスナーを解除する
    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    // TODO:7 onSensorChangedの実装
    // onSensorChangedはセンサの値が変わったら呼ばれる
    override fun onSensorChanged(event: SensorEvent) {
        // TODO:8 取得したいセンサイベントの取得
        // ここでは、照度センサSensor.TYPE_LIGHTを指定する
        if (event.sensor.type == Sensor.TYPE_LIGHT) {
            // TODO:11 センサ値の取得
            val value = event.values[0].toInt()
            // TODO:12  TextViewへ表示
            textView.text = value.toString()
        }
    }

    // onAccuracyChangedの実装
    // onAccuracyChangedはセンサの精度が変更されると呼ばれる
    // 今回は使わない
    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}
}
