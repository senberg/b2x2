package com.b2x2.competition.N1.Q8;

public class Q8 {
    public static void main(String[] args) {
        System.out.println(
                powerOf(2, 2)
        );
    }

    static int powerOf(int number, int power) {
        return number ^ power;
    }
}
