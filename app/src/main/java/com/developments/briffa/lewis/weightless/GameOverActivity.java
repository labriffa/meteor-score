package com.developments.briffa.lewis.weightless;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class GameOverActivity extends AppCompatActivity {

    private ImageButton mButton;
    private ImageButton mButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        mButton = (ImageButton) findViewById(R.id.imageButton);
        mButton2 = (ImageButton) findViewById(R.id.imageButton2);

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
    }
}
