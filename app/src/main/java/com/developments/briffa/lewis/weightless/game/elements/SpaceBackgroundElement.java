package com.developments.briffa.lewis.weightless.game.elements;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

public class SpaceBackgroundElement {
    private Paint mPaintWhite;
    private Paint mPaintBlack;
    private boolean isStart;
    private Drawable image1;
    private Drawable image2;
    private float y;
    private float x;
    private float dy;

    public SpaceBackgroundElement(Drawable image, float x, float y) {
        mPaintBlack = new Paint();
        mPaintBlack.setColor(Color.parseColor("#252525"));
        isStart = true;
        this.x = x;
        this.y = y;
        this.dy = -10;

        this.image1 = image;
        this.image2 = image;


        mPaintWhite = new Paint();
        mPaintWhite.setColor(Color.WHITE);
    }

    public void draw(Canvas canvas)
    {
        if(isStart) {
            canvas.drawRect(0,0,canvas.getWidth(), canvas.getHeight()*2, mPaintBlack);
            for(int i = 0; i < 100; i++)
            {
                canvas.drawCircle(5*(float)(Math.random() * canvas.getWidth()), 5*(float)(Math.random() * canvas.getHeight()*2), (float)Math.random() * 2 + 2, mPaintWhite);
            }
        }
    }

    public void setStart(boolean isStart)
    {
        this.isStart = isStart;
    }

//    @Override
//    public float getX() {
//        return 0;
//    }
//
//    @Override
//    public float getY() {
//        return 0;
//    }
//
//    @Override
//    public float getWidth() {
//        return 0;
//    }
//
//    @Override
//    public float getHeight() {
//        return 0;
//    }
    public void setY(float y) {
        this.y = y;
    }
}
