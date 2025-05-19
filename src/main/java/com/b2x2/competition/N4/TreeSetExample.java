package com.b2x2.competition.N4;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.TreeSet;

@RequiredArgsConstructor
public class TreeSetExample {
    private final TreeSet<BigDecimal> data;

    public void add(BigDecimal e) {
        data.add(e);
    }

    public Boolean contains(BigDecimal e) {
        return data.contains(e);
    }

    public BigDecimal getMax() {
        return data.last();
    }
}
