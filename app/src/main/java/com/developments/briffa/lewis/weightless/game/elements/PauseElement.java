package com.developments.briffa.lewis.weightless.game.elements;

import android.graphics.Canvas;
import android.graphics.Paint;

public class PauseElement extends CanvasElement {
    private Paint mPaint;
    private float x;
    private float y;
    private float width;
    private float height;

    public PauseElement(float x, float y, float width, float height, int color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        mPaint = new Paint();
        mPaint.setColor(color);
    }

    public void draw(Canvas canvas) {
        canvas.drawRect(canvas.getWidth()-200, 100, canvas.getWidth()-100, 200, mPaint);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    @Override
    public float getWidth() {
        return 0;
    }

    @Override
    public float getHeight() {
        return 0;
    }
}
