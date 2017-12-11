package com.developments.briffa.lewis.weightless.managers;

import android.content.Context;
import android.content.res.TypedArray;

import com.developments.briffa.lewis.weightless.R;
import com.developments.briffa.lewis.weightless.models.EncyclopediaEntry;

import java.util.ArrayList;

/**
 * Class: Responsible for the management of an aggregation of encyclopedia entries. The encyclopedia
 * manager upon creation pulls its information from the strings.xml file.
 *
 * @author lewisbriffa
 * @version V1.3
 */
public class EncyclopediaManager {

    private ArrayList<EncyclopediaEntry> mEncyclopediaEntries;

    public EncyclopediaManager(Context context) {
        mEncyclopediaEntries = new ArrayList<>();

        String[] encyclopediaEntryNames =        context.getResources().getStringArray(R.array.encyclopedia_entry_names);
        String[] encyclopediaEntryDescriptions = context.getResources().getStringArray(R.array.encyclopedia_entry_descriptions);
        TypedArray encyclopediaEntryImages =     context.getResources().obtainTypedArray(R.array.encyclopedia_entry_images);

        int encyclopediaLength = encyclopediaEntryNames.length;

        for(int i = 0; i < encyclopediaLength; i++) {

            // provide default values in the case that the entries do not exist
            String name         = i < encyclopediaEntryNames.length ? encyclopediaEntryNames[i] : "Unknown";
            String description  = i < encyclopediaEntryDescriptions.length ? encyclopediaEntryDescriptions[i] : "Unknown";
            int image           = encyclopediaEntryImages.getResourceId(i, R.drawable.encyclopedia_no_image);

            mEncyclopediaEntries.add(new EncyclopediaEntry(name, image, description, 0));
        }
    }

    public EncyclopediaEntry getEntry(int id) {
        return mEncyclopediaEntries.get(id);
    }

    public ArrayList<EncyclopediaEntry> getList() {
        return mEncyclopediaEntries;
    }
}
