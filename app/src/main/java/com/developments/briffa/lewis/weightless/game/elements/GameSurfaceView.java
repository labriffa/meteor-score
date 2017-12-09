package com.developments.briffa.lewis.weightless.game.elements;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Vibrator;
import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.developments.briffa.lewis.weightless.R;
import com.developments.briffa.lewis.weightless.activities.GameOverActivity;
import com.developments.briffa.lewis.weightless.factories.GameElementFactory;

import java.util.ArrayList;

/**
 * Represents a game type surface view object responsible
 * for updating the canvas
 *
 * @author lewisbriffa
 * created on 11/11/2017
 */
public class GameSurfaceView extends SurfaceView implements View.OnTouchListener, SurfaceHolder.Callback {

    private SurfaceHolder mSurfaceHolder;
    private boolean isRunning = true;
    private Paint mPaint;
    private PlayerElement mPlayerElement;
    private SensorManager mSensorManager;
    private Sensor mSensor;
    private Vibrator mVibrator;
    private float moveX;
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    private MeteorElement mMeteorElement;
    private Drawable spacebg;
    //private SpaceBackgroundElement mSpaceBackgroundElement;
    private PauseElement mPauseElement;
    private StarElement mStarElement;
    private int score;
    private MediaPlayer mMediaPlayer;
    private MediaPlayer mCoinMediaPlayer;
    private GameElementFactory mGameElementFactory;
    private StarTrailElement mStarTrailElement;
    private float x = 0;
    private float y = 0;

    public GameSurfaceView(final Context context) {
        super(context);

        mSurfaceHolder = getHolder();

        // observe surface holders lifecycle e.g. created, destroyed
        mSurfaceHolder.addCallback(this);

        mHandlerThread = new HandlerThread("My First HandlerThread");
        mHandlerThread.start();

        initializeBackgroundMusic();

        mCoinMediaPlayer = MediaPlayer.create(context, R.raw.coin_pickup);

        score = 0;

        spacebg = ContextCompat.getDrawable(context, R.drawable.space_bg);

        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mVibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

        mSensorManager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
                {
                    if(mSurfaceHolder.getSurface().isValid()) {

                        if(isRunning) {

                            mPlayerElement.setDx(-(event.values[0]*8));

                            Canvas canvas = mSurfaceHolder.lockCanvas();
                            clearScreen(canvas);

                            setup(canvas);
                            drawHud(canvas);

                            if(mPauseElement == null) {
                                mPauseElement = (PauseElement) mGameElementFactory.getInstance("pause");
                            }

                            if(mStarTrailElement == null) {
                                mStarTrailElement = (StarTrailElement) mGameElementFactory.getInstance("star-trail");
                            }

                            if(mStarTrailElement.getY() <= - 1000) {
                                mStarTrailElement = (StarTrailElement) mGameElementFactory.getInstance("star-trail");
                            } else {
                                mStarTrailElement.draw(canvas);
                            }

                            if(mStarElement.getY() <= -1000) {
                                mStarElement = (StarElement) mGameElementFactory.getInstance("star");
                            } else {
                                mStarElement.move(canvas);
                            }

                            if(hasCollided(mMeteorElement)) {
                                mVibrator.vibrate(500);
                                gameOver();
                            }

                            ArrayList<StarElement> starElements = mStarTrailElement.getStarElements();
                            for(StarElement starElement : starElements) {
                                if(hasCollided(starElement)) {
                                    starPickup(canvas, starElement);
                                }
                            }

                            if(hasCollided(mStarElement)) {
                                starPickup(canvas, mStarElement);
                            }

                            if(mMeteorElement.getY()+100 <= 0) {
                                mMeteorElement = (MeteorElement) mGameElementFactory.getInstance("meteor");
                            } else {
                                mMeteorElement.move(canvas);
                            }

                            mPlayerElement.move(canvas);
                            mSurfaceHolder.unlockCanvasAndPost(canvas);

                        }
                    }
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                // stub
            }
        }, mSensor, SensorManager.SENSOR_DELAY_GAME, mHandler);

        this.setOnTouchListener(this);
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public void quitHandlerThread() {
        mHandlerThread.quit();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if(x >= mPauseElement.getX() && x <= mPauseElement.getX()+125 && y >= mPauseElement.getY() && y <= mPauseElement.getY()+100) {
                    if(isRunning) {
                        pause();
                    } else {
                        unpause();
                    }
                }
                break;
        }

        return true;
    }

    public MediaPlayer getMediaPlayer() {
        return mMediaPlayer;
    }

    private int randomLightColor() {
        ArrayList<String> colorHex = new ArrayList<>();

        colorHex.add("#1abc9c");
        colorHex.add("#2ecc71");
        colorHex.add("#3498db");
        colorHex.add("#9b59b6");
        colorHex.add("#16a085");
        colorHex.add("#27ae60");
        colorHex.add("#2980b9");
        colorHex.add("#8e44ad");
        colorHex.add("#e74c3c");
        colorHex.add("#f39c12");
        colorHex.add("#d35400");
        colorHex.add("#bdc3c7");

        return Color.parseColor(colorHex.get((int)(Math.random()*12)));
        //return Color.rgb((int)(Math.random()*(256 - 150 + 1)), (int)(Math.random()*(256 - 150 + 1)), (int)(Math.random()*(256 - 150 + 1)));
    }

    private boolean hasCollided(CanvasElement canvasElement) {
        return  (hasCollidedToLeftOfElement(canvasElement)
                || hasCollidedToRightOfElement(canvasElement)
                || hasCollidedBetweenElement(canvasElement))
                && canvasElement.getY() <= mPlayerElement.getY() + mPlayerElement.getHeight();
    }

    private boolean hasCollidedToLeftOfElement(CanvasElement canvasElement) {
        return canvasElement.getWidth() + canvasElement.getX() >= mPlayerElement.getX() && canvasElement.getWidth() + canvasElement.getX() <= mPlayerElement.getX() + mPlayerElement.getWidth();
    }

    private boolean hasCollidedBetweenElement(CanvasElement canvasElement) {
        return mPlayerElement.getX() >= canvasElement.getX() && mPlayerElement.getX()+ mPlayerElement.getWidth() <= canvasElement.getWidth()+canvasElement.getX();
    }

    private boolean hasCollidedToRightOfElement(CanvasElement canvasElement) {
        return canvasElement.getX() <= mPlayerElement.getX() + mPlayerElement.getWidth() && canvasElement.getX() >= mPlayerElement.getX();
    }

    private void drawHud(Canvas canvas) {
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.pause_btn);
        drawable.setBounds(canvas.getWidth()-125, 25, canvas.getWidth()-25, 125);
        drawable.draw(canvas);

        Drawable star = ContextCompat.getDrawable(getContext(), R.drawable.star);
        star.setBounds(canvas.getWidth()-300, 25, canvas.getWidth()-200, 125);
        star.draw(canvas);

        Paint textPaint = new Paint();
        textPaint.setTextSize(75);
        textPaint.setColor(Color.WHITE);
        canvas.drawText(String.valueOf(score), canvas.getWidth()-375, 100, textPaint);
    }

    private void setup(Canvas canvas) {

        // create space background
//        if (mSpaceBackgroundElement == null) {
//            mSpaceBackgroundElement = new SpaceBackgroundElement(spacebg, 0, canvas.getHeight());
//        } else {
//            mSpaceBackgroundElement.draw(canvas);
//        }

        // create meteor object
        if(mMeteorElement == null) {
            mMeteorElement = (MeteorElement) mGameElementFactory.getInstance("meteor");
        }

        // create star object
        if(mStarElement == null) {
            mStarElement = (StarElement) mGameElementFactory.getInstance("star");
        } else {
            mStarElement.move(canvas);
        }
    }

    private void gameOver() {
        Intent gameOverIntent = new Intent(getContext(), GameOverActivity.class);
        getContext().startActivity(gameOverIntent);
    }

    private void starPickup(Canvas canvas, StarElement starElement) {
        score += 1;
        //mStarElement = (StarElement) mGameElementFactory.getInstance("star");
        starElement.setY(canvas.getHeight()*2);
        mCoinMediaPlayer.start();
    }

    private void clearScreen(Canvas canvas) {
        canvas.drawColor(Color.BLACK, PorterDuff.Mode.CLEAR);
    }

    private void pause() {
        isRunning = false;
        Canvas canvas = mSurfaceHolder.lockCanvas();
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.play_btn);
        drawable.setBounds(canvas.getWidth()-125, 25, canvas.getWidth()-25, 125);
        drawable.draw(canvas);
        mMediaPlayer.pause();
        int currentMusicPos = mMediaPlayer.getCurrentPosition();
        mMediaPlayer.seekTo(currentMusicPos);
        mSurfaceHolder.unlockCanvasAndPost(canvas);
    }

    private void unpause() {
        isRunning = true;
        mMediaPlayer.start();
    }

    private void initializeBackgroundMusic() {
        mMediaPlayer = MediaPlayer.create(getContext(), R.raw.rainbow);
        mMediaPlayer.start();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Canvas canvas = mSurfaceHolder.lockCanvas();
        mGameElementFactory = new GameElementFactory(this.getContext(), canvas);
        mPlayerElement = (PlayerElement) mGameElementFactory.getInstance("player");
        mSurfaceHolder.unlockCanvasAndPost(canvas);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
