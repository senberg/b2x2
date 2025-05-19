package com.b2x2.competition.N4;

import java.util.List;
import java.util.Set;

public class FasterExample {
    public List<Integer> findMultiples(List<Integer> numbers) {
        return numbers.stream()
                .filter(n1 -> numbers.stream().filter(n2 -> n1 == n2).count() > 1)
                .toList();
    }

    public List<String> hits(List<String> commands, List<String> input) {
        return input.stream().filter(commands::contains).toList();
    }

    public List<Integer> sort(Set<Integer> indata) {
        return indata.stream().sorted().toList();
    }
}
