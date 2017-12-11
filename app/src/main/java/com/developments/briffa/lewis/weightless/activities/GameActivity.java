package com.developments.briffa.lewis.weightless.activities;

import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.developments.briffa.lewis.weightless.game.elements.GameSurfaceView;
import com.developments.briffa.lewis.weightless.R;


/**
 * Represents the activity responsible for starting the game
 * screen
 *
 * @author lewisbriffa
 * created on 11/11/2017
 */
public class GameActivity extends AppCompatActivity {

    private GameSurfaceView mGameSurfaceView;
    private RelativeLayout mRelativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mGameSurfaceView = new GameSurfaceView(this);

        mRelativeLayout = (RelativeLayout) findViewById(R.id.activity_game);

        mRelativeLayout.addView(mGameSurfaceView);

        getSupportActionBar().hide();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mGameSurfaceView.setRunning(false);
        mGameSurfaceView.quitHandlerThread();
        mGameSurfaceView.getMediaPlayer().stop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGameSurfaceView.setRunning(false);
        mGameSurfaceView.quitHandlerThread();
        mGameSurfaceView.getMediaPlayer().stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mGameSurfaceView.setRunning(false);
        mGameSurfaceView.quitHandlerThread();
        mGameSurfaceView.getMediaPlayer().stop();
    }
}
