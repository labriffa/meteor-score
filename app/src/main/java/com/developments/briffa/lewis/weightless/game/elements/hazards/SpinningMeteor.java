package com.developments.briffa.lewis.weightless.game.elements.hazards;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.developments.briffa.lewis.weightless.R;
import com.developments.briffa.lewis.weightless.factories.GameElementFactory;
import com.developments.briffa.lewis.weightless.game.elements.CanvasElement;
import com.developments.briffa.lewis.weightless.game.elements.hazards.HazardElement;

public class SpinningMeteor extends HazardElement {

    private float dy;
    private float dx;
    private Drawable image;

    private static final int SPINNING_METEOR_IMAGE = R.drawable.spinning_meteor;
    private static final int SPINNING_METEOR_SIZE_DIVIDER = 3;
    private static final int SPINNING_METEOR_OFFSCREEN_MULTIPLIER = 4;
    private static final int DELTA_X = 6;
    private static final int DELTA_Y = -8;

    public SpinningMeteor(Context context, Canvas canvas) {
        super((int) (Math.random() * canvas.getWidth()),
                canvas.getHeight() * SPINNING_METEOR_OFFSCREEN_MULTIPLIER,
                (int) (Math.random() * canvas.getWidth() / SPINNING_METEOR_SIZE_DIVIDER)
        );

        dx = DELTA_X;
        dy = DELTA_Y;
        image = ContextCompat.getDrawable(context, SPINNING_METEOR_IMAGE);
    }

    public void move(Canvas canvas) {

        if(!hasBeenHit()) {
            setY(getY() + dy);
            setX(getX() + dx);

            if(getX() <= 0) {
                dx = DELTA_X;
            } else if(getX() >= canvas.getWidth() - getWidth()) {
                dx = -DELTA_X;
            }

            drawLifeBar(canvas);
            image.setBounds((int) getX(), (int) getY(), (int) (getX() + getWidth()), (int) (getY() + getHeight()));
            image.draw(canvas);
        }
    }

    public boolean hasPassed() {
        return getY() <= -getWidth();
    }

    public void damage(int hitPoint) {
        setHealth(getHealth() - hitPoint);
    }

    @Override
    public String getElementName() {
        return "spinning-meteor";
    }
}
