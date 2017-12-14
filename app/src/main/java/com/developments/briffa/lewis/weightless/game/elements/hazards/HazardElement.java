package com.developments.briffa.lewis.weightless.game.elements.hazards;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.developments.briffa.lewis.weightless.game.elements.CanvasElement;
import com.developments.briffa.lewis.weightless.interfaces.Movable;

public abstract class HazardElement extends CanvasElement implements Movable {

    private boolean hasBeenHit;
    private int health;
    private Paint lifeBarFullPaint;
    private Paint lifeBarDepletedPaint;

    private static final int LIFE_BAR_FULL_COLOR = Color.YELLOW;
    private static final int LIFE_BAR_DEPLETED_COLOR = Color.RED;

    public HazardElement(float x, float y, int width, int height) {
        super(x, y, width, height);
        hasBeenHit = false;
        health = width;

        lifeBarFullPaint = new Paint();
        lifeBarFullPaint.setColor(LIFE_BAR_FULL_COLOR);

        lifeBarDepletedPaint = new Paint();
        lifeBarDepletedPaint.setColor(LIFE_BAR_DEPLETED_COLOR);
    }

    public HazardElement(float x, float y, int radius) {
        this(x, y, radius, radius);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Draws the hazard's life bar, this is a combination of a depletion bar and a health bar
     *
     * @param canvas
     */
    public void drawLifeBar(Canvas canvas) {

        // draw depleted life bar
        canvas.drawRect((int) (getX() + getHealth()), (int)(getY()+getHeight()+50), (int) (getX() + getWidth()), (int)(getY()+getHeight()+75), lifeBarDepletedPaint);

        // draw over remaining health
        canvas.drawRect((int)getX(), (int)(getY()+getHeight()+50), (int) (getX() + getHealth()), (int)(getY()+getHeight()+75), lifeBarFullPaint);
    }

    public abstract void damage(int hitPoints);

    public boolean hasBeenHit() {
        return hasBeenHit;
    }
}
