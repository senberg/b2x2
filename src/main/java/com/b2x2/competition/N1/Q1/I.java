package com.b2x2.competition.N1.Q1;

public interface I {
    int x = 0;

    public default int c(){
        return x;
    }
}
