package com.b2x2.competition.N2.bits;

public class Playtest {
    record Snake(int n) {}

    public static void main(String[] args) {
        int x = 5; // primitive
        Integer y = Integer.valueOf(5); // object

        Integer z = 5; // primitive being auto-boxed to object
        int q = z; // object being unboxed to primitive

        z.hashCode();


        Integer z4 = 87894984; // primitive being auto-boxed to object
        Integer z6 = 87894984;

        System.out.println(z4 == z6);

        Snake ss = new Snake(87894984);
        Snake s2 = new Snake(87894984);

        System.out.println(ss.equals(s2));
        System.out.println(ss == s2);
    }
}
