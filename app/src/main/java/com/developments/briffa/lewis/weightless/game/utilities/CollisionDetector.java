package com.developments.briffa.lewis.weightless.game.utilities;

import com.developments.briffa.lewis.weightless.game.elements.CanvasElement;

public class CollisionDetector {

    public static boolean hasCollided(CanvasElement canvasElement1, CanvasElement canvasElement2) {
        return canvasElement1.getRect().intersect(canvasElement2.getRect());
    }

}
