package ru.geekware.gabbybaby;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Timer;

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
    private ShowWordsTask _showWordsTask;
    private Timer _showWordsTimer;

    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        _wordGenerator = new RandomWordsGenerator(
                new String[] { "МАМА", "ПАПА", "БАБА", "ДЕДА", "ВАНЯ", } );
        setContentView( R.layout.main );
    }

    @Override
    protected void onStart() {
        super.onStart();
        startWordsDemonstration();
    }

    private void startWordsDemonstration() {
        _showWordsTask =
                new ShowWordsTask( _wordGenerator.GenerateWordSequence( 5 ),
                        (TextView) findViewById( R.id.wordTextView ) );
        _showWordsTimer = new Timer();
        // todo: stop timer after all words shown
        _showWordsTimer.schedule( _showWordsTask, 0, 2000 );
    }
}
