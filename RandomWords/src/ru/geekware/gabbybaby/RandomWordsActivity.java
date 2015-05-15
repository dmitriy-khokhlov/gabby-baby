package ru.geekware.gabbybaby;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

// todo: вынести все в ресурсы и настройки
// todo: перезапуск показа после выхода из настроек
// todo: старт показа по правильному событию
// todo: старт и рестарт по действию пользователя
// todo: анимация смены слов
// todo: разный цвет слогов
// todo: динамический размер текста
// todo: полный экран
// todo: логотип
// todo: только горизонтальная ориентация
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
        setContentView( R.layout.main );
        Settings.loadDefaults( this );
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
        if ( length > Settings.wordsSequenceLength ) {
            length = Settings.wordsSequenceLength;
        }
        return _wordGenerator.GenerateWordsSequence( length );
    }

    public void onSettingsButtonClick( View v ) {
        startActivity( new Intent( this, SettingsActivity.class ) );
    }

    @Override
    protected void onActivityResult(
        int requestCode, int resultCode, Intent data
    ) {
        ( (TextView) findViewById( R.id.wordTextView ) ).setText(
            "display = " + Settings.wordDisplayTimeMillis + "; pause = "
                + Settings.pauseBetweenWordsMillis );
    }
}
