package com.developments.briffa.lewis.weightless.game.elements;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

public class MeteorElement extends CanvasElement {

    private float dy;
    private float dx;
    private Paint mPaint;
    private float width;
    private int meteorType = 0;

    public MeteorElement(float x, float y, int width, int height, int color) {
        super(x, y, width, height);
        this.width = width;
        dy = -15;

        mPaint = new Paint();
        mPaint.setColor(color);
        Random random = new Random();
        meteorType = random.nextInt(3);
        if(meteorType == 0) {
            dx = 0;
        } else if(meteorType == 1) {
            dx = -2;
        } else if(meteorType == 2) {
            dx = 2;
        } else {
            dx = 0;
        }
    }

    public void move(Canvas canvas) {
        setY(getY() + dy);
        setX(getX() + dx);
        canvas.drawRect(getX(),getY(),getX()+getWidth(),getY()+75,mPaint);
    }
}
