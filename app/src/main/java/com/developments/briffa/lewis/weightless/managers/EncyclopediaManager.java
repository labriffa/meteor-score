package com.developments.briffa.lewis.weightless.managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.preference.PreferenceManager;

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

        // get encyclopedia entry information from the strings xml file
        String[] encyclopediaEntryNames =        context.getResources().getStringArray(R.array.encyclopedia_entry_names);
        String[] encyclopediaEntryDescriptions = context.getResources().getStringArray(R.array.encyclopedia_entry_descriptions);
        TypedArray encyclopediaEntryImages =     context.getResources().obtainTypedArray(R.array.encyclopedia_entry_images);
        int[] encyclopediaEntryCosts =           context.getResources().getIntArray(R.array.encyclopedia_entry_costs);

        // user coins
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        int userCoins = sharedPreferences.getInt(context.getResources().getString(R.string.pref_user_coins_key), 0);

        int encyclopediaLength = encyclopediaEntryNames.length;

        for(int i = 0; i < encyclopediaLength; i++) {

            String name = encyclopediaEntryCosts[i] + " coins";
            String description = "This will become available when you collect an additional "
                    + (encyclopediaEntryCosts[i]-userCoins) + " coins";
            int image = R.drawable.locked_planet;

            if(userCoins >= encyclopediaEntryCosts[i]) {
                // provide default values in the case that the entries do not exist
                name         = i < encyclopediaEntryNames.length ? encyclopediaEntryNames[i] : "Unknown";
                description  = i < encyclopediaEntryDescriptions.length ? encyclopediaEntryDescriptions[i] : "Unknown";
                image           = encyclopediaEntryImages.getResourceId(i, R.drawable.encyclopedia_no_image);
            }

            mEncyclopediaEntries.add(new EncyclopediaEntry(name, image, description));
        }
    }

    public ArrayList<EncyclopediaEntry> getList() {
        return mEncyclopediaEntries;
    }
}
