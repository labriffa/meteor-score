package com.developments.briffa.lewis.weightless.game.elements;

import android.graphics.Canvas;
import android.graphics.Paint;

public class PauseElement extends CanvasElement {
    private Paint mPaint;

    public PauseElement(float x, float y, int width, int height, int color) {
        super(x, y, width, height);
        mPaint = new Paint();
        mPaint.setColor(color);
    }

    public void draw(Canvas canvas) {
        canvas.drawRect(canvas.getWidth()-200, 100, canvas.getWidth()-100, 200, mPaint);
    }
}
