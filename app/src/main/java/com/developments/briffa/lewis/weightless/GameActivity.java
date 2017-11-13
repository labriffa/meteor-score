package com.developments.briffa.lewis.weightless;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.logging.Logger;


/**
 * Represents the activity responsible for starting the game
 * screen
 *
 * @author lewisbriffa
 * created on 11/11/2017
 */
public class GameActivity extends AppCompatActivity {

    private SensorManager mSensorManager;
    private Sensor mAccelerometerSensor;
    private ImageView mImageViewSpaceman;
    private GameSurfaceView mGameSurfaceView;
    private RelativeLayout mRelativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mGameSurfaceView = new GameSurfaceView(this);

        mRelativeLayout = (RelativeLayout) findViewById(R.id.activity_game);

        mRelativeLayout.addView(mGameSurfaceView);
    }
}
