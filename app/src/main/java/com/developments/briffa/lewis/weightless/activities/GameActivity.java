package com.developments.briffa.lewis.weightless.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.developments.briffa.lewis.weightless.game.GameSurfaceView;
import com.developments.briffa.lewis.weightless.R;


/**
 * Activity: Responsible for displaying the game screen,
 * makes the necessary call to start the game surface view
 *
 * @author lewisbriffa
 * created on 11/11/2017
 */
public class GameActivity extends AppCompatActivity {

    GameSurfaceView mGameSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mGameSurfaceView = new GameSurfaceView(this);

        RelativeLayout mRelativeLayout = (RelativeLayout) findViewById(R.id.activity_game);

        mRelativeLayout.addView(mGameSurfaceView);

        getSupportActionBar().hide();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGameSurfaceView.setIsRunning(true);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGameSurfaceView.setIsRunning(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
