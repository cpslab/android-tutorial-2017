package jp.ac.dendai.im.cps.lightsensortodo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

// TODO:3 SensorEventListenerインターフェースの継承
public class MainActivity extends AppCompatActivity {

    // TODO:1 SensorManagerの宣言

    // TODO: 9 TextViewの宣言とlayoutの変更
    // 以下のリンクを参考にTextViewを配置してください。
    // https://github.com/cpslab/android-tutorial-2017/blob/master/LightSensor/app/src/main/res/layout/activity_main.xml

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO:2 SensorManagerの取得

        // TODO:10 TextViewの取得
    }

    // TODO:4 onResumeの実装
    @Override
    protected void onResume() {
        super.onResume();

        // TODO:5 センサを指定してリストを取得

        // TODO:6 リスナーの登録
        // リスナの登録しないとアプリケーションにセンサ情報を通知できない
        // 第1引数にはリスナー、第2引数にはセンサの種類、第3引数には取得する頻度を指定する
    }

    // TODO:13 アプリがバックグラウンドのときはバッテリーの消費を抑えるためにリスナーを解除する
    @Override
    protected void onPause() {
        super.onPause();
    }

    // TODO:7 onSensorChangedの実装
    // onSensorChangedはセンサの値が変わったら呼ばれる
    // TODO:8 取得したいセンサイベントの取得
    // ここでは、照度センサSensor.TYPE_LIGHTを指定する
    // TODO:11 センサ値の取得
    // TODO:12  TextViewへ表示

    // onAccuracyChangedの実装
    // onAccuracyChangedはセンサの精度が変更されると呼ばれる
    // 今回は使わない
}
