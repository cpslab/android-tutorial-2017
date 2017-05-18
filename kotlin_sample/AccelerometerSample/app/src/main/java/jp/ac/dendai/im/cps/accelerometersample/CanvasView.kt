package jp.ac.dendai.im.cps.accelerometersample

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class CanvasView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val paint: Paint = Paint()
    private val bmp: Bitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)
    private var xpos = 200f
    private var ypos = 200f
    private var preX: Float = 0.toFloat()
    private var preY: Float = 0.toFloat()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawColor(Color.argb(125, 0, 0, 255))

        val width = canvas.width
        val height = canvas.height

        // 画面外に出ないように
        xpos = if (xpos < 0) {
            0f
        } else if (xpos + bmp.width > width) {
            (width - bmp.width).toFloat()
        } else {
            xpos
        }

        ypos = if (ypos < 0) {
            0f
        } else if (ypos + bmp.height > height) {
            (height - bmp.height).toFloat()
        } else {
            ypos
        }

        canvas.drawBitmap(bmp, xpos, ypos, paint)
    }

    fun setPosition(xp: Float, yp: Float) {
        val dT = 1.0f
        val ax = -xp / 5
        val ay = yp / 5
        val limit = 30

        xpos += preX * dT + ax * dT * dT / 2
        ypos += preY * dT + ay * dT * dT / 2

        preX += ax * dT
        preY += ay * dT

        preX = if (preX < -limit) {
            (-limit).toFloat()
        } else if (preX > limit) {
            limit.toFloat()
        } else {
            preX
        }

        preY = if (preY < -limit) {
            (-limit).toFloat()
        } else if (preY > limit) {
            limit.toFloat()
        } else {
            preY
        }

        invalidate()
    }
}
