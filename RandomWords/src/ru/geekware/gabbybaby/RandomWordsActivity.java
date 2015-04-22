package ru.geekware.gabbybaby;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

// todo: вынести все в ресурсы и настройки
// todo: старт показа по правильному событию
// todo: старт и рестарт по действию пользователя
// todo: анимация смены слов
// todo: разный цвет слогов
// todo: динамический размер текста
// todo: полный экран
// todo: логотип
// todo: название с пробелом или без?
// todo: Play Market?
// todo: интернационализация?
public class RandomWordsActivity extends Activity {

    private RandomWordsGenerator _wordGenerator;
    private TextView _wordTextView;
    private WordsDemonstrator _wordsDemonstrator;

    @Override
    public void onCreate( Bundle savedInstanceState ) {

        super.onCreate( savedInstanceState );

        Settings.initialize( this );
        setContentView( R.layout.main );

        _wordGenerator = new RandomWordsGenerator( Settings.defaultWords );
        _wordsDemonstrator = new WordsDemonstrator( GenerateWordsSequence(),
                (TextView) findViewById( R.id.wordTextView ) );
    }

    @Override
    protected void onStart() {
        super.onStart();
        _wordsDemonstrator.startDemonstration();
    }

    private String[] GenerateWordsSequence() {
        int length = Settings.defaultWords.length;
        if ( length > Settings.maxWordsSequenceLength ) {
            length = Settings.maxWordsSequenceLength;
        }
        return _wordGenerator.GenerateWordsSequence( length );
    }
}
