package ru.geekware.gabbybaby;

import android.os.Handler;
import android.text.Html;
import android.widget.TextView;

public class WordsDemonstrator {

    private String[] _words;
    private int _nextIndex;
    private boolean _isStopped = true;

    private Settings _settings;
    private TextView _textView;
    private Handler _handler;
    private DisplayWordRunnable _displayWordRunnable;
    private ClearWordRunnable _clearWordRunnable;

    public WordsDemonstrator( TextView viewer ) {
        _settings = Settings.getInstance();
        _textView = viewer;
        _handler = new Handler();
        _displayWordRunnable = new DisplayWordRunnable();
        _clearWordRunnable = new ClearWordRunnable();
        reset();
    }

    public String[] getWords() {
        return _words;
    }

    public void setWords( String[] words ) {
        if ( !_isStopped ) {
            throw new SetWordsWhileDemonstrating();
        }
        _words = words;
    }

    public void start() {
        if ( _isStopped ) {
            _isStopped = false;
            _displayWordRunnable.run();
        }
    }

    public void stop() {
        _isStopped = true;
        _handler.removeCallbacks( _displayWordRunnable );
        _handler.removeCallbacks( _clearWordRunnable );
        _handler.post( _clearWordRunnable );
    }

    public void reset() {
        _nextIndex = 0;
    }

    private void checkCompletion() {
        if ( !_isStopped
            && ( _nextIndex < 0 || _nextIndex >= _words.length ) ) {
            _isStopped = true;
        }
    }

    private class DisplayWordRunnable implements Runnable {

        @Override
        public void run() {
            checkCompletion();
            if ( !_isStopped ) {
                _textView.setText( Html.fromHtml( _words[ _nextIndex ] ) );
                _nextIndex++;
                _handler.postDelayed( _clearWordRunnable,
                    _settings.getWordDisplayTimeMillis() );
            }
        }
    }

    private class ClearWordRunnable implements Runnable {

        @Override
        public void run() {
            _textView.setText( "" );
            checkCompletion();
            if ( !_isStopped ) {
                _handler.postDelayed( _displayWordRunnable,
                    _settings.getPauseBetweenWordsMillis() );
            }
        }
    }

    public class SetWordsWhileDemonstrating extends RuntimeException {

        public SetWordsWhileDemonstrating() {
            super( "Can't change words while demonstrating them, call stop() "
                + "first" );
        }
    }
}
