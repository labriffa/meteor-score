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
            image.setBounds(0, (int) 100f, (int) 284f, (int) 568f);
            image.draw(canvas);
        } else if(x + 284f <= canvas.getWidth()) {
            image.setBounds((int) x, (int) 100f, (int) (x + 284f), (int) 568f);
//            canvas.drawRect(x, 0f, x+100f, 100f, mPaint);
            image.draw(canvas);
        } else {
            //canvas.drawRect(canvas.getWidth()-100f, 0f, canvas.getWidth(), 100f, mPaint);
            image.setBounds((int)(canvas.getWidth()-284f), (int)100f, canvas.getWidth(), (int)586f);
            image.draw(canvas);
        }
    }

    public float getX()
    {
        return this.x;
    }

    public void setDx(float dx)
    {
        this.dx = dx;
    }
}
