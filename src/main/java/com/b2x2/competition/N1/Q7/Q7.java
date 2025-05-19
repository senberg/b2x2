package com.b2x2.competition.N1.Q7;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

public class Q7 {
    public static void method(int a, final int b) {
        int c = 5;
        final int d = 6;
        int e = 7;
        e = 8;
        AtomicInteger f = new AtomicInteger(9);
        final AtomicInteger g = new AtomicInteger(9);

        Supplier<Object> lambda = () -> {
            return a;
        };
    }
}
