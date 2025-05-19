package com.b2x2.competition.N3;


// Good. Lombok handles synchronization
// correctly adding a private lock object.
public class Ten {
    public static void main(String[] args) {
        Object l1 = new Object();
        Object l2 = new Object();

        new Thread(() -> {
            synchronized (l1) {
                try {
                    Thread.sleep(3000);
                    synchronized (l2) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();


        new Thread(() -> {
            synchronized (l2) {
                try {
                    Thread.sleep(3000);
                    synchronized (l1) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}

