package com.developments.briffa.lewis.weightless.interfaces;

import com.developments.briffa.lewis.weightless.game.elements.CanvasElement;

public interface ElementObservable {

    void update(String message);
    void update(CanvasElement canvasElement);
}
