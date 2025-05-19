package com.b2x2.competition.N3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.System.out;

public class PooledConcurrency {
    static ExecutorService threadExecutor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        for (int i = 0; i < 999; i++) {
            Runnable logTask = () -> out.println("Executing task");

            threadExecutor.submit(logTask);
        }
    }
}

