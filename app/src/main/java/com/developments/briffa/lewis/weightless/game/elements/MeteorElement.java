package com.developments.briffa.lewis.weightless.game.elements;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.developments.briffa.lewis.weightless.activities.HazardElement;
import com.developments.briffa.lewis.weightless.activities.ElementObservable;

import java.util.ArrayList;
import java.util.Random;

public class MeteorElement extends HazardElement {

    private float dy;
    private float dx;
    private Paint mPaint;
    private float width;
    private int meteorType = 0;

    public MeteorElement(float x, float y, int width, int height, int color) {
        super(x, y, width, height);
        this.width = width;
        dy = -8;
        mPaint = new Paint();
        mPaint.setColor(color);
        Random random = new Random();
    }

    public void move(Canvas canvas) {

        if(!hasBeenHit()) {
            setY(getY() + dy);
            setX(getX() + dx);

            canvas.drawRect(getX(),getY(),getX()+getWidth(),getY()+75,mPaint);
        }
    }

}
