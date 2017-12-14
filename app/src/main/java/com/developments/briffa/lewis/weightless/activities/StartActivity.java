package com.developments.briffa.lewis.weightless.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.developments.briffa.lewis.weightless.R;

/**
 * Represents the start activity screen
 * used to showcase the game menu
 *
 * @author lewisbriffa
 * created on 11/11/2017
 */
public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        ImageButton mButtonStart = (ImageButton) findViewById(R.id.button_start);
        mButtonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gameIntent = new Intent(StartActivity.this, GameActivity.class);
                startActivity(gameIntent);
            }
        });

        ImageButton mButtonEncyclopedia = (ImageButton) findViewById(R.id.button_encyclopedia);
        mButtonEncyclopedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent encyclopediaIntent = new Intent(StartActivity.this, EncyclopediaActivity.class);
                startActivity(encyclopediaIntent);
            }
        });

        ImageButton mButtonSettings = (ImageButton) findViewById(R.id.button_settings);
        mButtonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingsIntent = new Intent(StartActivity.this, SettingsActivity.class);
                startActivity(settingsIntent);
            }
        });

        if(getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }
}
