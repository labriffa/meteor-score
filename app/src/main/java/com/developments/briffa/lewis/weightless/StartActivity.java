package com.developments.briffa.lewis.weightless;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Represents the start activity screen
 * used to showcase the game menu
 *
 * @author lewisbriffa
 * created on 11/11/2017
 */
public class StartActivity extends AppCompatActivity {

    private ImageButton mButtonStart;
    private ImageButton mButtonEncyclopedia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        mButtonStart = (ImageButton) findViewById(R.id.button_start);
        mButtonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gameIntent = new Intent(StartActivity.this, GameActivity.class);
                startActivity(gameIntent);
            }
        });

        mButtonEncyclopedia = (ImageButton) findViewById(R.id.button_encyclopedia);
        mButtonEncyclopedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent encyclopediaIntent = new Intent(StartActivity.this, EncyclopediaActivity.class);
                startActivity(encyclopediaIntent);
            }
        });
    }
}
