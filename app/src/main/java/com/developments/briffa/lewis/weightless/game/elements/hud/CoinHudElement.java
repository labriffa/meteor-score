package com.developments.briffa.lewis.weightless.game.elements.hud;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

import com.developments.briffa.lewis.weightless.game.elements.CanvasElement;

public class CoinHudElement extends CanvasElement {
    private Drawable image;
    private Paint mPaint;
    private String mUserCoins;

    // Corresponding label value
    private static final int LABEL_X_POS_SPACING = 10;
    private static final int LABEL_Y_POS_SPACING = 3;
    private static final int LABEL_TEXT_SIZE = 45;
    private static final int LABEL_TEXT_COLOR = Color.WHITE;

    public CoinHudElement(float x, float y, int width, int height, Drawable image, String userCoins) {
        super(x, y, width, height);
        this.image = image;
        this.mUserCoins = userCoins;

        mPaint = new Paint();
        mPaint.setTextSize(LABEL_TEXT_SIZE);
        mPaint.setColor(LABEL_TEXT_COLOR);
    }

    public void draw(Canvas canvas) {
        image.setBounds((int)getX(), (int)getY(), (int)(getX() + getWidth()), (int)(getY() + getHeight()));
        image.draw(canvas);

        canvas.drawText(
                String.valueOf(mUserCoins),
                getX()+getWidth() + LABEL_X_POS_SPACING,
                getHeight() - LABEL_Y_POS_SPACING,
                mPaint
        );
    }

    public void updateUserCoins(String userCoins) {
        mUserCoins = userCoins;
    }

    @Override
    public String getElementName() {
        return "coin-hud";
    }
}
