package com.developments.briffa.lewis.weightless.factories;

import com.developments.briffa.lewis.weightless.game.elements.CanvasElement;

public class GameElementFactory {

    public CanvasElement getInstance(String type) {

        CanvasElement canvasElement = null;

        if(type.equals("star")) {
            //canvasElement = new Star();
        }

        return canvasElement;
    }
}
