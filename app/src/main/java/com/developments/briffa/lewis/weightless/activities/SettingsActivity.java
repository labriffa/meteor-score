package com.developments.briffa.lewis.weightless.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.developments.briffa.lewis.weightless.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTheme(R.style.SettingsTheme);

        setContentView(R.layout.activity_settings);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Settings");

    }
}
