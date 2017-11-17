package com.developments.briffa.lewis.weightless;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

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

        mTextViewPlanetName = (TextView) findViewById(R.id.textView2);
        mImageViewPlanet = (ImageView) findViewById(R.id.imageView2);
        mTextViewPlanetDescription = (TextView) findViewById(R.id.textView4);

        mTextViewPlanetName.setText(name);
        mImageViewPlanet.setImageResource(image);
        mTextViewPlanetDescription.setText(description);
    }
}
