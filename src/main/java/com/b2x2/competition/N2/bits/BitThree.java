package com.b2x2.competition.N2.bits;

import org.openjdk.jol.info.ClassLayout;

public class BitThree {
    static class Year {
        int year;

        public Year(int year) {
            this.year = year;
        }
    }

    public static void main(String[] args) {
        Year birthYear = new Year(1983);
        System.out.println(birthYear.hashCode());

        System.out.println(
                ClassLayout.parseInstance(birthYear).toPrintable()
        );
    }
}
