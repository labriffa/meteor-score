package com.developments.briffa.lewis.weightless.game.elements.hazards;

import com.developments.briffa.lewis.weightless.game.elements.CanvasElement;
import com.developments.briffa.lewis.weightless.interfaces.Movable;
import com.developments.briffa.lewis.weightless.interfaces.Recreatable;

public abstract class HazardElement extends CanvasElement implements Movable, Recreatable {

    private boolean hasBeenHit;

    public HazardElement(float x, float y, int width, int height) {
        super(x, y, width, height);
        hasBeenHit = false;
    }

    public abstract int getHealth();

    public abstract void damage(int hitPoints);

    public boolean hasBeenHit() {
        return hasBeenHit;
    }
}
