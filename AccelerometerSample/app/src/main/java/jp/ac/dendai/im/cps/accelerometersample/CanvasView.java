package jp.ac.dendai.im.cps.accelerometersample;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


public class CanvasView extends View {
    private final Paint paint;
    private final Bitmap bmp;
    private final int bmpWidth;
    private final int bmpHeight;
    private float xpos = 200;
    private float ypos = 200;
    private float preX;
    private float preY;

    public CanvasView(final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);

        paint = new Paint();
        bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        bmpWidth = bmp.getWidth();
        bmpHeight = bmp.getHeight();
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.argb(125, 0, 0, 255));

        int width = canvas.getWidth();
        int height = canvas.getHeight();

        // 画面外に出ないように
        if (xpos < 0) {
            xpos = 0;
        } else if (xpos + bmpWidth > width) {
            xpos = width - bmpWidth;
        }

        if (ypos < 0) {
            ypos = 0;
        } else if (ypos + bmpHeight > height) {
            ypos = height - bmpHeight;
        }

        canvas.drawBitmap(bmp, xpos, ypos, paint);
    }

    public void setPosition(float xp, float yp) {
        float dT = 1.0f;
        final float ax = -xp / 5;
        final float ay = yp / 5;
        int limit = 30;

        xpos += (preX * dT) + ((ax * dT * dT) / 2);
        ypos += (preY * dT) + ((ay * dT * dT) / 2);

        preX += ax * dT;
        preY += ay * dT;

        if (preX < -limit) {
            preX = -limit;
        } else if (preX > limit) {
            preX = limit;
        }

        if (preY < -limit) {
            preY = -limit;
        } else if (preY > limit) {
            preY = limit;
        }

        invalidate();
    }
}
