package com.developments.briffa.lewis.weightless.interfaces;

import android.graphics.Canvas;

public interface Movable {
    void move(Canvas canvas);
    boolean hasPassed();
}
