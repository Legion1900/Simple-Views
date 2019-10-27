package com.legion1900.simpleviews.views.utils;

import java.util.Random;

public class MathUtils {

    private static Random rnd = new Random();

    private MathUtils() {}

    public static byte getRandomSign() {
        if (rnd.nextBoolean())
            return 1;
        else return -1;
    }

    public static int randomInt(int upperBound) {
        return rnd.nextInt(upperBound);
    }
}
