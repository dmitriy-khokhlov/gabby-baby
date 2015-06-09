package ru.geekware.gabbybaby;

import android.content.res.Resources;

public final class WordsColorizer {

    private WordsColorizer() {
    }

    public static String[] Colorize( String[] words ) {
        String[] colorizedWords = new String[ words.length ];
        for ( int i = 0; i < words.length; i++ ) {
            colorizedWords[ i ] = Colorize( words[ i ] );
        }
        return colorizedWords;
    }

    public static String Colorize( String word ) {
        final Resources resources = Settings.getResources();
        final String[] syllablesColors =
            resources.getStringArray( R.array.syllablesColors );
        final String colorTextHtml =
            resources.getString( R.string.colorTextHtml );

        String colorizedWord = "";
        final String[] syllables = word.split( resources.getString(
            R.string.syllableParsingSeparatorRegexp ) );
        for ( int i = 0; i < syllables.length; i++ ) {
            colorizedWord += String.format(
                colorTextHtml, syllables[ i ], syllablesColors[ i % 2 ] );
        }

        return colorizedWord;
    }
}
