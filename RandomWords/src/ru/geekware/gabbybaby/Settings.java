package ru.geekware.gabbybaby;

import android.content.res.Resources;

public final class Settings {

    public static int wordDisplayTimeMillis;
    public static int pauseBetweenWordsMillis;
    public static int wordsSequenceLength;
    public static String[] dictionary;

    private Settings() {
    }

    public static void loadDefaults( Resources resources ) {
        wordDisplayTimeMillis = resources
            .getInteger( R.integer.preferenceDefault_wordDisplayTimeMillis );
        pauseBetweenWordsMillis = resources
            .getInteger( R.integer.preferenceDefault_pauseBetweenWordsMillis );
        wordsSequenceLength = resources
            .getInteger( R.integer.preferenceDefault_wordsSequenceLength );
        setDictionaryFromString(
            resources.getString( R.string.preferenceDefault_dictionary ) );
    }

    public static void setDictionaryFromString( String rawDictionary ) {
        if ( rawDictionary != null ) {
            dictionary = rawDictionary.split( "[,\\s]+" );
        }
    }
}
