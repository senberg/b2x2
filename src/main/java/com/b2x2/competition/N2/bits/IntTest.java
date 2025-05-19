package com.b2x2.competition.N2.bits;

public class IntTest {
    record Snake(int n) {}

    public static void main(String[] args) {
        Integer x6 = new Integer(6);
        Integer y6 = new Integer(6);
        System.out.println(x6 == y6);

        Integer x5 = 5;
        Integer y5 = 5;
        System.out.println(x5 == y5);

        Integer x999 = 999;
        Integer y999 = 999;
        System.out.println(x999 == y999);



        int u = 9898;
        Integer u2 = new Integer(9898);
        System.out.println(u == u2);

        Integer j = null;
        int qqq = j;
    }
}
