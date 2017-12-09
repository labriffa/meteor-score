package com.developments.briffa.lewis.weightless.game.elements;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;

public class StarTrailElement extends CanvasElement {

    private ArrayList<StarElement> mStarElements;

    public StarTrailElement(float x, float y, int width, int height, ArrayList<StarElement> starElements) {
        super(x, y, width, height);
        mStarElements = starElements;
    }

    public void draw(Canvas canvas) {
        for(StarElement starElement : mStarElements) {
            starElement.move(canvas);
        }

        setY(mStarElements.get(0).getY());
    }

    public ArrayList<StarElement> getStarElements() {
        return mStarElements;
    }
}
