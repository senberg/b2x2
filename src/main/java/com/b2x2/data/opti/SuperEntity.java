package com.b2x2.data.opti;

import jakarta.persistence.Embedded;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
abstract class SuperEntity<T> {
    @Id
    @GeneratedValue
    protected Long id;

    @Embedded
    T data;
}
