package ru.geekware.gabbybaby;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class SettingsActivity extends Activity {

    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        PreferenceManager.setDefaultValues( this, R.xml.preferences, false );
        getFragmentManager().beginTransaction()
            .replace( android.R.id.content, new SettingsFragment() ).commit();
    }
}
