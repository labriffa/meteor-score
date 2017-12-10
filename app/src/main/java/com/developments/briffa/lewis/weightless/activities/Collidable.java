package com.developments.briffa.lewis.weightless.activities;

import android.graphics.Canvas;

import com.developments.briffa.lewis.weightless.game.elements.CanvasElement;

public interface Collidable {

    void collision();
    void move(Canvas canvas);
}
