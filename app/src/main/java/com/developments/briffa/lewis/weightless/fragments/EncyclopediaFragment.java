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
import java.util.zip.Inflater;

/**
 * Fragment: Responsible for displaying the encyclopedia entries as a grid
 *
 * @author lewisbriffa
 * @version V1.4
 */
public class EncyclopediaFragment extends Fragment {

    private GridView mGridView;
    private ArrayList<EncyclopediaEntry> mEncyclopediaEntries;

    public EncyclopediaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_encyclopedia, container, false);

        // get encyclopedia entries
        EncyclopediaActivity encyclopediaActivity = (EncyclopediaActivity) getActivity();
        mEncyclopediaEntries = encyclopediaActivity.getList();

        // setup grid
        mGridView = fragmentView.findViewById(R.id.gridview_encyclopedia);
        mGridView.setAdapter(new EncyclopediaFragment.ItemAdapter());

        return fragmentView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public class ItemAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mEncyclopediaEntries.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        /**
         * Inflates the single grid view element and binds its views to the information
         * associated with the current encyclopedia entry
         *
         * @param position
         * @param convertView
         * @param parent
         * @return
         */
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = getActivity().getLayoutInflater();
            View itemView = inflater.inflate(R.layout.item_encyclopedia, parent, false);
            ImageView imageView = itemView.findViewById(R.id.imageView);
            TextView textView = itemView.findViewById(R.id.textView);
            imageView.setImageResource(mEncyclopediaEntries.get(position).getImage());
            textView.setText(mEncyclopediaEntries.get(position).getName());
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
