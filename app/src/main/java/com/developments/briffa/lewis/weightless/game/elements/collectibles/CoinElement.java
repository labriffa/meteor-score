package com.developments.briffa.lewis.weightless.game.elements.collectibles;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

import com.developments.briffa.lewis.weightless.factories.GameElementFactory;
import com.developments.briffa.lewis.weightless.game.elements.CanvasElement;
import com.developments.briffa.lewis.weightless.interfaces.Movable;
import com.developments.briffa.lewis.weightless.interfaces.Recreatable;

public class CoinElement extends CanvasElement implements Movable, Recreatable {

    private float dy;
    private Drawable image;

    public CoinElement(float x, float y, int width, int height, Drawable image) {
        super(x,y, width, height);
        this.image = image;
        dy = -10;
    }

    /**
     * Draws and updates the movement of the coin
     *
     * @param canvas
     */
    public void move(Canvas canvas) {
        // move coin downwards
        setY(getY() + dy);

        // draw coin
        image.setBounds((int)getX(),(int)getY(),(int)(getX()+getWidth()),(int)(getY()+getHeight()));
        image.draw(canvas);
    }

    /**
     * Checks whether or not the coin has left the screen
     *
     * @return boolean
     */
    @Override
    public boolean hasPassed() {
        return getY() <= -getWidth();
    }

    /**
     * Recreates a new version of itself via the passed game element factory
     *
     * @param gameElementFactory
     * @return CanvasElement
     */
    @Override
    public CanvasElement recreate(GameElementFactory gameElementFactory) {
        return gameElementFactory.getInstance("coin");
    }
}
