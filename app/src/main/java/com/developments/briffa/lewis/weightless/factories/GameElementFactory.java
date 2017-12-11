package com.developments.briffa.lewis.weightless.factories;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.developments.briffa.lewis.weightless.R;
import com.developments.briffa.lewis.weightless.game.elements.CanvasElement;
import com.developments.briffa.lewis.weightless.game.elements.MeteorElement;
import com.developments.briffa.lewis.weightless.game.elements.PauseElement;
import com.developments.briffa.lewis.weightless.game.elements.PlayerElement;
import com.developments.briffa.lewis.weightless.game.elements.SpinningMeteor;
import com.developments.briffa.lewis.weightless.game.elements.StarElement;
import com.developments.briffa.lewis.weightless.game.elements.StarTrailElement;

import java.util.ArrayList;
import java.util.Random;

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
            canvasElement = new MeteorElement((int) (Math.random() * mCanvas.getWidth()), mCanvas.getHeight(), dimension, dimension, ContextCompat.getDrawable(mContext, R.drawable.meteor));
        } else if(type.equals("spinning-meteor")) {
            int dimension = (int) (Math.random() * mCanvas.getWidth() / 3) + 20;
            canvasElement = new SpinningMeteor((int) (Math.random() * mCanvas.getWidth()), mCanvas.getHeight() * 2, dimension, dimension, ContextCompat.getDrawable(mContext, R.drawable.spinning_meteor));
        } else if(type.equals("pause")) {
            canvasElement = new PauseElement(mCanvas.getWidth() - 125, 25, 100, 200, Color.RED);
        } else if(type.equals("star-trail")) {
            ArrayList<StarElement> starElements = new ArrayList<>();

            Random rand = new Random();

            int startX = (int)(Math.random()*mCanvas.getWidth());
            int startY = rand.nextInt(3)+1*mCanvas.getHeight();
            int starWidth = 50;
            int starHeight = 50;

            for(int i = 0; i < 5; i++) {
                if(0 == i) {
                    starElements.add(new StarElement(startX, startY, starWidth, starHeight, ContextCompat.getDrawable(mContext, R.drawable.coin_spin)));
                } else {
                    starElements.add(new StarElement(startX, startY + ((starHeight + 20) * i), starWidth, starHeight, ContextCompat.getDrawable(mContext, R.drawable.coin_spin)));
                }
            }

            canvasElement = new StarTrailElement(startX, startY, 50, 50, starElements);
        }

        return canvasElement;
    }
}
