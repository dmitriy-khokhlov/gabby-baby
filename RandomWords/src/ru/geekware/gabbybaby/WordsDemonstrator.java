package ru.geekware.gabbybaby;

import android.os.Handler;
import android.widget.TextView;

/**
 * Задача показа слов по таймеру.
 */
public class WordsDemonstrator {

    private String[] _words;
    private int _nextIndex;
    private TextView _textView;
    private Handler _handler;
    private DisplayWordRunnable _displayWordRunnable;
    private ClearWordRunnable _clearWordRunnable;

    public WordsDemonstrator( String[] words, TextView viewer ) {
        _words = words;
        _nextIndex = 0;
        _textView = viewer;
        _handler = new Handler();
        _displayWordRunnable = new DisplayWordRunnable();
        _clearWordRunnable = new ClearWordRunnable();
    }

    public void startDemonstration() {
        _displayWordRunnable.run();
    }

    private class DisplayWordRunnable implements Runnable {

        @Override
        public void run() {
            if ( _nextIndex < _words.length ) {
                _textView.setText( _words[ _nextIndex ] );
                _nextIndex++;
                _handler.postDelayed( _clearWordRunnable,
                        Config.wordDisplayTimeMillis );
            }
        }
    }

    private class ClearWordRunnable implements Runnable {

        @Override
        public void run() {
            _textView.setText( "" );
            if ( _nextIndex < _words.length ) {
                _handler.postDelayed( _displayWordRunnable,
                        Config.pauseBetweenWordsMillis );
            }
        }
    }
}
