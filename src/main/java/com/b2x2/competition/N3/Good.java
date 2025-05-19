package com.b2x2.competition.N3;


import lombok.Synchronized;

// Good. Lombok handles synchronization
// correctly adding a private lock object.
public class Good {
    private long counter;

    @Synchronized
    public void increment() {
        counter = counter + 1;
    }
}

