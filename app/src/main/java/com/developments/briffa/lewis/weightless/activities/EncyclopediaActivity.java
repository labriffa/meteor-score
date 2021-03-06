package com.developments.briffa.lewis.weightless.activities;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.developments.briffa.lewis.weightless.fragments.EncyclopediaDetailsFragment;
import com.developments.briffa.lewis.weightless.interfaces.OnItemSelectionChangeListener;
import com.developments.briffa.lewis.weightless.managers.EncyclopediaManager;
import com.developments.briffa.lewis.weightless.models.EncyclopediaEntry;
import com.developments.briffa.lewis.weightless.R;

import java.util.ArrayList;

/**
 * Activity: Responsible for handling the interaction between users and
 * encyclopedia entries. A multi-pane UI is assembled for larger screens so that
 * the encyclopedia items grid shares itself alongside the encyclopedia entry
 * description. These screens are dynamically swapped out on smaller devices.
 *
 * @author lewisbriffa
 * @version v1.5
 */
public class EncyclopediaActivity extends AppCompatActivity implements OnItemSelectionChangeListener {

    private ArrayList<EncyclopediaEntry> mPlanetEntries;
    private EncyclopediaManager mEncyclopediaManager;

    private EncyclopediaDetailsFragment mEncyclopediaDetailsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mEncyclopediaManager = new EncyclopediaManager(this);
        mPlanetEntries = mEncyclopediaManager.getList();

        setContentView(R.layout.activity_encyclopedia);

        mEncyclopediaDetailsFragment =
                (EncyclopediaDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.encyclopedia_details_fragment);

        // if were in dual fragment screen mode, initial the description fragment
        // as the first entry in the encyclopedia
        if(mEncyclopediaDetailsFragment != null) {

            EncyclopediaEntry planetEntry = mPlanetEntries.get(0);

            mEncyclopediaDetailsFragment.setTextViewPlanetName(planetEntry.getName());
            mEncyclopediaDetailsFragment.setImageViewPlanet(planetEntry.getImage());
            mEncyclopediaDetailsFragment.setTextViewPlanetDescription(planetEntry.getDescription());
        }

        // update the action bar to reflect the currently shown fragment
        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if(mEncyclopediaDetailsFragment == null || !(mEncyclopediaDetailsFragment.isVisible())) {
                    if(getSupportActionBar() != null) {
                        getSupportActionBar().setTitle(R.string.encyclopedia_title);
                    }
                }
            }
        });

        // show the initial action bar title
        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.encyclopedia_title);
        }
    }

    /**
     * Handles the selection of an item in the encyclopedia's grid view
     * updates the details fragment based on whether or not the device
     * is a mobile or a larger device e.g. tablet
     *
     * @param position
     */
    public void onItemSelectionChange(int position) {

        mEncyclopediaDetailsFragment =
                (EncyclopediaDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.encyclopedia_details_fragment);


        // check if were in dual screen fragment mode
        if(mEncyclopediaDetailsFragment != null) {

            EncyclopediaEntry planetEntry = mPlanetEntries.get(position);

            mEncyclopediaDetailsFragment.setTextViewPlanetName(planetEntry.getName());
            mEncyclopediaDetailsFragment.setImageViewPlanet(planetEntry.getImage());
            mEncyclopediaDetailsFragment.setTextViewPlanetDescription(planetEntry.getDescription());

        } else {

            // explicitly create the details fragment
            mEncyclopediaDetailsFragment = new EncyclopediaDetailsFragment();

            // pass the id of the encyclopedia entry
            Bundle bundle = new Bundle();
            bundle.putInt(EncyclopediaDetailsFragment.ENTRY_INDEX, position);
            mEncyclopediaDetailsFragment.setArguments(bundle);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

            // replace the encyclopedia fragment
            fragmentTransaction.replace(R.id.activity_encyclopedia, mEncyclopediaDetailsFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

    public ArrayList<EncyclopediaEntry> getList() {
        return mPlanetEntries;
    }
}