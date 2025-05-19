package com.b2x2.competition.N3;

import java.util.Random;

import static java.lang.System.currentTimeMillis;

public class Concurrency {
    public static void main(String[] args) {
        // Create a new thread that will do something
        new Thread(() -> System.out.println("Ping")).start();

        // A daemon thread that will do something
        // until all non-daemon threads exits
        Thread backgroundPoller = new Thread(() -> {
            while (true) {
                System.out.println("Poll something");
            }
        });
        backgroundPoller.setDaemon(true);
        backgroundPoller.start();

        // Start something that implements Runnable
        new Thread(new TimePrinter()).start();

        // Start something that extends Thread
        new RandomPrinter().start();
    }

    static class TimePrinter implements Runnable {
        @Override
        public void run() {
            System.out.println("Time: " + currentTimeMillis());
        }
    }

    static class RandomPrinter extends Thread {
        @Override
        public void run() {
            System.out.println("Random: " + new Random().nextInt());
        }
    }
}

