package com.developments.briffa.lewis.weightless.activities;

import android.content.Context;
import android.net.Uri;
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
import com.developments.briffa.lewis.weightless.models.PlanetEntry;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EncyclopediaDetailsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class EncyclopediaDetailsFragment extends Fragment {

    private TextView mTextViewPlanetName;
    private ImageView mImageViewPlanet;
    private TextView mTextViewPlanetDescription;

    final static String ENTRY_INDEX = "position";

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

        if(bundle != null) {

            ArrayList<PlanetEntry> planetEntries = ((EncyclopediaActivity)getActivity()).getList();
            int planetEntryPosition = bundle.getInt(ENTRY_INDEX);

            PlanetEntry planetEntry = planetEntries.get(planetEntryPosition);

            mImageViewPlanet.setImageResource(planetEntry.getImage());
            mTextViewPlanetName.setText(planetEntry.getName());
            mTextViewPlanetDescription.setText(planetEntry.getDescription());
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
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(mTextViewPlanetName.getText());
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void setImageViewPlanet(int id) {
        mImageViewPlanet.setImageResource(id);
    }

    public void setTextViewPlanetName(String name) {
        mTextViewPlanetName.setText(name);
    }

    public void setTextViewPlanetDescription(String description) {
        mTextViewPlanetDescription.setText(description);
    }
}
