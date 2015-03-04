package ru.geekware.gabbybaby;

/**
 * Статический класс с глобальными конфигурационными параметрами.
 */
public class Config {

    public static String[] defaultWords =
            new String[] { "МАМА", "ПАПА", "БАБА", "ДЕДА", "ВАНЯ" };

    public static int wordDisplayTimeMillis = 3000;
    public static int pauseBetweenWordsMillis = 300;

    public static int maxWordsInSequence = 5;
}
