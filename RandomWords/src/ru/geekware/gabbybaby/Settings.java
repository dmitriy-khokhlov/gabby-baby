package ru.geekware.gabbybaby;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;

/**
 * Статический класс с глобальными конфигурационными параметрами.
 */
public final class Settings {

    private static final String WORD_DISPLAY_TIME_MILLIS_KEY =
            "wordDisplayTimeMillis";
    private static final String PAUSE_BETWEEN_WORDS_MILLIS_KEY =
            "pauseBetweenWordsMillis";

    public static String[] defaultWords =
            new String[] { "МАМА", "ПАПА", "БАБА", "ДЕДА", "ВАНЯ" };
    public static int wordDisplayTimeMillis;
    public static int pauseBetweenWordsMillis;
    public static int maxWordsSequenceLength = 5;

    private static Context _context;

    private Settings() {
    }

    public static void initialize( Context context ) {
        _context = context;
        PreferenceManager
                .setDefaultValues( _context, R.xml.preferences, false );
        refresh( _context );
    }

    private static void refresh( Context context ) {
        SharedPreferences preferences =
                PreferenceManager.getDefaultSharedPreferences( _context );

        wordDisplayTimeMillis =
                preferences.getInt( WORD_DISPLAY_TIME_MILLIS_KEY, 0 );
    }

    private static int getIntFromPreferences(
            SharedPreferences preferences, String key ) {
        String stringValue = preferences.getString( key, null );
        int intValue;
        try {
            intValue = Integer.parseInt( stringValue );
        } catch ( NumberFormatException exception ) {
            Preference preference = PreferenceManager.findPref intValue = 0;
        } return intValue;
    }
}
