package com.legion1900.simpleviews.views.utils;

import java.util.Random;

class RandomUtils {

    private static Random rnd = new Random();

    private RandomUtils() {}



    static int randomInt(int upperBound) {
        return rnd.nextInt(upperBound);
    }

    static int randomInt(int lowerBound, int upperBound) {
        upperBound -= lowerBound;
        return rnd.nextInt(upperBound) + lowerBound;
    }
}
