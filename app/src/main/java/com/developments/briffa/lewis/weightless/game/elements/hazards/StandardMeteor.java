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

import java.util.Random;

public class StandardMeteor extends HazardElement {

    private float dy;
    private Drawable image;

    private static final int STANDARD_METEOR_IMAGE = R.drawable.meteor;
    private static final int STANDARD_METEOR_SIZE_DIVIDER = 3;
    private static final int DELTA_Y = -8;

    public StandardMeteor(Context context, Canvas canvas) {
        super((int) (Math.random() * canvas.getWidth()),
                canvas.getHeight(),
                (int) (Math.random() * canvas.getWidth() / STANDARD_METEOR_SIZE_DIVIDER)
        );

        dy = DELTA_Y;
        image = ContextCompat.getDrawable(context, STANDARD_METEOR_IMAGE);
    }

    public void move(Canvas canvas) {

        if(!hasBeenHit()) {
            setY(getY() + dy);

            drawLifeBar(canvas);
            image.setBounds((int) getX(), (int) getY(), (int) (getX() + getWidth()), (int) (getY() + getHeight()));
            image.draw(canvas);
        }
    }

    @Override
    public boolean hasPassed() {
        return getY() <= -getWidth();
    }

    @Override
    public void damage(int hitPoint) {
        setHealth(getHealth() - hitPoint);
    }

    @Override
    public String getElementName() {
        return "standard-meteor";
    }
}
