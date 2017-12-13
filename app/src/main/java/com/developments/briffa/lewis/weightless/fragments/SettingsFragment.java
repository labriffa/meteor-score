package com.developments.briffa.lewis.weightless.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.v4.app.Fragment;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developments.briffa.lewis.weightless.R;

/**
 * Fragment: Responsible for displaying the fragment settings xml file.
 *
 * @author lewisbriffa
 * @version 1.3
 */
public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.pref_meteor_score);

        // get user preferences
        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
        PreferenceScreen preferenceScreen = getPreferenceScreen();

        // get the users current number of coins
        String value = String.valueOf(sharedPreferences.getInt(getResources().getString(R.string.pref_user_coins_key), 0));
        Preference preference = preferenceScreen.findPreference(getResources().getString(R.string.pref_user_coins_key));

        // set the number of coins as the summary
        setPreferenceSummary(preference, value);
    }

    /**
     * Sets the summary under a given preference
     *
     * @param preference
     * @param value
     */
    public void setPreferenceSummary(Preference preference, String value) {

        // need to find corresponding list entry based on value
        if(preference instanceof ListPreference) {
            ListPreference listPreference = (ListPreference) preference;
            int preferenceIndex = listPreference.findIndexOfValue(value);
            if(preferenceIndex >= 0) {
                listPreference.setSummary(listPreference.getEntries()[preferenceIndex]);
            }
        } else {
            preference.setSummary(value);
        }
    }
}
