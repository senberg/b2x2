package com.b2x2.competition.N4;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;

@RequiredArgsConstructor
public class ArrayListExample {
    private final ArrayList<BigDecimal> data;

    public BigDecimal getFirst() {
        return data.getFirst();
    }

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
