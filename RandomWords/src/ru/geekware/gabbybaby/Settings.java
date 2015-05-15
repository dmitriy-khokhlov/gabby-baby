package ru.geekware.gabbybaby;

import android.content.Context;

public final class Settings {

    public static String[] defaultWords =
        new String[] { "МАМА", "ПАПА", "БАБА", "ДЕДА", "ВАНЯ" };
    public static int wordDisplayTimeMillis;
    public static int pauseBetweenWordsMillis;
    public static int wordsSequenceLength;

    private Settings() {
    }

    public static void loadDefaults( Context context ) {
        wordDisplayTimeMillis = context.getResources()
            .getInteger( R.integer.preferenceDefault_wordDisplayTimeMillis );
        pauseBetweenWordsMillis = context.getResources()
            .getInteger( R.integer.preferenceDefault_pauseBetweenWordsMillis );
        wordsSequenceLength = context.getResources()
            .getInteger( R.integer.preferenceDefault_wordsSequenceLength );
    }
}
