package ru.geekware.gabbybaby;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Генератор случайной последовательности слов.
 */
public final class RandomWordsGenerator {

    private RandomWordsGenerator() {
    }

    /**
     * Генерирует случайную последовательность слов заданной длины с
     * минимальными повторениями.
     *
     * @return сгенерированная последовательность
     */
    public static String[] GenerateWordsSequence() {

        final Settings _settings = Settings.getInstance();
        int sequenceLength = _settings.getWordsSequenceLength();
        String[] dictionary = _settings.getDictionary();

        Random random = new Random();
        ArrayList<String> source = new ArrayList<String>();
        String[] sequence = new String[ sequenceLength ];

        String lastWord = null;
        for ( int sequenceIndex = 0; sequenceIndex < sequenceLength;
            sequenceIndex++
            ) {

            if ( source.isEmpty() ) {
                source.addAll( Arrays.asList( dictionary ) );
                if ( sequenceIndex > 0 ) {
                    // Если использовали все слова из словаря и нужно
                    // генерировать еще, запомнить последнее слово, чтобы
                    // избежать повторения двух одинаковых слов подряд.
                    lastWord = sequence[ sequenceIndex - 1 ];
                }
            }

            int sourceIndex;
            do {
                sourceIndex = random.nextInt( source.size() );
            } while ( lastWord != null && source.get( sourceIndex )
                .equals( lastWord ) );
            lastWord = null;

            sequence[ sequenceIndex ] = source.remove( sourceIndex );
        }

        return sequence;
    }
}
