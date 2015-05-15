package ru.geekware.gabbybaby;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class SettingsActivity extends Activity
    implements OnSharedPreferenceChangeListener {

    private String getStringPreference( int preferenceKeyResourceId ) {
        return PreferenceManager.getDefaultSharedPreferences( this )
            .getString( getResources().getString( preferenceKeyResourceId ),
                null );
    }

    private int getIntPreference( int preferenceKeyResourceId )
        throws NumberFormatException {
        return Integer
            .parseInt( getStringPreference( preferenceKeyResourceId ) );
    }

    private void refreshSettings() {
        try {
            Settings.wordDisplayTimeMillis = getIntPreference(
                R.string.preferenceKey_wordDisplayTimeMillis );
        } catch ( NumberFormatException exception ) {
            // Обработка исключения не нужна, т.к. значение поля в Settings не
            // изменится - это и есть желаемое поведение.
        }
        try {
            Settings.pauseBetweenWordsMillis = getIntPreference(
                R.string.preferenceKey_pauseBetweenWordsMillis );
        } catch ( NumberFormatException exception ) {
            // Обработка исключения не нужна, т.к. значение поля в Settings не
            // изменится - это и есть желаемое поведение.
        }
        try {
            Settings.wordsSequenceLength =
                getIntPreference( R.string.preferenceKey_wordsSequenceLength );
        } catch ( NumberFormatException exception ) {
            // Обработка исключения не нужна, т.к. значение поля в Settings не
            // изменится - это и есть желаемое поведение.
        }
        Settings.setDictionaryFromString(
            getStringPreference( R.string.preferenceKey_dictionary ) );
    }

    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        PreferenceManager.setDefaultValues( this, R.xml.preferences, false );
        getFragmentManager().beginTransaction()
            .replace( android.R.id.content, new SettingsFragment() ).commit();
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
        SharedPreferences sharedPreferences, String key
    ) {
        refreshSettings();
    }
}
