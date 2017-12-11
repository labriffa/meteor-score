package com.developments.briffa.lewis.weightless.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.developments.briffa.lewis.weightless.R;
import com.developments.briffa.lewis.weightless.activities.EncyclopediaActivity;
import com.developments.briffa.lewis.weightless.interfaces.OnItemSelectionChangeListener;
import com.developments.briffa.lewis.weightless.models.EncyclopediaEntry;

import java.util.ArrayList;

/**
 * Fragment: Responsible for displaying the encyclopedia entries as a grid
 *
 * @author lewisbriffa
 * @version V1.4
 */
public class EncyclopediaFragment extends Fragment {

    private GridView mGridView;
    private ArrayList<EncyclopediaEntry> mPlanetEntries;

    public EncyclopediaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_encyclopedia, container, false);

        EncyclopediaActivity encyclopediaActivty = (EncyclopediaActivity) getActivity();
        mPlanetEntries = encyclopediaActivty.getList();

        mGridView = fragmentView.findViewById(R.id.gridview_encyclopedia);
        mGridView.setAdapter(new EncyclopediaFragment.ImageAdapter());

        return fragmentView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public class ImageAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mPlanetEntries.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View itemView = getActivity().getLayoutInflater().inflate(R.layout.item_encyclopedia, null);
            ImageView imageView = itemView.findViewById(R.id.imageView);
            TextView textView = itemView.findViewById(R.id.textView);
            imageView.setImageResource(mPlanetEntries.get(position).getImage());
            textView.setText(mPlanetEntries.get(position).getName());
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OnItemSelectionChangeListener onItemSelectionChangeListener = (OnItemSelectionChangeListener) getActivity();
                    onItemSelectionChangeListener.onItemSelectionChange(position);
                }
            });

            return itemView;
        }
    }
}
