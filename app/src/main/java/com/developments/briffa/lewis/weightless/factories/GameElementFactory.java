package com.developments.briffa.lewis.weightless.factories;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;

import com.developments.briffa.lewis.weightless.R;
import com.developments.briffa.lewis.weightless.game.elements.CanvasElement;
import com.developments.briffa.lewis.weightless.game.elements.hud.HudElement;
import com.developments.briffa.lewis.weightless.game.elements.SpaceBackgroundElement;
import com.developments.briffa.lewis.weightless.game.elements.hazards.StandardMeteor;
import com.developments.briffa.lewis.weightless.game.elements.PlayerElement;
import com.developments.briffa.lewis.weightless.game.elements.hazards.SpinningMeteor;
import com.developments.briffa.lewis.weightless.game.elements.collectibles.CoinElement;
import com.developments.briffa.lewis.weightless.interfaces.GameFactory;

/**
 * Factory: Responsible for the creation of game world objects. Creates different game objects
 * based on the type passed.
 *
 * @author lewisbriffa
 * @version 1.5
 */
public class GameElementFactory implements GameFactory {

    private Context mContext;
    private Canvas mCanvas;

    public GameElementFactory(Context context, Canvas canvas) {
        mContext = context;
        mCanvas = canvas;
    }

    /**
     * The factory method to determine which object to create based on the type passed by the user
     * @param type
     * @return
     */
    public CanvasElement getInstance(String type) {

        CanvasElement canvasElement = null;

        if(type.equals("player")) {

            canvasElement = new PlayerElement(mContext, mCanvas);

        } else if(type.equals("coin")) {

            canvasElement = new CoinElement(mContext, mCanvas);

        } else if(type.equals("standard-meteor")) {

            canvasElement = new StandardMeteor(mContext, mCanvas);

        } else if(type.equals("spinning-meteor")) {

            canvasElement = new SpinningMeteor(mContext, mCanvas);

        } else if(type.equals("space-background")) {

            // pass whether or not the user wishes to see a space background
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
            boolean isSpaceBg = sharedPreferences.getBoolean(mContext.getResources().getString(R.string.pref_user_show_space), true);

            canvasElement = new SpaceBackgroundElement(mContext, mCanvas, isSpaceBg);

        } else if(type.equals("hud")) {

            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
            String userCoins = String.valueOf(sharedPreferences.getInt(mContext.getResources().getString(R.string.pref_user_coins_key), 0));

            canvasElement = new HudElement(mContext, mCanvas, userCoins);
        }

        return canvasElement;
    }
}
