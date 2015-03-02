package ru.geekware.gabbybaby;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Генератор случайной последовательности слов.
 *
 * @author Dmitry Khokhlov
 */
public class RandomWordsGenerator {

    private String[] _dictionary;

    public RandomWordsGenerator( String[] dictionary ) {
        _dictionary = dictionary;
    }

    /**
     * Генерирует случайную последовательность слов заданной длины без
     * повторений.
     *
     * @param length длина генерируемой последовательности
     * @return сгенерированная последовательность
     * @throws java.lang.IllegalArgumentException если длина последовательности
     * меньше 1 или больше длины словаря
     */
    public String[] GenerateWordSequence( int length ) {

        if ( length < 1 || length > _dictionary.length ) {
            throw new IllegalArgumentException(
                    "Parameter length must be in [1; dictionary.length]" );
        }

        Random random = new Random();
        ArrayList<String> source =
                new ArrayList<String>( Arrays.asList( _dictionary ) );
        String[] sequence = new String[ length ];

        for ( int index = 0; index < length; index++ ) {
            int randomIndex = random.nextInt( length - index );
            sequence[ index ] = source.remove( randomIndex );
        }

        return sequence;
    }
}
