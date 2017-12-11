package com.developments.briffa.lewis.weightless.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.developments.briffa.lewis.weightless.models.EncyclopediaEntry;
import com.developments.briffa.lewis.weightless.R;

import java.util.ArrayList;

public class EncyclopediaActivity extends AppCompatActivity implements OnItemSelectionChangeListener {

    private GridView mGridView;
    private ArrayList<EncyclopediaEntry> mPlanetEntries;
    private EncyclopediaManager mEncyclopediaManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mEncyclopediaManager = new EncyclopediaManager(this);
        mPlanetEntries = mEncyclopediaManager.getList();

        setContentView(R.layout.activity_encyclopedia);

        getSupportActionBar().setTitle("Encyclopedia");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        EncyclopediaDetailsFragment encyclopediaDetailsFragment = (EncyclopediaDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.encyclopedia_details_fragment);

        if(encyclopediaDetailsFragment != null) {

            EncyclopediaEntry planetEntry = mPlanetEntries.get(0);

            encyclopediaDetailsFragment.setTextViewPlanetName(planetEntry.getName());
            encyclopediaDetailsFragment.setImageViewPlanet(planetEntry.getImage());
            encyclopediaDetailsFragment.setTextViewPlanetDescription(planetEntry.getDescription());
        }

    }

    public void onItemSelectionChange(int position) {

        EncyclopediaDetailsFragment encyclopediaDetailsFragment = (EncyclopediaDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.encyclopedia_details_fragment);

        if(encyclopediaDetailsFragment != null) {

            EncyclopediaEntry planetEntry = mPlanetEntries.get(position);

            encyclopediaDetailsFragment.setTextViewPlanetName(planetEntry.getName());
            encyclopediaDetailsFragment.setImageViewPlanet(planetEntry.getImage());
            encyclopediaDetailsFragment.setTextViewPlanetDescription(planetEntry.getDescription());
        } else {
            encyclopediaDetailsFragment = new EncyclopediaDetailsFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(EncyclopediaDetailsFragment.ENTRY_INDEX, position);
            encyclopediaDetailsFragment.setArguments(bundle);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

            fragmentTransaction.replace(R.id.activity_encyclopedia, encyclopediaDetailsFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

    public ArrayList<EncyclopediaEntry> getList() {
        return mPlanetEntries;
    }


}