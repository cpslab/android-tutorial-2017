package jp.ac.dendai.im.cps.toastlight

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

// 画像の表示については以下のサイトが参考になります
// https://akira-watson.com/android/imageview.html

class MainActivity : AppCompatActivity(), SensorEventListener {

    private val textView: TextView by lazy { findViewById(R.id.text_view) as TextView }
    private val imageView: ImageView by lazy { findViewById(R.id.image_view) as ImageView }
    private val sensorManager: SensorManager by lazy {
        getSystemService(Context.SENSOR_SERVICE) as SensorManager }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        val sensors = sensorManager.getSensorList(Sensor.TYPE_LIGHT)
        if (sensors.size > 0) {
            // リスナーを登録
            sensorManager.registerListener(this, sensors[0], SensorManager.SENSOR_DELAY_UI)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent) {
        // TYPE_LIGHTでない場合は何もしない
        if (event.sensor.type != Sensor.TYPE_LIGHT) {
            return
        }

        val value = event.values[0].toInt()
        // TextViewへ現在の明るさを表示します
        textView.text = "現在の明るさは$value"

        // 200より大きかったらToastで通知
        if (value > 200) {
            // Toastと呼ばれる機能です。
            // ダウンロード完了などの通知に便利です
            val toast = Toast.makeText(this, "うぉ！まぶしっ", Toast.LENGTH_SHORT)
            toast.show()
        }

        // 画像のスケールを変更します
        imageView.scaleX = (1 + value / 100).toFloat()
        imageView.scaleY = (1 + value / 100).toFloat()
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {

    }
}
