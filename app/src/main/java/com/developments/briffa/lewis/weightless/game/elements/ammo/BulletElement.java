package com.developments.briffa.lewis.weightless.game.elements.ammo;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;

import com.developments.briffa.lewis.weightless.game.elements.CanvasElement;
import com.developments.briffa.lewis.weightless.interfaces.Movable;

public class BulletElement extends CanvasElement implements Movable {

    private float dy;

    public BulletElement(float x, float y, int width, int height) {
        super(x, y, width, height);
        dy = 10;
    }

    public void move(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.YELLOW);
        setY(getY()+dy);
        paint.setShader(new LinearGradient(0, 0, 0, getHeight(), Color.YELLOW, Color.RED, Shader.TileMode.MIRROR));
        canvas.drawRect((int)getX(),(int)getY(),(int)(getX()+getWidth()),(int)(getY()+getHeight()), paint);
    }

    @Override
    public boolean hasPassed() {
        return false;
    }
}
