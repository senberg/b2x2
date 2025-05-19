package com.b2x2.competition.N3;

public class ThreadStuff {
    public static void main(String[] args) throws InterruptedException {
        Thread.currentThread().setName("The best thread");
        Thread.currentThread().setPriority(999);
        Thread.currentThread().setDaemon(false);

        Thread.activeCount();
        Thread.getAllStackTraces();

        Thread.sleep(1000);
        Thread.yield();
    }
}

