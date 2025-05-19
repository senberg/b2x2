package com.b2x2.competition.N1.Q5;

import java.util.stream.Stream;

public class Q5 {
    public static void main(String[] args) {
        Stream<Integer> numbers =
                Stream.of(1, 2, 3, 4, 5);

        numbers.distinct()
                .forEach(System.out::println);

        numbers.forEach(System.out::println);
    }
}
