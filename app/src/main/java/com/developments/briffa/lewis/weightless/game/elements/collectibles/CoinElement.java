package com.developments.briffa.lewis.weightless.game.elements.collectibles;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.developments.briffa.lewis.weightless.R;
import com.developments.briffa.lewis.weightless.game.elements.CanvasElement;
import com.developments.briffa.lewis.weightless.interfaces.Movable;

public class CoinElement extends CanvasElement implements Movable {

    private float dy;
    private Drawable image;

    private static final int COIN_WIDTH = 50;
    private static final int COIN_HEIGHT = 50;
    private static final int COIN_IMAGE = R.drawable.coin_spin;

    public CoinElement(Context context, Canvas canvas) {
        super((int)(Math.random() * canvas.getWidth()),
                canvas.getHeight(),
                COIN_WIDTH,
                COIN_HEIGHT
        );

        this.image = ContextCompat.getDrawable(context, COIN_IMAGE);
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

    @Override
    public String getElementName() {
        return "coin";
    }
}
