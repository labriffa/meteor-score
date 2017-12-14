package com.developments.briffa.lewis.weightless.game.elements.hud;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.developments.briffa.lewis.weightless.R;
import com.developments.briffa.lewis.weightless.game.elements.CanvasElement;

/**
 * Draws the user hud display onto the screen by creating the necessary hud display
 * elements.
 *
 * @author lewisbriffa
 * @version 1.3
 */
public class HudElement extends CanvasElement {

    private Context mContext;

    private PauseHudElement mPauseHudElement;
    private CoinHudElement mCoinHudElement;

    // Pause
    private final static int PAUSE_STARTING_POS_X = 20;
    private final static int PAUSE_STARTING_POS_Y = 20;
    private final static int PAUSE_WIDTH = 70;
    private final static int PAUSE_HEIGHT = 70;

    // Coin Display
    private final static int COIN_STARTING_POS_X = 120;
    private final static int COIN_STARTING_POS_Y = 20;
    private final static int COIN_WIDTH = 5;
    private final static int COIN_HEIGHT = 70;

    // Hud Display
    private final static int HUD_BACKGROUND_COLOR = Color.WHITE;
    private final static int HUD_ALPHA = 10;
    private final static int HUD_STARTING_POS_X = 0;
    private final static int HUD_STARTING_POS_Y = 0;
    private final static int HUD_HEIGHT = 140;

    private Paint mPaint;

    public HudElement(Context context, Canvas canvas, String userCoins) {
        super(HUD_STARTING_POS_X, HUD_STARTING_POS_Y, canvas.getWidth(), HUD_HEIGHT);
        this.mContext = context;

        // pause hud element
        mPauseHudElement = new PauseHudElement(
                PAUSE_STARTING_POS_X,
                PAUSE_STARTING_POS_Y,
                PAUSE_STARTING_POS_X + PAUSE_WIDTH,
                PAUSE_STARTING_POS_Y + PAUSE_HEIGHT,
                ContextCompat.getDrawable(mContext, R.drawable.pause_btn)
        );

        // coin display hud element
        mCoinHudElement = new CoinHudElement(
                COIN_STARTING_POS_X,
                COIN_STARTING_POS_Y,
                COIN_STARTING_POS_X + COIN_WIDTH,
                COIN_STARTING_POS_Y + COIN_HEIGHT,
                ContextCompat.getDrawable(mContext, R.drawable.coin_spin),
                userCoins
        );

        mPaint = new Paint();
        mPaint.setColor(HUD_BACKGROUND_COLOR);
        mPaint.setAlpha(HUD_ALPHA);
    }

    public void draw(Canvas canvas) {

        canvas.drawRect(getX(), getY(), getWidth(), getHeight(), mPaint);

        // pause button
        mPauseHudElement.draw(canvas);

        // coin hud display
        mCoinHudElement.draw(canvas);
    }

    public PauseHudElement getPause() {
        return mPauseHudElement;
    }
    public CoinHudElement getCoinHudElement() {
        return mCoinHudElement;
    }

    public boolean isPauseLocation(float x, float y) {
        return x >= getPause().getX()
                && x <= getPause().getX() + getPause().getWidth()
                && y >= mPauseHudElement.getY()
                && y <= mPauseHudElement.getY() + getPause().getHeight();
    }

    @Override
    public String getElementName() {
        return "hud";
    }
}

