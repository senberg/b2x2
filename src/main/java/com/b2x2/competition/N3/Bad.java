package com.b2x2.competition.N3;


// Very bad, using the synchronized keyword means it will lock
// on the monitor of this object. If another thread does the
// same things will go very bad.
public class Bad {
    private long counter;

    synchronized public void increment() {
        counter = counter + 1;
    }
}

