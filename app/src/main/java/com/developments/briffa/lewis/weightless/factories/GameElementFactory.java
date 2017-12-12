package com.developments.briffa.lewis.weightless.factories;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;

import com.developments.briffa.lewis.weightless.R;
import com.developments.briffa.lewis.weightless.game.elements.CanvasElement;
import com.developments.briffa.lewis.weightless.game.elements.hazards.StandardMeteor;
import com.developments.briffa.lewis.weightless.game.elements.PauseElement;
import com.developments.briffa.lewis.weightless.game.elements.PlayerElement;
import com.developments.briffa.lewis.weightless.game.elements.hazards.SpinningMeteor;
import com.developments.briffa.lewis.weightless.game.elements.collectibles.StarElement;

public class GameElementFactory {

    private Context mContext;
    private Canvas mCanvas;

    public GameElementFactory(Context context, Canvas canvas) {
        mContext = context;
        mCanvas = canvas;
    }

    public CanvasElement getInstance(String type) {

        CanvasElement canvasElement = null;

        if(type.equals("player")) {
            canvasElement = new PlayerElement(0, 150, 200, 300, ContextCompat.getDrawable(mContext, R.drawable.rocket));
        } else if(type.equals("star")) {
            canvasElement = new StarElement((int)(Math.random()*mCanvas.getWidth()),mCanvas.getHeight(),50,50,ContextCompat.getDrawable(mContext, R.drawable.coin_spin));
        } else if(type.equals("meteor")) {
            int dimension = (int) (Math.random() * mCanvas.getWidth() / 3) + 20;
            canvasElement = new StandardMeteor((int) (Math.random() * mCanvas.getWidth()), mCanvas.getHeight(), dimension, dimension, ContextCompat.getDrawable(mContext, R.drawable.meteor));
        } else if(type.equals("spinning-meteor")) {
            int dimension = (int) (Math.random() * mCanvas.getWidth() / 3) + 20;
            canvasElement = new SpinningMeteor((int) (Math.random() * mCanvas.getWidth()), mCanvas.getHeight() * 2, dimension, dimension, ContextCompat.getDrawable(mContext, R.drawable.spinning_meteor));
        } else if(type.equals("pause")) {
            canvasElement = new PauseElement(mCanvas.getWidth() - 125, 25, 100, 200, Color.RED);
        }

        return canvasElement;
    }
}
