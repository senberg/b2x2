package com.b2x2.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class ToyEntity {
    @Id
    @GeneratedValue
    public Long id;
    public String name;
}
