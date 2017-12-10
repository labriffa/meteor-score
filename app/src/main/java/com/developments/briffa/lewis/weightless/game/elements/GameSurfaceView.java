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
import com.developments.briffa.lewis.weightless.activities.Collidable;
import com.developments.briffa.lewis.weightless.activities.CollisionDetector;
import com.developments.briffa.lewis.weightless.activities.GameOverActivity;
import com.developments.briffa.lewis.weightless.factories.GameElementFactory;

import java.util.ArrayList;
import java.util.ListIterator;

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
    private SpaceBackgroundElement mSpaceBackgroundElement;
    private PauseElement mPauseElement;
    private StarElement mStarElement;
    private int score;
    private MediaPlayer mMediaPlayer;
    private MediaPlayer mCoinMediaPlayer;
    private GameElementFactory mGameElementFactory;
    private StarTrailElement mStarTrailElement;
    private float x = 0;
    private float y = 0;
    private ArrayList<BulletElement> mBulletElements;
    private CollisionDetector mCollisionDetector;
    private ArrayList<Collidable> collidables = new ArrayList<Collidable>();

    public GameSurfaceView(final Context context) {
        super(context);

        mSurfaceHolder = getHolder();

        // observe surface holders lifecycle e.g. created, destroyed
        mSurfaceHolder.addCallback(this);

        mHandlerThread = new HandlerThread("My First HandlerThread");
        mHandlerThread.start();

        initializeBackgroundMusic();

        mCoinMediaPlayer = MediaPlayer.create(context, R.raw.coin_pickup);

        mBulletElements = new ArrayList<>();

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

                            canvas.scale(1, -1, canvas.getWidth() / 2, canvas.getHeight() / 2);


                            drawBackground(canvas);
                            drawHud(canvas);

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

                            if(mMeteorElement.getY() <= 0) {
                                mMeteorElement = (MeteorElement) mGameElementFactory.getInstance("meteor");
                            }

                            if(CollisionDetector.hasCollided(mMeteorElement, mPlayerElement)) {
                                mVibrator.vibrate(500);
                                gameOver();
                            }

                            ArrayList<StarElement> starElements = mStarTrailElement.getStarElements();
                            for(StarElement starElement : starElements) {
                                if(CollisionDetector.hasCollided(starElement, mPlayerElement)) {
                                    starPickup(canvas, starElement);
                                }
                            }

                            if(CollisionDetector.hasCollided(mStarElement, mPlayerElement)) {
                                starPickup(canvas, mStarElement);
                            }


                            mMeteorElement.move(canvas);
                            mStarElement.move(canvas);

                            ListIterator<BulletElement> bulletElementListIterator = mBulletElements.listIterator();

                            while(bulletElementListIterator.hasNext()) {

                                BulletElement bulletElement = bulletElementListIterator.next();

                                if(CollisionDetector.hasCollided(mMeteorElement, bulletElement)) {
                                    mMeteorElement = (MeteorElement) mGameElementFactory.getInstance("meteor");
                                    bulletElementListIterator.remove();
                                    mVibrator.vibrate(200);
                                } else {
                                    bulletElement.move(canvas);
                                }
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
                } else {
                    fire();
                }
                break;
        }

        return true;
    }

    public MediaPlayer getMediaPlayer() {
        return mMediaPlayer;
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
        mSpaceBackgroundElement = new SpaceBackgroundElement(spacebg, 0, canvas.getHeight());
        mSpaceBackgroundElement.draw(canvas);
        mPlayerElement = (PlayerElement) mGameElementFactory.getInstance("player");
        mMeteorElement = (MeteorElement) mGameElementFactory.getInstance("meteor");
        mPauseElement = (PauseElement) mGameElementFactory.getInstance("pause");
        mStarElement = (StarElement) mGameElementFactory.getInstance("star");
        mSurfaceHolder.unlockCanvasAndPost(canvas);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    public void fire() {
        BulletElement bulletElement = new BulletElement(mPlayerElement.getX()+(mPlayerElement.getWidth()/2), mPlayerElement.getY()+mPlayerElement.getHeight(), 10, 20);
        mBulletElements.add(bulletElement);
    }

    public void drawBackground(Canvas canvas) {
        mSpaceBackgroundElement.draw(canvas);
    }
}
