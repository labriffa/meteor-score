package com.developments.briffa.lewis.weightless.game.elements;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

public class SpaceBackgroundElement {
    private Paint mPaintStars;
    private Paint mPaintBackground;
    private float y;
    private float x;

    public SpaceBackgroundElement(Drawable image, float x, float y) {
        mPaintBackground = new Paint();
        mPaintBackground.setColor(Color.parseColor("#252525"));

        this.x = x;
        this.y = y;

        mPaintStars = new Paint();
        mPaintStars.setColor(Color.WHITE);
    }

    public void draw(Canvas canvas)
    {
        // create background
        canvas.drawRect(x,y,canvas.getWidth(), canvas.getHeight()*2, mPaintBackground);

        // create stars
        for(int i = 0; i < 100; i++) {
            canvas.drawCircle(
                    5*(float)(Math.random() * canvas.getWidth()),
                    5*(float)(Math.random() * canvas.getHeight()*2),
                    (float)Math.random() * 2 + 2,
                    mPaintStars);
        }
    }
}
