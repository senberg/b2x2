package com.b2x2.competition.N3;

import static java.lang.System.out;

public class AgeCheck {
    int age;

    public boolean check() {
        return age >= 18;
    }

    public static void main(String[] args) throws InterruptedException {
        AgeCheck ageCheck = new AgeCheck();

        new Thread(() -> {
            ageCheck.age = 18;
            out.println(ageCheck.age + " " + ageCheck.check());
        }).start();

        new Thread(() -> {
            ageCheck.age = 12;
            out.println(ageCheck.age + " " + ageCheck.check());
        }).start();
    }
}

