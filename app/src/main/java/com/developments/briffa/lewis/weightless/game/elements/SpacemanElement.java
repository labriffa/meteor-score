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
public class SpacemanElement extends CanvasElement {
    private float x;
    private float y;
    private float dx;
    private Drawable image;
    private Paint mPaint;

    private final static float HEIGHT = 300f;
    private final static float WIDTH = 300f;

    public SpacemanElement(float x, Drawable image)
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
        // check if this object is too far left of the screen
        if(x < 0 || x + WIDTH >= canvas.getWidth()) {
            x = x < 0 ? 0 : canvas.getWidth()-WIDTH;
        }

        x += dx;
        image.setBounds((int) x, (int) 100f, (int) (x + WIDTH), (int) HEIGHT);
        image.draw(canvas);
    }

    public float getX()
    {
        return this.x;
    }

    @Override
    public float getY() {
        return 0;
    }

    public float getWidth() { return WIDTH; }

    public float getHeight() { return HEIGHT; }

    public void setDx(float dx)
    {
        this.dx = dx;
    }
}
