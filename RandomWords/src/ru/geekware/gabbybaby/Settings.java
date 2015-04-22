package ru.geekware.gabbybaby;

public final class Settings {

    public static String[] defaultWords =
            new String[] { "МАМА", "ПАПА", "БАБА", "ДЕДА", "ВАНЯ" };
    public static int wordDisplayTimeMillis;
    public static int pauseBetweenWordsMillis;
    public static int maxWordsSequenceLength = 5;

    private Settings() {
    }
}
