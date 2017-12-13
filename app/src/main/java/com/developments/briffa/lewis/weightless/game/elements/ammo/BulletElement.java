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
    private int damage;
    private Paint mShade1;
    private Paint mShade2;

    public BulletElement(float x, float y, int width, int height, int damage) {
        super(x, y, width, height);
        dy = 10;
        this.damage = damage;

        // first shade
        mShade1 = new Paint();
        mShade1.setColor(Color.YELLOW);

        // second shade
        mShade2 = new Paint();
        mShade2.setColor(Color.RED);
    }

    /**
     * Draws and updates the movement of the coin
     *
     * @param canvas
     */
    public void move(Canvas canvas) {

        // update movement
        setY(getY() + dy);

        // add fire effect
        mShade1.setShader(new LinearGradient(0, 0, 0, getHeight(), mShade1.getColor(), mShade2.getColor(), Shader.TileMode.MIRROR));
        canvas.drawRect((int)getX(),(int)getY(),(int)(getX()+getWidth()),(int)(getY()+getHeight()), mShade1);
    }

    /**
     * Method stub, set to override the movable hasPassed method
     *
     * @return boolean
     */
    @Override
    public boolean hasPassed() {
        return false;
    }

    public int getDamage() {
        return damage;
    }
}
