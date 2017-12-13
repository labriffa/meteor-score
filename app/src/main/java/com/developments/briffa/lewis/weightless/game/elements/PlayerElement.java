package com.developments.briffa.lewis.weightless.game.elements;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

import com.developments.briffa.lewis.weightless.interfaces.Movable;

/**
 * Represents the main game player object
 *
 * @author lewisbriffa
 * created on 11/11/2017
 */
public class PlayerElement extends CanvasElement implements Movable {

    private float dx;
    private Drawable image;
    private int milesPerHour;

    public PlayerElement(float x, float y, int width, int height, Drawable image)
    {
        super(x, y, width, height);
        this.image = image;
        dx = 0;
        milesPerHour = 5;
    }

    /**
     * Draws and updates the movement of the player, checks if the player has gone too far on
     * either sides of the screen
     *
     * @param canvas
     */
    public void move(Canvas canvas)
    {
        // check if this object is too far left or too far right of the screen
        if(getX() < 0 || getX() + getWidth() >= canvas.getWidth()) {
            setX(getX() < 0 ? 0 : canvas.getWidth()-getWidth());
        }

        // update player position
        setX(getX() + dx);

        // redraw the player
        image.setBounds((int) getX(), (int) getY(), (int) (getX() + getWidth()), (int) (getY() + getHeight()));
        image.draw(canvas);
    }

    /**
     * Stub method to override Movable hasPassed()
     *
     * @return
     */
    @Override
    public boolean hasPassed() {
        return false;
    }

    public int getSpeed() { return milesPerHour; }

    public void setDx(float dx)
    {
        this.dx = dx;
    }
}
