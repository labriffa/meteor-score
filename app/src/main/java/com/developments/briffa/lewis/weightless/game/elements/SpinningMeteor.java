package com.developments.briffa.lewis.weightless.game.elements;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

public class SpinningMeteor extends MeteorElement {

    private float dy;
    private float dx;
    private Paint mPaint;
    private float width;
    private int meteorType = 0;
    private Drawable image;
    private int health;

    public SpinningMeteor(float x, float y, int width, int height, Drawable drawable) {
        super(x, y, width, height, drawable);
        dx = 6f;
        dy = -8;
        mPaint = new Paint();
        mPaint.setColor(Color.YELLOW);
        image = drawable;
        health = width;
    }

    public void move(Canvas canvas) {

        if(!hasBeenHit()) {
            setY(getY() + dy);
            setX(getX() + dx);

            if(getX() <= 0) {
                dx = 6f;
            } else if(getX() >= canvas.getWidth() - getWidth()) {
                dx = -6f;
            }

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
