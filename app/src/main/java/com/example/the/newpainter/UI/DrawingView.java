package com.example.the.newpainter.UI;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DrawingView extends View {

    private Canvas myCanvas;
    private Bitmap canvasBitmap;
    private Path myPath;
    private Paint myPaint;
    private Paint canvasPaint;


    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupDrawing();
    }


    public void setupDrawing() {
        myPath = new Path();
        myPaint = new Paint();

        myPaint.setColor(Color.BLACK);
        myPaint.setStrokeWidth(3);
        myPaint.setStyle(Paint.Style.STROKE);
        myPaint.setStrokeJoin(Paint.Join.ROUND);
        myPaint.setStrokeCap(Paint.Cap.ROUND);
        canvasPaint = new Paint(Paint.DITHER_FLAG);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        myCanvas = new Canvas(canvasBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
        canvas.drawPath(myPath, myPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        float x = e.getX();
        float y = e.getY();

        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                myPath.moveTo(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                myPath.lineTo(x, y);
                break;
            case MotionEvent.ACTION_UP:
                myCanvas.drawPath(myPath, myPaint);
                myPath.reset();
                break;
            default:
                return false;
        }
        invalidate();
        return true;
    }
}
