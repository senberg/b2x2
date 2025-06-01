package com.b2x2.core;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class Base62Test {
    @Test
    public void encode_negative_value() {
        assertThrows(IllegalArgumentException.class, () -> Base62.encode(-1, "".toCharArray(), 0, 0));
    }

    @Test
    public void encode_buffer_null() {
        assertThrows(IllegalArgumentException.class, () -> Base62.encode(0, null, 0, 0));
    }

    @Test
    public void encode_negative_offset() {
        assertThrows(IllegalArgumentException.class, () -> Base62.encode(0, "".toCharArray(), -1, 0));
    }

    @Test
    public void encode_buffer_too_short() {
        assertThrows(IllegalArgumentException.class, () -> Base62.encode(0, "".toCharArray(), 0, 1));
    }

    @Test
    public void encode_0() {
        char[] buffer = new char[20];
        Base62.encode(0, buffer, 0, 9);
        Base62.encode(0, buffer, 9, 11);

        assertEquals("XXXXXXXXXXXXXXXXXXXX", new String(buffer));
    }

    @Test
    public void encode_1() {
        char[] buffer = new char[20];
        Base62.encode(1, buffer, 0, 9);
        Base62.encode(1, buffer, 9, 11);

        assertEquals("YXXXXXXXXYXXXXXXXXXX", new String(buffer));
    }

    @Test
    public void encode_61() {
        char[] buffer = new char[20];
        Base62.encode(61, buffer, 0, 9);
        Base62.encode(61, buffer, 9, 11);

        assertEquals("9XXXXXXXX9XXXXXXXXXX", new String(buffer));
    }

    @Test
    public void encode_3843() {
        char[] buffer = new char[20];
        Base62.encode(3843, buffer, 0, 9);
        Base62.encode(3843, buffer, 9, 11);

        assertEquals("99XXXXXXX99XXXXXXXXX", new String(buffer));
    }

    @Test
    public void encode_238327() {
        char[] buffer = new char[20];
        Base62.encode(238327, buffer, 0, 9);
        Base62.encode(238327, buffer, 9, 11);

        assertEquals("999XXXXXX999XXXXXXXX", new String(buffer));
    }

    @Test
    public void encode_max() {
        char[] buffer = new char[11];
        Base62.encode(Long.MAX_VALUE, buffer, 0, 11);

        assertEquals("EwFCiXxFS9H", new String(buffer));
    }

    @Test
    public void encode_unique() {
        long count = 1000000;
        Set<String> set = new HashSet<>();

        for (int i = 0; i < count; i++) {
            char[] buffer = new char[11];
            Base62.encode(i, buffer, 0, 11);
            set.add(new String(buffer));
        }

        assertEquals(count, set.size());
    }

    @Test
    public void decode_null() {
        assertThrows(IllegalArgumentException.class, () -> Base62.decode(null, 0, 1));
    }

    @Test
    public void decode_negative_offset() {
        assertThrows(IllegalArgumentException.class, () -> Base62.decode("A".toCharArray(), -1, 1));
    }

    @Test
    public void decode_negative_count() {
        assertThrows(IllegalArgumentException.class, () -> Base62.decode("A".toCharArray(), 0, -1));
    }

    @Test
    public void decode_zero_count() {
        assertThrows(IllegalArgumentException.class, () -> Base62.decode("A".toCharArray(), 0, 0));
    }

    @Test
    public void decode_0_filled() {
        long result11 = Base62.decode("XXXXXXXXXXX".toCharArray(), 0, 11);

        assertEquals(0L, result11);
    }

    @Test
    public void decode_1() {
        long result11 = Base62.decode("Y".toCharArray(), 0, 1);

        assertEquals(1L, result11);
    }

    @Test
    public void decode_1_filled() {
        long result11 = Base62.decode("YXXXXXXXXXXX".toCharArray(), 0, 11);

        assertEquals(1L, result11);
    }

    @Test
    public void encode_decode_alot() {
        Set<String> encodings = new HashSet<>();
        Long max = 1000000L;

        for (long i = 0; i < max; i++) {
            char[] buffer = new char[11];
            Base62.encode(i, buffer, 0, 11);
            String encoding = new String(buffer);
            assertTrue(encodings.add(encoding));
        }

        assertEquals(max, encodings.size());
    }

    @Test
    public void encode_decode() {
        long value = 999999999999999999L;
        char[] buffer = new char[12];
        Base62.encode(value, buffer, 0, 12);
        String encoded = new String(buffer);
        long decoded = Base62.decode(encoded.toCharArray(), 0, 12);
        assertEquals(value, decoded);
    }

    @Test
    public void decode_max() {
        long result = Base62.decode("EwFCiXxFS9H".toCharArray(), 0, 11);

        assertEquals(Long.MAX_VALUE, result);
    }

    @Test
    public void encode_decode_max() {
        long highBits = -1L >>> 11;
        long lowBits = -1L >>> 2;

        char[] buffer = new char[20];
        Base62.encode(highBits, buffer, 0, 9);
        Base62.encode(lowBits, buffer, 9, 11);
        String encodedHigh = new String(buffer).substring(0, 9);
        String encodedLow = new String(buffer).substring(9);

        long decodedHigh = Base62.decode(encodedHigh.toCharArray(), 0, encodedHigh.length());
        long decodedLow = Base62.decode(encodedLow.toCharArray(), 0, 11);

        assertEquals(highBits, decodedHigh);
        assertEquals(lowBits, decodedLow);
    }
}
