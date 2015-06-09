package ru.geekware.gabbybaby;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

// todo: разный цвет слогов
// todo: полный экран
// todo: только горизонтальная ориентация
// todo: логотип
// todo: динамический размер текста
// todo: старт показа по правильному событию
// todo: старт и рестарт по действию пользователя
// todo: расположить кнопку настроек точно в углу
// todo: пояснения в диалогах редактирования настроек
// todo: кнопки инкремента и декремента для числовых настроек
// todo: проверки и предупреждения при изменении настроек
// todo: использовать quantity strings, где нужно
// todo: анимация смены слов?
// todo: название с пробелом или без?
// todo: Play Market?
// todo: интернационализация?
public class RandomWordsActivity extends Activity {

    private Settings _settings;
    private TextView _wordTextView;
    private WordsDemonstrator _wordsDemonstrator;

    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        Settings.provideContext( this );
        _settings = Settings.getInstance();
        setContentView( R.layout.main );
        _wordTextView = (TextView) findViewById( R.id.wordTextView );
        _wordsDemonstrator = new WordsDemonstrator( _wordTextView );
    }

    @Override
    protected void onStart() {
        super.onStart();
        startWordsDemonstration();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopWordsDemonstration();
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
        _wordsDemonstrator.setWords( WordsColorizer.Colorize(
            RandomWordsGenerator.GenerateWordsSequence() ) );
        _wordsDemonstrator.start();
    }

    private void stopWordsDemonstration() {
        _wordsDemonstrator.stop();
        _wordsDemonstrator.reset();
    }
}
