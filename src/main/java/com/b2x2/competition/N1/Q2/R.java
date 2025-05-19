package com.b2x2.competition.N1.Q2;

public class R {
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> System.out.println("Hello World"));
        Thread thread2 = new Thread(() -> System.out.println("Hello World"));
        Thread thread3 = new Thread(() -> System.out.println("Hello World"));

        thread1.run();
        thread2.run();
        thread3.run();
    }
}
