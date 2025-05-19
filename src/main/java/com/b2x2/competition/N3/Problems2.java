package com.b2x2.competition.N3;

import static java.lang.System.out;

public class Problems2 {
    static boolean stop = false;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            while (!stop) {
                out.println("Hello World");
            }
        }).start();

        Thread.sleep(5000);
        stop = true;
    }
}

