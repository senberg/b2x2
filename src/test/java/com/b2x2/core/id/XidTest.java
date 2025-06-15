package com.b2x2.core.id;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.Test;

public class XidTest {
    @Test
    public void create() {
        Xid xid = Xid.create();

        assertTrue(xid.toString().matches("[A-Za-z0-9]{22}"));
    }

    @Test
    public void create_unique() {
        int count = 10000;
        Set<String> strings = new HashSet<>();
        Set<Xid> xids = new HashSet<>();

        for (int i = 0; i < count; i++) {
            Xid xid = Xid.create();
            assertTrue(xid.toString().matches("[A-Za-z0-9]{22}"));
            strings.add(xid.toString());
            xids.add(xid);
        }

        assertEquals(count, strings.size());
        assertEquals(count, xids.size());
    }

    @Test
    public void fromString_max() {
        long mostSigBits = ~(1L << 12) >>> 1;
        long leastSigBits = ~(1L << 62);
        Xid xid = new Xid(mostSigBits, leastSigBits);

        assertEquals(Instant.parse("3058-10-26T03:46:06.999475711Z"), xid.getCreatedAt());
        assertEquals("AzL8n0Y58F55UfZOVH2ZO3", xid.toString());
    }

    @Test
    public void fromString() {
        String string = "0YhIl0zlP7p0080i2VD9l8";
        Xid xid = Xid.fromString(string);
        System.out.println(xid.getCreatedAt().toString());

        assertEquals(string, xid.toString());
    }

    @Test
    public void fromString_UUID() {
        String string = "019769e1-1e79-7b10-92c3-7e883813e2e5";
        Xid xid = Xid.fromString(string);
        assertEquals(UUID.fromString(string), xid.toUUID());
    }

    @Test
    public void createdAt_now() throws InterruptedException {
        Instant before = Instant.now();
        Thread.sleep(1);
        Xid xid = Xid.create();
        Thread.sleep(1);
        Instant after = Instant.now();

        assertTrue(xid.getCreatedAt().isAfter(before));
        assertTrue(xid.getCreatedAt().isBefore(after));
    }

    @Test
    public void toUUID() {
        Xid xid = Xid.create();
        UUID uuid = xid.toUUID();
        assertEquals(uuid.getMostSignificantBits(), xid.mostSigBits());
        assertEquals(uuid.getLeastSignificantBits(), xid.leastSigBits());
        assertEquals(7, uuid.version());
        assertEquals(2, uuid.variant());
        assertThrows(UnsupportedOperationException.class, uuid::clockSequence);
        assertThrows(UnsupportedOperationException.class, uuid::timestamp);
    }

    @Test
    public void fromUUID() {
        String string = "019769e1-1e79-7b10-92c3-7e883813e2e5";
        UUID uuid = UUID.fromString(string);
        Xid xid = Xid.fromUUID(uuid);
        assertEquals(uuid.getMostSignificantBits(), xid.mostSigBits());
        assertEquals(uuid.getLeastSignificantBits(), xid.leastSigBits());
        assertEquals(uuid, xid.toUUID());
    }
}
