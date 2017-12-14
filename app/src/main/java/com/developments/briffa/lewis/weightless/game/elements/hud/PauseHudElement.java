package com.developments.briffa.lewis.weightless.game.elements.hud;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

import com.developments.briffa.lewis.weightless.game.elements.CanvasElement;

public class PauseHudElement extends CanvasElement {
    private Drawable image;

    public PauseHudElement(float x, float y, int width, int height, Drawable image) {
        super(x, y, width, height);
        this.image = image;
    }

    public void draw(Canvas canvas) {
        image.setBounds((int)getX(), (int)getY(), (int)(getX() + getWidth()), (int)(getY() + getHeight()));
        image.draw(canvas);
    }

    @Override
    public String getElementName() {
        return "pause";
    }
}
