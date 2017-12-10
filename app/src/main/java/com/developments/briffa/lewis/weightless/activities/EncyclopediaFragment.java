package com.developments.briffa.lewis.weightless.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.developments.briffa.lewis.weightless.R;
import com.developments.briffa.lewis.weightless.models.PlanetEntry;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EncyclopediaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class EncyclopediaFragment extends Fragment {

    private GridView mGridView;
    private ArrayList<PlanetEntry> mPlanetEntries;

    private OnFragmentInteractionListener mListener;

    public EncyclopediaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View fragmentView = inflater.inflate(R.layout.fragment_encyclopedia, container, false);

        EncyclopediaActivity encyclopediaActivty = (EncyclopediaActivity) getActivity();
        mPlanetEntries = encyclopediaActivty.getList();

        mGridView = fragmentView.findViewById(R.id.gridview_encyclopedia);
        mGridView.setAdapter(new EncyclopediaFragment.ImageAdapter(getContext()));

        return fragmentView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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

    public class ImageAdapter extends BaseAdapter {

        public ImageAdapter(Context context) {

        }

        @Override
        public int getCount() {
            return 8;
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
//                    Intent encyclopediaDetailsIntent = new Intent(getContext(), EncyclopediaDetailsActivity.class);
//                    encyclopediaDetailsIntent.putExtra("NAME", mPlanetEntries.get(position).getName());
//                    encyclopediaDetailsIntent.putExtra("ID", mPlanetEntries.get(position).getPhoto());
//                    encyclopediaDetailsIntent.putExtra("DESCRIPTION", mPlanetEntries.get(position).getDescription());
//                    startActivity(encyclopediaDetailsIntent);
                    OnItemSelectionChangeListener onItemSelectionChangeListener = (OnItemSelectionChangeListener) getActivity();
                    onItemSelectionChangeListener.onItemSelectionChange(position);
                }
            });

            return itemView;
        }
    }
}
