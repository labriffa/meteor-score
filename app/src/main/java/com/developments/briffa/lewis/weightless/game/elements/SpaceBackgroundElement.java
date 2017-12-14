package com.developments.briffa.lewis.weightless.game.elements;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

/**
 * Class representing a space background, creates stars on the fly.
 */
public class SpaceBackgroundElement extends CanvasElement {
    private Paint mPaintStars;
    private Paint mPaintBackground;

    private static final int SPACE_STARTING_POS_X = 0;
    private static final int SPACE_STARTING_POS_Y = 0;

    public SpaceBackgroundElement(Context context, Canvas canvas, boolean isSpace) {
        super(SPACE_STARTING_POS_X, SPACE_STARTING_POS_Y, canvas.getWidth(), canvas.getHeight());

        mPaintBackground = new Paint();

        if(isSpace) {
            mPaintBackground.setColor(Color.parseColor("#252525"));
        } else {
            mPaintBackground.setColor(Color.parseColor("#87CEEB"));
        }

        mPaintStars = new Paint();
        mPaintStars.setColor(Color.WHITE);
    }

    /**
     * Responsible for redrawing the background
     *
     * @param canvas
     */
    public void draw(Canvas canvas)
    {
        // create background
        canvas.drawRect(getX(),getY(), getWidth(), getHeight(), mPaintBackground);

        // create stars
        for(int i = 0; i < 100; i++) {
            canvas.drawCircle(
                    5*(float)(Math.random() * canvas.getWidth()),
                    5*(float)(Math.random() * canvas.getHeight()*2),
                    (float)Math.random() * 2 + 2,
                    mPaintStars);
        }
    }

    @Override
    public String getElementName() {
        return "space-background";
    }
}
