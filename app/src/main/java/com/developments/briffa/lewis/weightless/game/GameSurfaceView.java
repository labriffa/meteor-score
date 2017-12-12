package com.developments.briffa.lewis.weightless.game;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.developments.briffa.lewis.weightless.R;
import com.developments.briffa.lewis.weightless.game.elements.hazards.HazardElement;
import com.developments.briffa.lewis.weightless.game.utilities.CollisionDetector;
import com.developments.briffa.lewis.weightless.activities.GameOverActivity;
import com.developments.briffa.lewis.weightless.game.elements.hazards.StandardMeteor;
import com.developments.briffa.lewis.weightless.game.elements.PauseElement;
import com.developments.briffa.lewis.weightless.game.elements.PlayerElement;
import com.developments.briffa.lewis.weightless.game.elements.SpaceBackgroundElement;
import com.developments.briffa.lewis.weightless.game.elements.hazards.SpinningMeteor;
import com.developments.briffa.lewis.weightless.game.elements.collectibles.StarElement;
import com.developments.briffa.lewis.weightless.factories.GameElementFactory;
import com.developments.briffa.lewis.weightless.game.elements.ammo.BulletElement;

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
    private PlayerElement mPlayerElement;
    private SensorManager mSensorManager;
    private Sensor mSensor;
    private Vibrator mVibrator;
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    private SpaceBackgroundElement mSpaceBackgroundElement;
    private PauseElement mPauseElement;
    private MediaPlayer mMediaPlayer;
    private MediaPlayer mCoinMediaPlayer;
    private GameElementFactory mGameElementFactory;
    private int distanceTravelled = 0;

    private ArrayList<BulletElement> mBullets;
    private ArrayList<HazardElement> mHazards;
    private ArrayList<StarElement> mCollectables;

    public static final String DISTANCE_TRAVELLED_KEY = "Travelled Distance";

    public GameSurfaceView(final Context context) {
        super(context);

        mSurfaceHolder = getHolder();

        // observe surface holders lifecycle e.g. created, destroyed
        mSurfaceHolder.addCallback(this);

        mHandlerThread = new HandlerThread("My First HandlerThread");
        mHandlerThread.start();

        initializeBackgroundMusic();

        mCoinMediaPlayer = MediaPlayer.create(context, R.raw.coin_pickup);

        // initialize game objects
        mBullets = new ArrayList<>();
        mHazards = new ArrayList<>();
        mCollectables = new ArrayList<>();

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

                            drawBackground(canvas);

                            drawHud(canvas);

                            // go through collectibles checking to see if the player
                            // has collided with any of them
                            for(int i = 0; i < mCollectables.size(); i++) {
                                StarElement collectable = mCollectables.get(i);
                                collectable.move(canvas);
                                boolean hasCollided = CollisionDetector.hasCollided(collectable, mPlayerElement);

                                if(hasCollided || collectable.hasPassed()) {
                                    if(hasCollided) {
                                        starPickup(canvas, collectable);
                                    }

                                    mCollectables.remove(i);
                                    mCollectables.add((StarElement) collectable.recreate(mGameElementFactory));
                                }
                            }

                            // go hazards checking to see if the player collided with
                            // any of them
                            for(int i = 0; i < mHazards.size(); i++) {
                                HazardElement hazardElement = mHazards.get(i);

                                hazardElement.move(canvas);

                                if(mHazards.get(i) != null) {

                                    // go through bullets checking to see if any of them collided
                                    // with the current meteor
                                    for(int j = 0; j < mBullets.size(); j++) {
                                        BulletElement bulletElement = mBullets.get(j);
                                        bulletElement.move(canvas);

                                        boolean hasCollided = CollisionDetector.hasCollided(hazardElement, bulletElement);

                                        if(hasCollided) {
                                            hazardElement.damage(30);
                                        }

                                        if(bulletElement.getY()+bulletElement.getHeight() >= canvas.getHeight() || hasCollided) {
                                            mBullets.remove(j);
                                        }

                                        // if the hazard has been destroyed, we no longer need to check for other collisions
                                        if(hazardElement.getHealth() <= 0) {
                                            mVibrator.vibrate(200);
                                            break;
                                        }
                                    }
                                }

                                if(hazardElement.getHealth() <= 0 || hazardElement.hasPassed()) {
                                    mHazards.remove(i);
                                    mHazards.add((HazardElement) hazardElement.recreate(mGameElementFactory));
                                }

                                if(CollisionDetector.hasCollided(hazardElement, mPlayerElement)) {
                                    gameOver();
                                    break;
                                }
                            }

                            mPlayerElement.move(canvas);
                            distanceTravelled += mPlayerElement.getSpeed();
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

        Drawable star = ContextCompat.getDrawable(getContext(), R.drawable.coin_spin);
        star.setBounds(canvas.getWidth()-300, 25, canvas.getWidth()-200, 125);
        star.draw(canvas);

        Paint textPaint = new Paint();
        textPaint.setTextSize(60);
        textPaint.setColor(Color.WHITE);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        int userCoins = sharedPreferences.getInt(getResources().getString(R.string.pref_user_coins_key), -1);

        if(-1 == userCoins) {
            sharedPreferences.edit().putInt(getResources().getString(R.string.pref_user_coins_key), 0).apply();
            userCoins = 0;
        }

        canvas.drawText(String.valueOf(userCoins), canvas.getWidth()-450, 100, textPaint);
    }

    private void gameOver() {
        mVibrator.vibrate(500);
        Intent gameOverIntent = new Intent(getContext(), GameOverActivity.class);
        gameOverIntent.putExtra(DISTANCE_TRAVELLED_KEY, distanceTravelled);
        getContext().startActivity(gameOverIntent);
    }

    private void starPickup(Canvas canvas, StarElement starElement) {
        starElement.setY(canvas.getHeight()*2);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        boolean playSound = sharedPreferences.getBoolean(getResources().getString(R.string.pref_user_play_sound), true);
        if(playSound) {
            mCoinMediaPlayer.start();
        }
        int userCoins = sharedPreferences.getInt(getResources().getString(R.string.pref_user_coins_key), -1) + 1;
        sharedPreferences.edit().putInt(getResources().getString(R.string.pref_user_coins_key), userCoins).apply();
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

        if(mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
            int currentMusicPos = mMediaPlayer.getCurrentPosition();
            mMediaPlayer.seekTo(currentMusicPos);
        }

        mSurfaceHolder.unlockCanvasAndPost(canvas);
    }

    private void unpause() {
        isRunning = true;
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        boolean playSound = sharedPreferences.getBoolean(getResources().getString(R.string.pref_user_play_sound), true);
        if(playSound) {
            mMediaPlayer.start();
        }
    }

    private void initializeBackgroundMusic() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        boolean playSound = sharedPreferences.getBoolean(getResources().getString(R.string.pref_user_play_sound), true);

        mMediaPlayer = MediaPlayer.create(getContext(), R.raw.rainbow);

        if(playSound) {
            mMediaPlayer.start();
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Canvas canvas = mSurfaceHolder.lockCanvas();

        mGameElementFactory = new GameElementFactory(this.getContext(), canvas);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        boolean isSpaceBackground = sharedPreferences.getBoolean(getResources().getString(R.string.pref_user_show_space), true);
        mSpaceBackgroundElement = new SpaceBackgroundElement(0, 0, isSpaceBackground);
        mSpaceBackgroundElement.draw(canvas);
        mPlayerElement = (PlayerElement) mGameElementFactory.getInstance("player");
        mPauseElement = (PauseElement) mGameElementFactory.getInstance("pause");

        mCollectables.add((StarElement) mGameElementFactory.getInstance("star"));

        mHazards.add((SpinningMeteor) mGameElementFactory.getInstance("spinning-meteor"));
        mHazards.add((StandardMeteor) mGameElementFactory.getInstance("meteor"));

        mSurfaceHolder.unlockCanvasAndPost(canvas);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    public void fire() {
        BulletElement bulletElement = new BulletElement(mPlayerElement.getX()+(mPlayerElement.getWidth()/2)-5, mPlayerElement.getY()+mPlayerElement.getHeight(), 10, 20);
        mBullets.add(bulletElement);
    }

    public void drawBackground(Canvas canvas) {
        mSpaceBackgroundElement.draw(canvas);
    }
}
