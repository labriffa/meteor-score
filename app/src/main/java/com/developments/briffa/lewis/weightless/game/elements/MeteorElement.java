package com.developments.briffa.lewis.weightless.game.elements;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

import com.developments.briffa.lewis.weightless.activities.HazardElement;

public class MeteorElement extends HazardElement {

    private float dy;
    private float dx;
    private Paint mPaint;
    private float width;
    private Drawable image;
    private int health;

    public MeteorElement(float x, float y, int width, int height, Drawable drawable) {
        super(x, y, width, height);
        this.width = width;
        dy = -8;
        mPaint = new Paint();
        mPaint.setColor(Color.YELLOW);
        this.image = drawable;
        health = width;
    }

    public void move(Canvas canvas) {

        if(!hasBeenHit()) {
            setY(getY() + dy);
            setX(getX() + dx);

            Paint paint = new Paint();
            paint.setColor(Color.RED);

            canvas.drawRect((int) (getX() + getHealth()), (int)(getY()+getHeight()+50), (int) (getX() + getWidth()), (int)(getY()+getHeight()+75), paint);
            canvas.drawRect((int)getX(), (int)(getY()+getHeight()+50), (int) (getX() + getHealth()), (int)(getY()+getHeight()+75), mPaint);
            image.setBounds((int) getX(), (int) getY(), (int) (getX() + getWidth()), (int) (getY() + getHeight()));
            image.draw(canvas);
        }
    }

    public int getHealth() {
        return health;
    }

    public void damage(int hitPoint) {
        health -= hitPoint;
    }

}
