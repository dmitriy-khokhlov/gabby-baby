package ru.geekware.gabbybaby;

import android.widget.TextView;

import java.util.TimerTask;

/**
 * Задача показа слов по таймеру.
 */
public class ShowWordsTask extends TimerTask {

    private String[] _words;
    private int _nextIndex;
    private TextView _viewer;

    public ShowWordsTask( String[] words, TextView viewer ) {
        _words = words;
        _nextIndex = 0;
        _viewer = viewer;
    }

    @Override
    public void run() {
        if ( _nextIndex < _words.length ) {
            setViewerText( "" );
            try {
                // todo: нужна ли пауза с белым экраном?
                Thread.sleep( 300 );
            } catch ( InterruptedException e ) {
            }
            setViewerText( _words[ _nextIndex ] );
            _nextIndex++;
        } else {
            setViewerText( "" );
        }
    }

    private void setViewerText( final String text ) {
        _viewer.post( new Runnable() {
            public void run() {
                _viewer.setText( text );
            }
        } );
    }
}
