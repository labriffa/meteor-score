package com.developments.briffa.lewis.weightless.game.elements.hazards;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

import com.developments.briffa.lewis.weightless.factories.GameElementFactory;
import com.developments.briffa.lewis.weightless.game.elements.CanvasElement;
import com.developments.briffa.lewis.weightless.game.elements.hazards.HazardElement;

public class SpinningMeteor extends HazardElement {

    private float dy;
    private float dx;
    private Paint mPaint;
    private Drawable image;
    private int health;

    public SpinningMeteor(float x, float y, int width, int height, Drawable drawable) {
        super(x, y, width, height);
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

    public boolean hasPassed() {
        return getY() <= -getWidth();
    }

    public int getHealth() {
        return health;
    }

    @Override
    public CanvasElement recreate(GameElementFactory gameElementFactory) {
        return gameElementFactory.getInstance("spinning-meteor");
    }

    public void damage(int hitPoint) {
        health -= hitPoint;
    }
}
