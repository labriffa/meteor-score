package com.developments.briffa.lewis.weightless.game.elements;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

public class StarElement extends CanvasElement {

    private float x;
    private float y;
    private float dy;
    private Paint mPaint;
    private float width;
    private float height;
    private Drawable image;

    public StarElement(float x, float y, float width, float height, Drawable image) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = image;
        dy = -10;
        mPaint = new Paint();
    }

    public void move(Canvas canvas) {
        y += dy;
        image.setBounds((int)x,(int)y,(int)(x+width),(int)(y+height));
        image.draw(canvas);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }
}
