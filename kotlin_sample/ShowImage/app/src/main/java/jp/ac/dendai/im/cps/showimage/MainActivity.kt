package jp.ac.dendai.im.cps.showimage

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

// 画像の表示については以下のサイトが参考になります
// https://akira-watson.com/android/imageview.html
// 今回は以下の手順で行いました
// 1) res/drawableディレクトリの中にface.pngを配置
// 2) activity_main.xmlにImageViewを配置
// 3) ImageViewにandroid:src="@drawable/face"を指定

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
