package com.b2x2.core.id;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.UUID;

@Converter(autoApply = true)
public class XidUuidConverter implements AttributeConverter<Xid, UUID> {
    @Override
    public UUID convertToDatabaseColumn(Xid attribute) {
        return attribute.toUUID();
    }

    @Override
    public Xid convertToEntityAttribute(UUID dbData) {
        return Xid.fromUUID(dbData);
    }
}
