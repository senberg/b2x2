package com.b2x2.core.id;

import static java.lang.String.format;

import java.util.Arrays;

public class Base62 {
    private static final char[] BYTE_TO_CHAR = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final byte[] CHAR_TO_BYTE = new byte['z' + 1];

    static {
        for (byte i = 0; i < BYTE_TO_CHAR.length; i++) {
            CHAR_TO_BYTE[BYTE_TO_CHAR[i]] = i;
        }
    }

    public static String encode(long value) {
        if (value < 0) {
            throw new IllegalArgumentException(format("Value '%s' is negative", value));
        }

        char[] buffer = new char[11];

        for (int i = 10; i >= 0; i--) {
            buffer[i] = BYTE_TO_CHAR[(byte) (value % 62)];
            value /= 62;
        }

        return new String(buffer);
    }

    public static void encode(long value, char[] buffer, int offset, int count) {
        if (value < 0 || buffer == null || offset < 0 || count < 0 || buffer.length < offset + count) {
            throw new IllegalArgumentException(
                    format("value %s, chars %s, offset %s, count %s", value, Arrays.toString(buffer), offset, count));
        }

        for (int i = offset + count - 1; i >= offset; i--) {
            buffer[i] = BYTE_TO_CHAR[(byte) (value % 62)];
            value /= 62;
        }
    }

    public static long decode(char[] buffer, int offset, int count) {
        if (buffer == null || offset < 0 || count <= 0 || buffer.length < offset + count) {
            throw new IllegalArgumentException(format("buffer %s, offset %s, count %s", Arrays.toString(buffer), offset, count));
        }

        long result = 0;
        long multiplier = 1;

        for (int i = offset + count - 1; i >= offset; i--) {
            result += multiplier * CHAR_TO_BYTE[buffer[i]];
            multiplier *= 62;
        }

        return result;
    }
}
