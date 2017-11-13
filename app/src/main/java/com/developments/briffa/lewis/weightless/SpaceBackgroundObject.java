package com.developments.briffa.lewis.weightless;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

public class SpaceBackgroundObject {
    private Paint mPaintWhite;
    private Paint mPaintBlack;
    private boolean isStart;

    public SpaceBackgroundObject() {
        mPaintBlack = new Paint();
        mPaintBlack.setColor(Color.parseColor("#252525"));
        isStart = false;

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
        } else {

        }
    }

    public void setStart(boolean isStart)
    {
        this.isStart = isStart;
    }
}
