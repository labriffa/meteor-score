package com.developments.briffa.lewis.weightless.game.elements;

import android.graphics.Rect;

/**
 * Defines the blueprint for a generic game element object
 * with getters and setters for default game object properties
 *
 * @author lewisbriffa
 * @version 1.2
 */
public abstract class CanvasElement {

    private float x;
    private float y;

    private int width;
    private int height;

    public CanvasElement(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    // Getters
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public float getWidth() {
        return width;
    }
    public float getHeight() {
        return height;
    }
    public Rect getRect() {
        return new Rect((int)x, (int)y, (int)(x + getWidth()), (int)(y + getHeight()));
    }

    // Setters
    public void setX(float x) {
        this.x = x;
    }
    public void setY(float y) {
        this.y = y;
    }
}
