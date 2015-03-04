package ru.geekware.gabbybaby;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

// todo: правильно ли использовать Timer в данной ситуации?
// todo: анимация смены слов
// todo: разный цвет слогов
// todo: динамический размер текста
// todo: полный экран
// todo: логотип
// todo: название с пробелом или без?
// todo: Play Market?
// todo: вынести все в ресурсы и настройки
public class RandomWordsActivity extends Activity {

    private RandomWordsGenerator _wordGenerator;
    private Handler _displayWordsHandler;

    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        _wordGenerator = new RandomWordsGenerator( Config.defaultWords );
        _displayWordsHandler = new Handler();
        setContentView( R.layout.main );
    }

    @Override
    protected void onStart() {
        super.onStart();
        startWordsDemonstration();
    }

    private String[] GenerateWordsSequence() {
        int sequenceLength = Config.defaultWords.length;
        if ( sequenceLength > Config.maxWordsInSequence ) {
            sequenceLength = Config.maxWordsInSequence;
        }
        return _wordGenerator.GenerateWordsSequence( sequenceLength );
    }

    private void startWordsDemonstration() {
        ShowWordsTask _showWordsTask =
                new ShowWordsTask( _wordGenerator.GenerateWordsSequence( 5 ),
                        (TextView) findViewById( R.id.wordTextView ) );
    }

    private class DisplayWordRunnable implements Runnable {

        @Override
        public void run() {

        }
    }
}
