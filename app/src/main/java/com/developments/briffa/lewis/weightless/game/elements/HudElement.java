package com.developments.briffa.lewis.weightless.game.elements;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.developments.briffa.lewis.weightless.R;

public class HudElement  {

    private Context mContext;
    private String mUserCoins;

    public HudElement(Context context, String userCoins) {
        this.mContext = context;
        this.mUserCoins = userCoins;
    }

    public void draw(Canvas canvas) {
        // pause button
        Drawable drawable = ContextCompat.getDrawable(mContext, R.drawable.pause_btn);
        drawable.setBounds(canvas.getWidth()-125, 25, canvas.getWidth()-25, 125);
        drawable.draw(canvas);

        // star
        Drawable coin = ContextCompat.getDrawable(mContext, R.drawable.coin_spin);
        coin.setBounds(canvas.getWidth()-300, 25, canvas.getWidth()-200, 125);
        coin.draw(canvas);

        Paint textPaint = new Paint();
        textPaint.setTextSize(60);
        textPaint.setColor(Color.WHITE);

        canvas.drawText(String.valueOf(mUserCoins), canvas.getWidth()-450, 100, textPaint);
    }
}

