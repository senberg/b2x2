package com.b2x2.competition.N2;

import lombok.Builder;

@Builder
public record Dog (
    int integer,
    Integer boxedInteger,
    long longInteger,
    Long boxedLongInteger,
    String string
) { }
