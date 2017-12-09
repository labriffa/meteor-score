package com.developments.briffa.lewis.weightless.game.elements;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.developments.briffa.lewis.weightless.R;

public class HudElement  {

    private Drawable pause;
    private Drawable star;

    public HudElement(Drawable pause, Drawable star) {
        this.pause = pause;
        this.star = star;
    }

    public void draw(Canvas canvas) {
        pause.setBounds(canvas.getWidth()-125, 25, canvas.getWidth()-25, 125);
        pause.draw(canvas);
        star.setBounds(canvas.getWidth()-300, 25, canvas.getWidth()-200, 125);
        star.draw(canvas);
    }
}

