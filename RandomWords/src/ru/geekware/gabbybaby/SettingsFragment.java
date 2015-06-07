package ru.geekware.gabbybaby;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.text.TextUtils;

public class SettingsFragment extends PreferenceFragment
    implements SharedPreferences.OnSharedPreferenceChangeListener {

    private String _wordDisplayTimeMillisKey;
    private String _pauseBetweenWordsMillisKey;
    private String _wordsSequenceLengthKey;
    private String _dictionaryKey;

    private Settings _settings;

    @Override
    public void onCreate( Bundle savedInstanceState ) {

        super.onCreate( savedInstanceState );

        _settings = Settings.getInstance();

        final Resources resources = getResources();
        _wordDisplayTimeMillisKey = resources.getString(
            R.string.preferenceKey_wordDisplayTimeMillis );
        _pauseBetweenWordsMillisKey = resources.getString(
            R.string.preferenceKey_pauseBetweenWordsMillis );
        _wordsSequenceLengthKey = resources.getString(
            R.string.preferenceKey_wordsSequenceLength );
        _dictionaryKey = resources.getString(
            R.string.preferenceKey_dictionary );

        addPreferencesFromResource( R.xml.preferences );

        SharedPreferences sharedPreferences =
            getPreferenceScreen().getSharedPreferences();
        updateWordDisplayTimeMillisSummary( sharedPreferences );
        updatePauseBetweenWordsMilliSummary( sharedPreferences );
        updateWordsSequenceLengthSummary( sharedPreferences );
        updateDictionarySummary( sharedPreferences );
    }

    /**
     * Called when a shared preference is changed, added, or removed. This may
     * be called even if a preference is set to its existing value.
     *
     * <p>This callback will be run on your main thread.
     *
     * @param sharedPreferences The {@link SharedPreferences} that received the
     * change.
     * @param key The key of the preference that was changed, added, or
     */
    @Override
    public void onSharedPreferenceChanged(
        SharedPreferences sharedPreferences,
        String key
    ) {
        if ( key.equals( _wordDisplayTimeMillisKey ) ) {
            updateWordDisplayTimeMillisSummary( sharedPreferences );
        } else if ( key.equals( _pauseBetweenWordsMillisKey ) ) {
            updatePauseBetweenWordsMilliSummary( sharedPreferences );
        } else if ( key.equals( _wordsSequenceLengthKey ) ) {
            updateWordsSequenceLengthSummary( sharedPreferences );
        } else if ( key.equals( _dictionaryKey ) ) {
            updateDictionarySummary( sharedPreferences );
        }
    }

    /**
     * Called when the fragment is visible to the user and actively running.
     * This is generally tied to Activity.onResume of the containing Activity's
     * lifecycle.
     */
    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences()
            .registerOnSharedPreferenceChangeListener( this );
    }

    /**
     * Called when the Fragment is no longer resumed.  This is generally tied to
     * Activity.onPause of the containing Activity's lifecycle.
     */
    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences()
            .unregisterOnSharedPreferenceChangeListener( this );
    }

    private void updateWordDisplayTimeMillisSummary(
        SharedPreferences sharedPreferences
    ) {
        updateMillisecondsPreferenceSummary( sharedPreferences,
            _wordDisplayTimeMillisKey );
    }

    private void updatePauseBetweenWordsMilliSummary(
        SharedPreferences sharedPreferences
    ) {
        updateMillisecondsPreferenceSummary( sharedPreferences,
            _pauseBetweenWordsMillisKey );
    }

    private void updateWordsSequenceLengthSummary(
        SharedPreferences sharedPreferences
    ) {
        updateSimplePreferenceSummary( sharedPreferences,
            _wordsSequenceLengthKey );
    }

    private void updateDictionarySummary(
        SharedPreferences sharedPreferences
    ) {
        _settings.setDictionary(
            sharedPreferences.getString( _dictionaryKey, null ) );
        final String summary = TextUtils
            .join( getResources().getString( R.string.dictionarySeparator ),
                _settings.getDictionary() );
        findPreference( _dictionaryKey )
            .setSummary( summary );
    }

    private void updateMillisecondsPreferenceSummary(
        SharedPreferences sharedPreferences,
        String key
    ) {
        final String value = sharedPreferences.getString( key, null );
        final String summary = String.format( getResources().getString(
            R.string.numberOfMilliseconds ), value );
        findPreference( key ).setSummary( summary );
    }

    private void updateSimplePreferenceSummary(
        SharedPreferences sharedPreferences,
        String key
    ) {
        final String value = sharedPreferences.getString( key, null );
        findPreference( key ).setSummary( value );
    }
}
