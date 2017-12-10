package com.developments.briffa.lewis.weightless.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.developments.briffa.lewis.weightless.R;

public class EncyclopediaDetailsActivity extends AppCompatActivity {

    private TextView mTextViewPlanetName;
    private ImageView mImageViewPlanet;
    private TextView mTextViewPlanetDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encyclopedia_details);

        Intent intent = getIntent();
        String name = intent.getExtras().getString("NAME");
        int image = intent.getExtras().getInt("ID");
        String description = intent.getExtras().getString("DESCRIPTION");

        mImageViewPlanet = (ImageView) findViewById(R.id.planet_details_image);
        mTextViewPlanetDescription = (TextView) findViewById(R.id.textView5);

        mImageViewPlanet.setImageResource(image);
        mTextViewPlanetDescription.setText(description);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(name);
    }
}
