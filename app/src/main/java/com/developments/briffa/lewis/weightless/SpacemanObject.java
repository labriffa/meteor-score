package com.developments.briffa.lewis.weightless;

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
public class SpacemanObject {
    private float x;
    private float dx;
    private Drawable image;
    private Paint mPaint;

    private final static float HEIGHT = 300f;
    private final static float WIDTH = 300f;

    public SpacemanObject(float x, Drawable image)
    {
        this.x = x;
        this.image = image;
        dx = 0;
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(100);
    }

    public void move(Canvas canvas)
    {
        x += dx;
        if(x < 0) {
            image.setBounds(0, (int) 100f, (int) WIDTH, (int) HEIGHT);
            image.draw(canvas);
        } else if(x + WIDTH <= canvas.getWidth()) {
            image.setBounds((int) x, (int) 100f, (int) (x + WIDTH), (int) HEIGHT);
//            canvas.drawRect(x, 0f, x+100f, 100f, mPaint);
            image.draw(canvas);
        } else {
            //canvas.drawRect(canvas.getWidth()-100f, 0f, canvas.getWidth(), 100f, mPaint);
            image.setBounds((int)(canvas.getWidth()-WIDTH), (int)100f, canvas.getWidth(), (int)HEIGHT);
            image.draw(canvas);
        }
    }

    public float getX()
    {
        return this.x;
    }

    public float getWidth() { return WIDTH; }

    public float getHeight() { return HEIGHT; }

    public void setDx(float dx)
    {
        this.dx = dx;
    }
}
