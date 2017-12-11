package com.developments.briffa.lewis.weightless.game.elements;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;

import com.developments.briffa.lewis.weightless.interfaces.Collidable;
import com.developments.briffa.lewis.weightless.interfaces.ElementObservable;

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
        paint.setShader(new LinearGradient(0, 0, 0, getHeight(), Color.YELLOW, Color.RED, Shader.TileMode.MIRROR));
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
