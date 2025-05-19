package com.b2x2.client;

import java.util.Random;

public class Rng {
    private static final Random random = new Random();

    public static double randomPercentage() {
        return random.nextDouble(1);
    }

    public static boolean randomBoolean() {
        return random.nextBoolean();
    }

    public static double randomDouble(double origin, double bound) {
        return random.nextDouble(origin, bound);
    }
}
