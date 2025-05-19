package com.b2x2.competition.N3;

import static java.lang.System.out;

public class Problems {
    static long veryLong = Long.parseLong("4000000004");

    public static void main(String[] args) {
        new Thread(() -> {
            veryLong = veryLong - Long.parseLong("2000000002");
        }).start();

        new Thread(() -> {
            veryLong = veryLong - Long.parseLong("1000000001");
        }).start();

        out.println(veryLong);
    }
}

