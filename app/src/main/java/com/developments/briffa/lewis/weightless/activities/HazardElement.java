package com.developments.briffa.lewis.weightless.activities;

import android.util.Log;

import com.developments.briffa.lewis.weightless.game.elements.CanvasElement;

import java.util.ArrayList;

public abstract class HazardElement extends CanvasElement implements Collidable {

    private ArrayList<ElementObservable> mElementObservables;
    private boolean hasBeenPassed;
    private boolean hasBeenHit;
    private boolean hasBeenDestroyed;

    public HazardElement(float x, float y, int width, int height) {
        super(x, y, width, height);
        mElementObservables = new ArrayList<>();
        hasBeenHit = false;
        hasBeenPassed = false;
        hasBeenDestroyed = false;
    }

    public void registerObserver(ElementObservable observer) {
        mElementObservables.add(observer);
    }

    public void unregisterObserver(ElementObservable observer) {
        mElementObservables.remove(observer);
    }

    public void notifyObservables() {
        for(ElementObservable elementObserver : mElementObservables) {
            if(hasBeenHit) {
                elementObserver.update("meteor-hit");
            } else if(hasBeenPassed) {
                elementObserver.update("meteor-passed");
            } else if(hasBeenDestroyed) {
                elementObserver.update("meteor-destroyed");
            } else {
                elementObserver.update("meteor");
            }
        }
    }

    public void collision() {
        hasBeenHit = true;
        notifyObservables();
    }

    public void passed() {
        hasBeenPassed = true;
        notifyObservables();
    }

    public void destroyed() {
        hasBeenDestroyed = true;
        notifyObservables();
    }

    public void setHasBeenHit(boolean hasBeenHit) {
        this.hasBeenHit = hasBeenHit;
    }

    public boolean hasBeenHit() {
        return hasBeenHit;
    }
}
