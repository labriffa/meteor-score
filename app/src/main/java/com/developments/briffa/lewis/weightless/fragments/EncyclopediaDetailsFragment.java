package com.developments.briffa.lewis.weightless.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.developments.briffa.lewis.weightless.R;
import com.developments.briffa.lewis.weightless.activities.EncyclopediaActivity;
import com.developments.briffa.lewis.weightless.models.EncyclopediaEntry;

import java.util.ArrayList;

/**
 * Fragment: Responsible for displaying the information for a given encyclopedia entry.
 *
 * @author lewisbriffa
 * @version V1.3
 */
public class EncyclopediaDetailsFragment extends Fragment {

    private TextView mTextViewPlanetName;
    private ImageView mImageViewPlanet;
    private TextView mTextViewPlanetDescription;

    public final static String ENTRY_INDEX = "position";

    public EncyclopediaDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View detailsView = inflater.inflate(R.layout.fragment_encyclopedia_details, container, false);

        mTextViewPlanetName = detailsView.findViewById(R.id.planet_details_name);
        mTextViewPlanetDescription = detailsView.findViewById(R.id.planet_details_description);
        mImageViewPlanet = detailsView.findViewById(R.id.planet_details_image);

        Bundle bundle = getArguments();

        // were currently in mobile mode
        if(bundle != null) {

            ArrayList<EncyclopediaEntry> encyclopediaManager = ((EncyclopediaActivity)getActivity()).getList();
            int planetEntryPosition = bundle.getInt(ENTRY_INDEX);

            EncyclopediaEntry encyclopediaEntry = encyclopediaManager.get(planetEntryPosition);

            mImageViewPlanet.setImageResource(encyclopediaEntry.getImage());
            mTextViewPlanetName.setText(encyclopediaEntry.getName());
            mTextViewPlanetDescription.setText(encyclopediaEntry.getDescription());
        }

        return detailsView;
    }

    /**
     * The code for this method was referenced from
     * https://stackoverflow.com/questions/18320713/getsupportactionbar-from-inside-of-fragment-actionbarcompat
     *
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(getArguments() != null) {
            // change the action bar title to reflect the currently selected encyclopedia entry
            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(mTextViewPlanetName.getText());
        }
    }

    /**
     * Sets the image view resource for the encyclopedia entry
     *
     * @param id
     */
    public void setImageViewPlanet(int id) {
        mImageViewPlanet.setImageResource(id);
    }

    /**
     * Sets the name for the encyclopedia entry
     *
     * @param name
     */
    public void setTextViewPlanetName(String name) {
        mTextViewPlanetName.setText(name);
    }

    /**
     * Sets the description for the encyclopedia entry
     *
     * @param description
     */
    public void setTextViewPlanetDescription(String description) {
        mTextViewPlanetDescription.setText(description);
    }
}
