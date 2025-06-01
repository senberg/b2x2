package com.b2x2.core;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.Test;

public class XidTest {
    @Test
    public void constructor() {
        Xid xid = new Xid();

        assertTrue(xid.toString().matches("[A-Za-z0-9]{20}"));
    }

    @Test
    public void constructor_unique() {
        int count = 1000000;
        Set<String> strings = new HashSet<>();
        Set<Xid> xids = new HashSet<>();

        for (int i = 0; i < count; i++) {
            Xid xid = new Xid();
            strings.add(xid.toString());
            xids.add(xid);
        }

        assertEquals(count, strings.size());
        assertEquals(count, xids.size());
    }

    @Test
    public void fromString_min() {
        String string = Xid.MIN.toString();
        Xid xid = Xid.fromString(string);

        assertEquals(string, xid.toString());
    }

    @Test
    public void fromString() {
        String string = "JywUMQMMXmUCrPJi18hY";
        Xid xid = Xid.fromString(string);

        assertEquals(string, xid.toString());
    }

    @Test
    public void fromString_max() {
        String string = Xid.MAX.toString();
        Xid xid = Xid.fromString(string);

        assertEquals(string, xid.toString());
    }

    @Test
    public void fromString_UUID() {
        String string = "00030d60-2f35-8000-b9b6-3e194c266d02";
        Xid xid = Xid.fromString(string);
        assertEquals(UUID.fromString(string), xid.toUUID());
    }

    @Test
    public void createdAt_min() {
        Instant createdAt = Xid.MIN.getCreatedAt();

        assertEquals(Instant.parse("2025-01-01T00:00:00.000Z"), createdAt);
    }

    @Test
    public void createdAt_now() throws InterruptedException {
        Instant before = Instant.now();
        Thread.sleep(1);
        Xid xid = new Xid();
        Thread.sleep(1);
        Instant after = Instant.now();

        assertTrue(xid.getCreatedAt().isAfter(before));
        assertTrue(xid.getCreatedAt().isBefore(after));
    }

    @Test
    public void createdAt_max() {
        Instant createdAt = Xid.MAX.getCreatedAt();

        assertEquals(Instant.parse("2094-09-07T15:47:35.551Z"), createdAt);
    }

    @Test
    public void toUUID() {
        Xid xid = new Xid();
        UUID uuid = xid.toUUID();
        assertEquals(uuid.getMostSignificantBits(), xid.mostSigBits());
        assertEquals(uuid.getLeastSignificantBits(), xid.leastSigBits());
        assertEquals(0b1000, uuid.version());
        assertEquals(0b10, uuid.variant());
        assertThrows(UnsupportedOperationException.class, uuid::clockSequence);
        assertThrows(UnsupportedOperationException.class, uuid::timestamp);
    }

    @Test
    public void fromUUID() {
        String string = "00030d60-2f35-8000-b9b6-3e194c266d02";
        UUID uuid = UUID.fromString(string);
        Xid xid = Xid.fromUUID(uuid);
        assertEquals(uuid.getMostSignificantBits(), xid.mostSigBits());
        assertEquals(uuid.getLeastSignificantBits(), xid.leastSigBits());
        assertEquals(uuid, xid.toUUID());
    }
}
