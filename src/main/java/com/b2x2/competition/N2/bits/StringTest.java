package com.b2x2.competition.N2.bits;

public class StringTest {
    public static void main(String[] args) {
        String a = "snake";
        String b = "snake";
        System.out.println(a == b);

        String c = new String("snake");
        String d = new String("snake");
        System.out.println(c == d);

        String e = "s" + "nake";
        System.out.println(a == e);
        System.out.println(c == e);



        // force put stuff in the cache
        // get the canonical object
        String common =
                (new String("people")).intern();
        String common2 =
                (new String("people")).intern();
        System.out.println(common == common2);
        common.hashCode();
    }
}
