package com.developments.briffa.lewis.weightless.game.elements.collectibles;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

import com.developments.briffa.lewis.weightless.factories.GameElementFactory;
import com.developments.briffa.lewis.weightless.game.elements.CanvasElement;
import com.developments.briffa.lewis.weightless.interfaces.Movable;
import com.developments.briffa.lewis.weightless.interfaces.Recreatable;

public class StarElement extends CanvasElement implements Movable, Recreatable {

    private float dy;
    private Paint mPaint;
    private Drawable image;

    public StarElement(float x, float y, int width, int height, Drawable image) {
        super(x,y, width, height);
        this.image = image;
        dy = -10;
        mPaint = new Paint();
    }

    public void move(Canvas canvas) {
        setY(getY() + dy);
        image.setBounds((int)getX(),(int)getY(),(int)(getX()+getWidth()),(int)(getY()+getHeight()));
        image.draw(canvas);
    }

    @Override
    public boolean hasPassed() {
        return getY() <= -getWidth();
    }

    @Override
    public CanvasElement recreate(GameElementFactory gameElementFactory) {
        return gameElementFactory.getInstance("star");
    }
}
