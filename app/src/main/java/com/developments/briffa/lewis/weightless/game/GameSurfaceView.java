package com.developments.briffa.lewis.weightless.game;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.developments.briffa.lewis.weightless.R;
import com.developments.briffa.lewis.weightless.game.elements.HudElement;
import com.developments.briffa.lewis.weightless.game.elements.hazards.HazardElement;
import com.developments.briffa.lewis.weightless.game.utilities.CollisionDetector;
import com.developments.briffa.lewis.weightless.activities.GameOverActivity;
import com.developments.briffa.lewis.weightless.game.elements.hazards.StandardMeteor;
import com.developments.briffa.lewis.weightless.game.elements.PauseElement;
import com.developments.briffa.lewis.weightless.game.elements.PlayerElement;
import com.developments.briffa.lewis.weightless.game.elements.SpaceBackgroundElement;
import com.developments.briffa.lewis.weightless.game.elements.hazards.SpinningMeteor;
import com.developments.briffa.lewis.weightless.game.elements.collectibles.CoinElement;
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
    private GameElementFactory mGameElementFactory;

    // BOOLEANS
    private boolean isSound;
    private boolean hasInitialized;

    // GAME OBJECTS
    private SpaceBackgroundElement mSpaceBackgroundElement;
    private PlayerElement mPlayerElement;
    private PauseElement mPauseElement;

    // GAME OBJECT COLLECTIONS
    private ArrayList<BulletElement> mBullets;
    private ArrayList<HazardElement> mHazards;
    private ArrayList<CoinElement> mCollectibles;

    // SOUNDS
    private MediaPlayer mThemePlayer;
    private MediaPlayer mCoinMediaPlayer;

    // BACKGROUND ACTIVITIES
    private GameRunnable mGameRunnable;
    private Thread mThread;

    // SENSORS
    private Sensor mSensor;
    private SensorManager mSensorManager;
    private SensorEventListener mSensorEventListener;
    private Vibrator mVibrator;

    // SCORE
    private int distanceTravelled = 0;
    public static final String DISTANCE_TRAVELLED_KEY = "Travelled Distance";

    public GameSurfaceView(Context context) {
        super(context);

        mSurfaceHolder = getHolder();

        // observe surface holders lifecycle e.g. created, destroyed
        mSurfaceHolder.addCallback(this);

        // initialize game objects
        mBullets = new ArrayList<>();
        mHazards = new ArrayList<>();
        mCollectibles = new ArrayList<>();

        // sensor initialization
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mVibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

        mGameRunnable = new GameRunnable(this);

        // check if the user wishes to play sound
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        isSound = sharedPreferences.getBoolean(getResources().getString(R.string.pref_user_play_sound), true);
        hasInitialized = false;

        this.setOnTouchListener(this);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {

        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if(x >= mPauseElement.getX() && x <= mPauseElement.getX()+125 && y >= mPauseElement.getY() && y <= mPauseElement.getY()+100) {
                    if(mGameRunnable.isRunning()) {
                        pause();
                    } else {
                        resume();
                    }
                } else {
                    fire();
                }
                break;
        }

        return true;
    }

    /**
     * Ends the game by taking the user to the game over activity screen
     */
    private void gameOver() {
        mVibrator.vibrate(500);

        // send the score to the game over screen
        Intent gameOverIntent = new Intent(getContext(), GameOverActivity.class);
        gameOverIntent.putExtra(DISTANCE_TRAVELLED_KEY, distanceTravelled);
        getContext().startActivity(gameOverIntent);
    }

    /**
     * Adds to the users current coins
     */
    private void coinPickup() {

        increaseUserScore();

        if(isSound) {
            mCoinMediaPlayer.start();
        }
    }

    /**
     * Increases the users coin by one and saves the result via shared preferences
     */
    public void increaseUserScore() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        int userCoins = sharedPreferences.getInt(getResources().getString(R.string.pref_user_coins_key), -1) + 1;
        sharedPreferences.edit().putInt(getResources().getString(R.string.pref_user_coins_key), userCoins).apply();
    }

    /**
     * Refreshes the screen
     *
     * @param canvas
     */
    private void clearScreen(Canvas canvas) {
        canvas.drawColor(Color.BLACK, PorterDuff.Mode.CLEAR);
    }

    /**
     * Interrupts the background game thread and pause the theme music
     */
    public void pause() {
        mGameRunnable.setIsRunning(false);

        if(mThemePlayer.isPlaying()) {
            mThemePlayer.pause();
        }

    }

    /**
     * Creates a new background thread and resumes the them music
     */
    public void resume() {
        mGameRunnable.setIsRunning(true);
        mThread = new Thread(mGameRunnable);
        mThread.start();

        if(isSound) {
            mThemePlayer.start();
        }
    }

    /**
     *  Starts playing the theme music
     */
    private void initializeBackgroundMusic() {
        mThemePlayer = MediaPlayer.create(getContext(), R.raw.rainbow);

        if(isSound) {
            mThemePlayer.start();
        }
    }

    /**
     * Called when the game first starts, called when the
     * activity is additionally resumed, i.e. user goes to the home screen and back
     *
     * @param holder
     */
    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        // the surface is created for the first time, do intial game setup
        if(!hasInitialized) {
            Canvas canvas = mSurfaceHolder.lockCanvas();
            gameSetup(canvas);
            mSurfaceHolder.unlockCanvasAndPost(canvas);
            hasInitialized = true;
        }

        mSensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                mPlayerElement.setDx(-(event.values[0]*8));
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                // stub
            }
        };

        mSensorManager.registerListener(mSensorEventListener, mSensor, SensorManager.SENSOR_DELAY_GAME);

        // user may have gone to the home screen and gone back
        resume();
    }

    /**
     * Called when the game surface is no longer visible, i.e the user has moved
     * to another activity, or the user has gone to the home screen
     *
     * @param holder
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mThemePlayer.pause();
        mSensorManager.unregisterListener(mSensorEventListener);
        mGameRunnable.setIsRunning(false);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) { }

    /**
     * Initial game setup
     *
     * @param canvas
     */
    private void gameSetup(Canvas canvas) {

        mGameElementFactory = new GameElementFactory(this.getContext(), canvas);

        // Sounds
        initializeBackgroundMusic();
        mCoinMediaPlayer = MediaPlayer.create(getContext(), R.raw.coin_pickup);

        // create initial game objects
        mSpaceBackgroundElement = (SpaceBackgroundElement) mGameElementFactory.getInstance("space-background");
        mPlayerElement = (PlayerElement) mGameElementFactory.getInstance("player");
        mPauseElement = (PauseElement) mGameElementFactory.getInstance("pause");
        mCollectibles.add((CoinElement) mGameElementFactory.getInstance("coin"));
        mHazards.add((SpinningMeteor) mGameElementFactory.getInstance("spinning-meteor"));
        mHazards.add((StandardMeteor) mGameElementFactory.getInstance("meteor"));
    }


    private void fire() {
        BulletElement bulletElement = new BulletElement(
                mPlayerElement.getX()+(mPlayerElement.getWidth()/2)-5,
                mPlayerElement.getY()+mPlayerElement.getHeight(),
                10, 20, 30);

        mBullets.add(bulletElement);
    }

    /**
     * Draws all game elements to the screen
     */
    public void draw() {

        Canvas canvas = mSurfaceHolder.lockCanvas();

        if(mSurfaceHolder.getSurface().isValid()) {

            clearScreen(canvas);

            drawBackground(canvas);
            drawHud(canvas);
            drawCollectibles(canvas);
            drawHazards(canvas);

            mPlayerElement.move(canvas);
            distanceTravelled += mPlayerElement.getSpeed();
            mSurfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    /**
     * Draws the current background
     *
     * @param canvas
     */
    private void drawBackground(Canvas canvas) {
        mSpaceBackgroundElement.draw(canvas);
    }

    /**
     * Draws the User Information
     *
     * @param canvas
     */
    private void drawHud(Canvas canvas) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        int userCoins = sharedPreferences.getInt(getResources().getString(R.string.pref_user_coins_key), -1);

        if(-1 == userCoins) {
            sharedPreferences.edit().putInt(getResources().getString(R.string.pref_user_coins_key), 0).apply();
            userCoins = 0;
        }

        HudElement hudElement = new HudElement(getContext(), String.valueOf(userCoins));
        hudElement.draw(canvas);
    }

    /**
     * Draws the items in the collectibles and checks if they collided with the player
     *
     * @param canvas
     */
    private void drawCollectibles(Canvas canvas) {
        // go through collectibles checking to see if the player
        // has collided with any of them
        for (int i = 0; i < mCollectibles.size(); i++) {
            CoinElement collectible = mCollectibles.get(i);
            collectible.move(canvas);
            boolean hasCollided = CollisionDetector.hasCollided(collectible, mPlayerElement);

            if (hasCollided || collectible.hasPassed()) {
                if (hasCollided) {
                    coinPickup();
                }

                mCollectibles.remove(i);
                mCollectibles.add((CoinElement) collectible.recreate(mGameElementFactory));
            }
        }
    }

    /**
     * Draws the items in the hazards and checks to see if they collided with the player or
     * any of the bullets
     *
     * @param canvas
     */
    private void drawHazards(Canvas canvas) {
        // go hazards checking to see if the player collided with
        // any of them
        for (int i = 0; i < mHazards.size(); i++) {
            HazardElement hazardElement = mHazards.get(i);
            hazardElement.move(canvas);

            // go through bullets checking to see if any of them collided
            // with the current meteor
            for (int j = 0; j < mBullets.size(); j++) {
                BulletElement bulletElement = mBullets.get(j);
                bulletElement.move(canvas);

                boolean hasCollided = CollisionDetector.hasCollided(hazardElement, bulletElement);

                if (hasCollided) {
                    hazardElement.damage(bulletElement.getDamage());
                }

                if (bulletElement.getY() + bulletElement.getHeight() >= canvas.getHeight() || hasCollided) {
                    mBullets.remove(j);
                }

                // if the hazard has been destroyed, we no longer need to check for other collisions
                if (hazardElement.getHealth() <= 0) {
                    mVibrator.vibrate(200);
                    break;
                }
            }

            if (hazardElement.getHealth() <= 0 || hazardElement.hasPassed()) {
                mHazards.remove(i);
                mHazards.add((HazardElement) hazardElement.recreate(mGameElementFactory));
            }

            if (CollisionDetector.hasCollided(hazardElement, mPlayerElement)) {
                gameOver();
                break;
            }
        }
    }
}
