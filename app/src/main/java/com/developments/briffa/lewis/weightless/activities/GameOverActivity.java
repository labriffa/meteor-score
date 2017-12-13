package com.developments.briffa.lewis.weightless.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.developments.briffa.lewis.weightless.R;
import com.developments.briffa.lewis.weightless.game.GameSurfaceView;

/**
 * Activity: Responsible for displaying the game over screen. Allows the user to go back to the main
 * menu, try again or share their score
 *
 * @author lewisbriffa
 * @version 1.4
 */
public class GameOverActivity extends AppCompatActivity {

    private ImageButton mButtonTryAgain;
    private ImageButton mButtonBackToMenu;
    private ImageButton mButtonShareScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        // References
        mButtonTryAgain = (ImageButton) findViewById(R.id.button_try_again);
        mButtonBackToMenu = (ImageButton) findViewById(R.id.button_back_to_menu);
        mButtonShareScore = (ImageButton) findViewById(R.id.button_share_score);

        // Listeners
        mButtonTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gameIntent = new Intent(GameOverActivity.this, GameActivity.class);
                startActivity(gameIntent);
            }
        });

        mButtonBackToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menuIntent = new Intent(GameOverActivity.this, StartActivity.class);
                startActivity(menuIntent);
            }
        });

        // check if the high score was passed to this activity
        if(getIntent().hasExtra(GameSurfaceView.DISTANCE_TRAVELLED_KEY)) {

            // handle sharing for high score
            mButtonShareScore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int score = getIntent().getIntExtra(GameSurfaceView.DISTANCE_TRAVELLED_KEY, 0);
                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_TEXT, "I Scored " + score + " in Meteor Score!");
                    shareIntent.setType("text/plain");
                    startActivity(shareIntent);
                }
            });
        }

        getSupportActionBar().hide();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
