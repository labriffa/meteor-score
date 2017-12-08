package com.developments.briffa.lewis.weightless.game.elements;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class MeteorElement extends CanvasElement {

    private float x;
    private float y;
    private float dy;
    private Paint mPaint;
    private float width;

    public MeteorElement(float x, float y, int width, int color) {
        this.x = x;
        this.y = y;
        this.width = width;
        dy = -((int)(Math.random()*1+20));
        mPaint = new Paint();
        mPaint.setColor(color);
    }

    public void move(Canvas canvas) {
        y += dy;
        canvas.drawRect(x,y,x+width,y+75,mPaint);
    }

    public float getY() {
        return y;
    }

    public float getX() {
        return x;
    }

    public float getWidth() {
        return width;
    }

    @Override
    public float getHeight() {
        return 0;
    }

    public float getDy() {
        return dy;
    }
}
