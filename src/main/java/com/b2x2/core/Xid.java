package com.b2x2.core;

import static com.b2x2.core.BitConstants.BIT_MASK_LONG_12;
import static com.b2x2.core.BitConstants.BIT_MASK_LONG_62;
import static java.lang.String.format;

import jakarta.annotation.Nonnull;
import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;


/**
 * 128 bit UUIDv8 based on time, a counter and randomness. Uses 115 bits of unique data.
 * <p>
 * bit 0-6. 7 unused bits always set to 0b00000000.
 * bit 7-47. 41 bit millisecond time since 2025-01-01 giving a max of 2094-09-07.
 * bit 48-51. 4 bits always set to 0b1000.
 * bit 52-63. 12 bit counter to improve the chance of multiple ids generated the same ms to be in the correct order.
 * bit 64-65. 2 bits always set to 0b10.
 * bit 66-127. 62 bits generated randomly. Gives 1% risk of collision if 2^28 ids are generated at the same time+counter.
 * </p>
 */
public record Xid(long mostSigBits, long leastSigBits) {
    private static final Pattern UUID_PATTERN = Pattern.compile("^[A-Za-z0-9]{8}-([A-Za-z0-9]{4}-){3}[A-Za-z0-9]{12}$");
    private static final Pattern XID_PATTERN = Pattern.compile("^[A-Za-p][A-Za-z0-9]{19}$");
    private static final long TIME_ADJUSTMENT = 1735689600000L;
    private static final long FIXED_HIGH_BITS_MASK = (0b1111111L << 57) | (0b1111L << 12);
    private static final long ALWAYS_SET_HIGH_BITS = 1L << 15;
    private static final long FIXED_LOW_BITS_MASK = 0b11L << 62;
    private static final long ALWAYS_SET_LOW_BITS = 1L << 63;
    public static final Xid MIN = new Xid(ALWAYS_SET_HIGH_BITS, ALWAYS_SET_LOW_BITS);
    public static final Xid MAX = new Xid(ALWAYS_SET_HIGH_BITS | ~FIXED_HIGH_BITS_MASK, ALWAYS_SET_LOW_BITS | ~FIXED_LOW_BITS_MASK);
    private static final AtomicInteger counter = new AtomicInteger();

    public Xid {
        if (((mostSigBits & FIXED_HIGH_BITS_MASK) != ALWAYS_SET_HIGH_BITS) || ((leastSigBits & FIXED_LOW_BITS_MASK)
                != ALWAYS_SET_LOW_BITS)) {
            throw new IllegalArgumentException(format("mostSigBits %s, leastSigBits %s", mostSigBits, leastSigBits));
        }
    }

    public Xid() {
        this(
                getMostSigBits(System.currentTimeMillis() - TIME_ADJUSTMENT, counter.getAndIncrement() % 4096),
                getLeastSigBits(ThreadLocalRandom.current().nextLong() >>> 2));
    }

    public Instant getCreatedAt() {
        return Instant.ofEpochMilli((mostSigBits >>> 16) + TIME_ADJUSTMENT);
    }

    public UUID toUUID() {
        return new UUID(mostSigBits, leastSigBits);
    }

    public static Xid fromUUID(UUID uuid) {
        return new Xid(uuid.getMostSignificantBits(), uuid.getLeastSignificantBits());
    }

    @Override
    public @Nonnull String toString() {
        char[] buffer = new char[20];
        long high = ((mostSigBits >>> 16) << 12) | (mostSigBits & BIT_MASK_LONG_12);
        Base62.encode(high, buffer, 0, 9);
        long low = leastSigBits & BIT_MASK_LONG_62;
        Base62.encode(low, buffer, 9, 11);
        return new String(buffer);
    }


    public static Xid fromString(String value) {
        if (value == null) {
            throw new IllegalArgumentException("string cannot be null");
        } else if (XID_PATTERN.matcher(value).matches()) {
            char[] chars = value.toCharArray();
            long highBits = Base62.decode(chars, 0, chars.length - 11);
            long timeBits = highBits >>> 12;
            long counterBits = highBits & BIT_MASK_LONG_12;
            long randomBits = Base62.decode(chars, chars.length - 11, 11);
            return new Xid(getMostSigBits(timeBits, counterBits), getLeastSigBits(randomBits));
        } else if (UUID_PATTERN.matcher(value).matches()) {
            return fromUUID(UUID.fromString(value));
        } else {
            throw new IllegalArgumentException(format("incorrect format of value %s", value));
        }
    }

    private static long getMostSigBits(long timeBits, long counterBits) {
        return ALWAYS_SET_HIGH_BITS | (timeBits << 16) | counterBits;
    }

    private static long getLeastSigBits(long randomBits) {
        return ALWAYS_SET_LOW_BITS | randomBits;
    }
}
