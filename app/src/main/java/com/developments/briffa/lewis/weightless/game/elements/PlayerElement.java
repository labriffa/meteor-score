package com.developments.briffa.lewis.weightless.game.elements;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.Log;

/**
 * Represents the main game player object
 *
 * @author lewisbriffa
 * created on 11/11/2017
 */
public class PlayerElement extends CanvasElement {

    private float dx;
    private float dy;
    private Drawable image;
    private Paint mPaint;

    public PlayerElement(float x, float y, int width, int height, Drawable image)
    {
        super(x, y, width, height);
        this.image = image;
        dx = 0;
        dy = 0;
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(100);
    }

    public void move(Canvas canvas)
    {
        // check if this object is too far left of the screen
        if(getX() < 0 || getX() + getWidth() >= canvas.getWidth()) {
            setX(getX() < 0 ? 0 : canvas.getWidth()-getWidth());
        }

        setX(getX()+dx);
        setY(getY()+dy);
        image.setBounds((int) getX(), (int) getY(), (int) (getX() + getWidth()), (int) (getY() + getHeight()));
        image.draw(canvas);
    }

//    @Override
//    public float getY() {
//        return 0;
//    }

    public void setDx(float dx)
    {
        this.dx = dx;
    }
}
