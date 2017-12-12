package com.developments.briffa.lewis.weightless.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.developments.briffa.lewis.weightless.R;
import com.developments.briffa.lewis.weightless.game.GameSurfaceView;

public class GameOverActivity extends AppCompatActivity {

    private ImageButton mButton;
    private ImageButton mButton2;
    private ImageButton mButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        mButton = (ImageButton) findViewById(R.id.imageButton);
        mButton2 = (ImageButton) findViewById(R.id.imageButton2);
        mButton3 = (ImageButton) findViewById(R.id.imageButton5);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gameIntent = new Intent(GameOverActivity.this, GameActivity.class);
                startActivity(gameIntent);
            }
        });
        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menuIntent = new Intent(GameOverActivity.this, StartActivity.class);
                startActivity(menuIntent);
            }
        });

        if(getIntent().hasExtra(GameSurfaceView.DISTANCE_TRAVELLED_KEY)) {
            mButton3.setOnClickListener(new View.OnClickListener() {
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
}
