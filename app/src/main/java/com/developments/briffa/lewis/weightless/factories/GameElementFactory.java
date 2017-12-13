package com.developments.briffa.lewis.weightless.factories;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;

import com.developments.briffa.lewis.weightless.R;
import com.developments.briffa.lewis.weightless.game.elements.CanvasElement;
import com.developments.briffa.lewis.weightless.game.elements.SpaceBackgroundElement;
import com.developments.briffa.lewis.weightless.game.elements.hazards.StandardMeteor;
import com.developments.briffa.lewis.weightless.game.elements.PauseElement;
import com.developments.briffa.lewis.weightless.game.elements.PlayerElement;
import com.developments.briffa.lewis.weightless.game.elements.hazards.SpinningMeteor;
import com.developments.briffa.lewis.weightless.game.elements.collectibles.CoinElement;

/**
 * Factory: Responsible for the creation of game world objects. Creates different game objects
 * based on the type passed.
 *
 * @author lewisbriffa
 * @version 1.5
 */
public class GameElementFactory {

    private Context mContext;
    private Canvas mCanvas;

    // Player
    private static final int PLAYER_STARTING_POS_X = 0;
    private static final int PLAYER_STARTING_POS_Y = 150;
    private static final int PLAYER_WIDTH = 200;
    private static final int PLAYER_HEIGHT = 300;
    private static final int PLAYER_IMAGE = R.drawable.rocket;

    // Coin
    private static final int COIN_WIDTH = 50;
    private static final int COIN_HEIGHT = 50;
    private static final int COIN_IMAGE = R.drawable.coin_spin;

    // Standard Meteor
    private static final int STANDARD_METEOR_IMAGE = R.drawable.meteor;
    private static final int STANDARD_METEOR_SIZE_DIVIDER = 3;

    // Spinning Meteor
    private static final int SPINNING_METEOR_IMAGE = R.drawable.spinning_meteor;
    private static final int SPINNING_METEOR_SIZE_DIVIDER = 3;
    private static final int SPINNING_METEOR_OFFSCREEN_MULTIPLIER = 4;

    // Space Background
    private static final int SPACE_STARTING_POS_X = 0;
    private static final int SPACE_STARTING_POS_Y = 0;

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
        int canvasWidth = mCanvas.getWidth();
        int canvasHeight = mCanvas.getHeight();

        if(type.equals("player")) {

            canvasElement =
                    new PlayerElement(PLAYER_STARTING_POS_X, PLAYER_STARTING_POS_Y, PLAYER_WIDTH, PLAYER_HEIGHT, ContextCompat.getDrawable(mContext, PLAYER_IMAGE));

        } else if(type.equals("coin")) {

            canvasElement = new CoinElement(
                    (int)(Math.random() * canvasWidth), canvasHeight, COIN_WIDTH, COIN_HEIGHT, ContextCompat.getDrawable(mContext, COIN_IMAGE));

        } else if(type.equals("meteor")) {

            // get a random dimension
            int randomDimension = (int) (Math.random() * mCanvas.getWidth() / STANDARD_METEOR_SIZE_DIVIDER);

            canvasElement = new StandardMeteor(
                    (int) (Math.random() * canvasWidth),
                    canvasHeight,
                    randomDimension,
                    randomDimension,
                    ContextCompat.getDrawable(mContext, STANDARD_METEOR_IMAGE));

        } else if(type.equals("spinning-meteor")) {

            int randomDimension = (int) (Math.random() * mCanvas.getWidth() / SPINNING_METEOR_SIZE_DIVIDER);

            canvasElement = new SpinningMeteor((int) (Math.random() * canvasWidth),
                    canvasHeight * SPINNING_METEOR_OFFSCREEN_MULTIPLIER,
                    randomDimension,
                    randomDimension,
                    ContextCompat.getDrawable(mContext, SPINNING_METEOR_IMAGE));

        } else if(type.equals("space-background")) {

            // pass whether or not the user wishes to see a space background
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
            boolean isSpaceBg = sharedPreferences.getBoolean(mContext.getResources().getString(R.string.pref_user_show_space), true);

            canvasElement = new SpaceBackgroundElement(SPACE_STARTING_POS_X, SPACE_STARTING_POS_Y, canvasWidth, canvasHeight, isSpaceBg);
        } else if(type.equals("pause")) {
            canvasElement = new PauseElement(mCanvas.getWidth() - 125, 25, 100, 200, Color.RED);
        }

        return canvasElement;
    }
}
