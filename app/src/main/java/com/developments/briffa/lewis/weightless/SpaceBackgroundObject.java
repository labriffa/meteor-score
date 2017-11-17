package com.developments.briffa.lewis.weightless;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

public class SpaceBackgroundObject {
    private Paint mPaintWhite;
    private Paint mPaintBlack;
    private boolean isStart;
    private Drawable image1;
    private Drawable image2;
    private float y;
    private float x;
    private float dy;

    public SpaceBackgroundObject(Drawable image, float x, float y) {
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
                canvas.drawCircle(5*(float)(Math.random() * canvas.getWidth()), 5*(float)(Math.random() * canvas.getHeight()*2), (float)Math.random() * 2 + 10, mPaintWhite);
            }
        }

//        y += dy;
//        image1.setBounds((int)x, 0, canvas.getWidth(), (int)(y));
//        image1.draw(canvas);
//        image2.setBounds((int)x, (int)y, canvas.getWidth(), (int)(y+canvas.getHeight()));
//        image2.draw(canvas);
    }

    public void setStart(boolean isStart)
    {
        this.isStart = isStart;
    }
}
