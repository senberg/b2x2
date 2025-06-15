package com.b2x2.core.persistence;

import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@NoArgsConstructor
public class ConfigurationValue extends BaseEntity {
    public String value;
}
