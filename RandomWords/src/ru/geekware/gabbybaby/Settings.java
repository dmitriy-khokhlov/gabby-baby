package ru.geekware.gabbybaby;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;

public class Settings {

    private static Resources _resources;
    private static SharedPreferences _preferences;
    private static Settings _instance;

    /**
     * This method should be called before any other usage of this class.
     */
    public static void provideContext( Context context ) {
        _resources = context.getResources();
        _preferences = PreferenceManager.getDefaultSharedPreferences( context );
    }

    public static Settings getInstance() {
        if ( _instance == null ) {
            if ( _resources == null || _preferences == null ) {
                throw new ContextNotProvidedException();
            }
            _instance = new Settings();
        }
        return _instance;
    }

    public static Resources getResources() {
        return _resources;
    }

    private int _wordDisplayTimeMillis;
    private int _pauseBetweenWordsMillis;
    private int _wordsSequenceLength;
    private String[] _dictionary;

    private Settings() {
        loadDefaults();
        refresh();
    }

    public int getWordDisplayTimeMillis() {
        return _wordDisplayTimeMillis;
    }

    public void setWordDisplayTimeMillis( int wordDisplayTimeMillis ) {
        _wordDisplayTimeMillis = wordDisplayTimeMillis;
    }

    public int getPauseBetweenWordsMillis() {
        return _pauseBetweenWordsMillis;
    }

    public void setPauseBetweenWordsMillis( int pauseBetweenWordsMillis ) {
        _pauseBetweenWordsMillis = pauseBetweenWordsMillis;
    }

    public int getWordsSequenceLength() {
        return _wordsSequenceLength;
    }

    public void setWordsSequenceLength( int wordsSequenceLength ) {
        _wordsSequenceLength = wordsSequenceLength;
    }

    public String[] getDictionary() {
        return _dictionary;
    }

    public void setDictionary( String[] dictionary ) {
        _dictionary = dictionary;
    }

    public void setDictionary( String rawDictionary ) {
        if ( rawDictionary != null ) {
            setDictionary( rawDictionary.split(
                _resources
                    .getString( R.string.wordsParsingSeparatorRegexp ) ) );
        }
    }

    public void refresh() {
        try {
            setWordDisplayTimeMillis( getIntPreference(
                R.string.preferenceKey_wordDisplayTimeMillis ) );
        } catch ( NumberFormatException exception ) {
            // Обработка исключения не нужна, т.к. значение поля в Settings не
            // изменится - это и есть желаемое поведение.
        }
        try {
            setPauseBetweenWordsMillis( getIntPreference(
                R.string.preferenceKey_pauseBetweenWordsMillis ) );
        } catch ( NumberFormatException exception ) {
            // Обработка исключения не нужна, т.к. значение поля в Settings не
            // изменится - это и есть желаемое поведение.
        }
        try {
            setWordsSequenceLength( getIntPreference(
                R.string.preferenceKey_wordsSequenceLength ) );
        } catch ( NumberFormatException exception ) {
            // Обработка исключения не нужна, т.к. значение поля в Settings не
            // изменится - это и есть желаемое поведение.
        }
        setDictionary(
            getStringPreference( R.string.preferenceKey_dictionary ) );
    }

    private void loadDefaults() {
        setWordDisplayTimeMillis( _resources
            .getInteger( R.integer.preferenceDefault_wordDisplayTimeMillis ) );
        setPauseBetweenWordsMillis( _resources.getInteger(
            R.integer.preferenceDefault_pauseBetweenWordsMillis ) );
        setWordsSequenceLength( _resources
            .getInteger( R.integer.preferenceDefault_wordsSequenceLength ) );
        setDictionary(
            _resources.getString( R.string.preferenceDefault_dictionary ) );
    }

    private String getStringPreference( int preferenceKeyResourceId ) {
        return _preferences
            .getString( _resources.getString( preferenceKeyResourceId ), null );
    }

    private int getIntPreference( int preferenceKeyResourceId )
        throws NumberFormatException {
        return Integer
            .parseInt( getStringPreference( preferenceKeyResourceId ) );
    }

    public static class ContextNotProvidedException extends RuntimeException {

        public ContextNotProvidedException() {
            super( "Call provideContext() first" );
        }
    }
}
