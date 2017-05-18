package jp.ac.dendai.im.cps.accelerometersample

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(), SensorEventListener {

    private val sensorManager: SensorManager by lazy {
        getSystemService(Context.SENSOR_SERVICE) as SensorManager }

    private var sensorX: Float = 0.toFloat()

    private var sensorY: Float = 0.toFloat()

    private var sensorZ: Float = 0.toFloat()

    private val canvasView: CanvasView by lazy { CanvasView(this, null) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(canvasView)
    }

    override fun onResume() {
        super.onResume()

        val sensors = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER)
        if (sensors.size > 0) {
            sensorManager.registerListener(this, sensors[0], SensorManager.SENSOR_DELAY_UI)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type != Sensor.TYPE_ACCELEROMETER) {
            return
        }

        sensorX = event.values[0]
        sensorY = event.values[1]
        sensorZ = event.values[2];

        canvasView.setPosition(sensorX, sensorY)
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {

    }
}
