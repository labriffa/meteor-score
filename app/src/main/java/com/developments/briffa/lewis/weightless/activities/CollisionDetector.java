package com.developments.briffa.lewis.weightless.activities;

import com.developments.briffa.lewis.weightless.game.elements.CanvasElement;

public class CollisionDetector {

    public static boolean hasCollided(CanvasElement canvasElement1, CanvasElement canvasElement2) {
        return (hasCollidedToLeftOfElement(canvasElement1, canvasElement2)
                || hasCollidedBetweenElement(canvasElement1, canvasElement2)
                || hasCollidedToRightOfElement(canvasElement1, canvasElement2))
                && canvasElement1.getY() <= canvasElement2.getY() + canvasElement2.getHeight();
    }

    private static boolean hasCollidedToLeftOfElement(CanvasElement canvasElement1, CanvasElement canvasElement2) {
        return canvasElement1.getWidth() + canvasElement1.getX() >= canvasElement2.getX() && canvasElement1.getWidth() + canvasElement1.getX() <= canvasElement2.getX() + canvasElement2.getWidth();
    }

    private static boolean hasCollidedBetweenElement(CanvasElement canvasElement1, CanvasElement canvasElement2) {
        return canvasElement2.getX() >= canvasElement1.getX() && canvasElement2.getX()+ canvasElement2.getWidth() <= canvasElement1.getWidth()+canvasElement1.getX();
    }

    private static boolean hasCollidedToRightOfElement(CanvasElement canvasElement1, CanvasElement canvasElement2) {
        return canvasElement1.getX() <= canvasElement2.getX() + canvasElement2.getWidth() && canvasElement1.getX() >= canvasElement2.getX();
    }

}
