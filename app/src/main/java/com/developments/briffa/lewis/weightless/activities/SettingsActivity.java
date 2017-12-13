package com.developments.briffa.lewis.weightless.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.developments.briffa.lewis.weightless.R;

/**
 * Activity: Responsible for displaying the Settings Fragment
 *
 * @author lewisbriffa
 * @version 1.2
 */
public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // change default styling
        setTheme(R.style.SettingsTheme);

        setContentView(R.layout.activity_settings);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Settings");

    }
}
