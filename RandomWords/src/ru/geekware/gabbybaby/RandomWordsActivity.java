package ru.geekware.gabbybaby;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

// todo: старт показа по правильному событию
// todo: старт и рестарт по действию пользователя
// todo: анимация смены слов
// todo: разный цвет слогов
// todo: динамический размер текста
// todo: полный экран
// todo: логотип
// todo: только горизонтальная ориентация
// todo: проверка и предупреждения при изменении настроек
// todo: название с пробелом или без?
// todo: Play Market?
// todo: интернационализация?
public class RandomWordsActivity extends Activity {

    private Settings _settings;
    private RandomWordsGenerator _wordGenerator;
    private TextView _wordTextView;
    private WordsDemonstrator _wordsDemonstrator;

    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        Settings.provideContext( this );
        _settings = Settings.getInstance();
        setContentView( R.layout.main );
        _wordTextView = (TextView) findViewById( R.id.wordTextView );
        _wordGenerator = new RandomWordsGenerator();
        _wordsDemonstrator = new WordsDemonstrator( _wordTextView );
    }

    @Override
    protected void onStart() {
        super.onStart();
        startWordsDemonstration();
    }

    public void onSettingsButtonClick( View v ) {
        stopWordsDemonstration();
        startActivityForResult( new Intent( this, SettingsActivity.class ), 0 );
    }

    @Override
    protected void onActivityResult(
        int requestCode, int resultCode, Intent data
    ) {
        _settings.refresh();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopWordsDemonstration();
    }

    private void startWordsDemonstration() {
        //stopWordsDemonstration();
        _wordsDemonstrator.setWords( _wordGenerator.GenerateWordsSequence() );
        _wordsDemonstrator.start();
    }

    private void stopWordsDemonstration() {
        _wordsDemonstrator.stop();
        _wordsDemonstrator.reset();
    }
}
