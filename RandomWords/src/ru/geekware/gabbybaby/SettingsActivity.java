package ru.geekware.gabbybaby;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class SettingsActivity extends Activity
        implements OnSharedPreferenceChangeListener {

    private static final String WORD_DISPLAY_TIME_MILLIS_KEY =
            "wordDisplayTimeMillis";

    private static final String PAUSE_BETWEEN_WORDS_MILLIS_KEY =
            "pauseBetweenWordsMillis";

    private void loadSettingsDefaults() {
        Settings.wordDisplayTimeMillis = getResources().getInteger(
                R.integer.preferenceDefault_wordDisplayTimeMillis );
        Settings.pauseBetweenWordsMillis = getResources().getInteger(
                R.integer.preferenceDefault_pauseBetweenWordsMillis );
    }

    private void refreshSettings() {
        SharedPreferences preferences =
                PreferenceManager.getDefaultSharedPreferences( this );
        try {
            Settings.wordDisplayTimeMillis = Integer.parseInt( preferences
                    .getString( WORD_DISPLAY_TIME_MILLIS_KEY, null ) );
        } catch ( NumberFormatException exception ) {
        }
        try {
            Settings.pauseBetweenWordsMillis = Integer.parseInt( preferences
                    .getString( PAUSE_BETWEEN_WORDS_MILLIS_KEY, null ) );
        } catch ( NumberFormatException exception ) {
        }
    }

    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        PreferenceManager.setDefaultValues( this, R.xml.preferences, false );
        getFragmentManager().beginTransaction()
                .replace( android.R.id.content, new SettingsFragment() )
                .commit();
        loadSettingsDefaults();
        refreshSettings();
    }

    @Override
    protected void onResume() {
        super.onResume();
        PreferenceManager.getDefaultSharedPreferences( this )
                .registerOnSharedPreferenceChangeListener( this );
    }

    @Override
    protected void onPause() {
        super.onPause();
        PreferenceManager.getDefaultSharedPreferences( this )
                .unregisterOnSharedPreferenceChangeListener( this );
    }

    @Override
    public void onSharedPreferenceChanged(
            SharedPreferences sharedPreferences, String key ) {
        refreshSettings();
    }
}
