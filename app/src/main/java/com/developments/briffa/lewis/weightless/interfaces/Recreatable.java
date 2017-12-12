package com.developments.briffa.lewis.weightless.interfaces;

import com.developments.briffa.lewis.weightless.factories.GameElementFactory;
import com.developments.briffa.lewis.weightless.game.elements.CanvasElement;

public interface Recreatable {
    CanvasElement recreate(GameElementFactory gameElementFactory);
}
