package com.b2x2.competition.N1.Q1;

public class Ex extends RuntimeException {
    public Ex(String message) {
        super(message);
    }

    public int x;

    public int c(){
        return x;
    }
}
