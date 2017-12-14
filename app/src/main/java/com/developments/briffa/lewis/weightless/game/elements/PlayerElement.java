package com.developments.briffa.lewis.weightless.game.elements;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.developments.briffa.lewis.weightless.R;
import com.developments.briffa.lewis.weightless.interfaces.Movable;

/**
 * Represents the main game player object
 *
 * @author lewisbriffa
 * created on 11/11/2017
 */
public class PlayerElement extends CanvasElement {

    private float dx;
    private Drawable image;

    private static final int PLAYER_STARTING_POS_X = 0;
    private static final int PLAYER_STARTING_POS_Y = 160;
    private static final int PLAYER_WIDTH = 180;
    private static final int PLAYER_HEIGHT = 280;
    private static final int PLAYER_IMAGE = R.drawable.rocket;
    private static final int MILES_PER_HOUR = 5;

    public PlayerElement(Context context, Canvas canvas)
    {
        super(PLAYER_STARTING_POS_X, PLAYER_STARTING_POS_Y, PLAYER_WIDTH, PLAYER_HEIGHT);
        this.image = ContextCompat.getDrawable(context, PLAYER_IMAGE);
        dx = 0;
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

    public int getSpeed() { return MILES_PER_HOUR; }

    public void setDx(float dx)
    {
        this.dx = dx;
    }

    @Override
    public String getElementName() {
        return "player";
    }
}
