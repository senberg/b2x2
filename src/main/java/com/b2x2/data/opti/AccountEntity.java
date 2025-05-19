package com.b2x2.data.opti;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import static lombok.AccessLevel.PROTECTED;

@Entity
@ToString
@SuperBuilder
@NoArgsConstructor(access = PROTECTED)
public class AccountEntity {
    @Id
    @GeneratedValue
    protected Long id;

    @Embedded
    AccountData data;
}
