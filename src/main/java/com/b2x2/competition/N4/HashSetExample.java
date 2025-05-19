package com.b2x2.competition.N4;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;

@RequiredArgsConstructor
public class HashSetExample {
    private final HashSet<BigDecimal> data;

    public void add(BigDecimal e) {
        data.add(e);
    }

    public Boolean contains(BigDecimal e) {
        return data.contains(e);
    }

    public BigDecimal getMax() {
        return Collections.max(data);
    }
}
