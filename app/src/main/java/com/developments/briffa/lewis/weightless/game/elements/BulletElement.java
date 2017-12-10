package com.developments.briffa.lewis.weightless.game.elements;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.developments.briffa.lewis.weightless.activities.Collidable;
import com.developments.briffa.lewis.weightless.activities.ElementObservable;

import java.util.ArrayList;

public class BulletElement extends CanvasElement implements Collidable {

    private float dy;
    private ArrayList<ElementObservable> mElementObservables;

    public BulletElement(float x, float y, int width, int height) {
        super(x, y, width, height);
        dy = 10;
        mElementObservables = new ArrayList<>();
    }

    public void move(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.YELLOW);
        setY(getY()+dy);
        canvas.drawRect((int)getX(),(int)getY(),(int)(getX()+getWidth()),(int)(getY()+getHeight()), paint);
    }

    @Override
    public void collision() {
        notifyObservables();
    }

    public void registerObserver(ElementObservable observer) {
        mElementObservables.add(observer);
    }

    public void unregisterObserver(ElementObservable observer) {
        mElementObservables.remove(observer);
    }

    public void notifyObservables() {
        for(ElementObservable elementObserver : mElementObservables) {
            elementObserver.update(this);
        }
    }
}
