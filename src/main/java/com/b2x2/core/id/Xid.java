package com.b2x2.core.id;

import static com.b2x2.core.id.BitConstants.BIT_MASK_LONG_63;
import static java.lang.String.format;

import com.b2x2.core.time.XClock;
import jakarta.annotation.Nonnull;
import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Pattern;


/**
 * UUIDv7 based on time and randomness. Uses 122 bits of unique data.
 */
public record Xid(long mostSigBits, long leastSigBits) {
    private static final Pattern XID_PATTERN = Pattern.compile("^[A-Za-z0-9]{22}$");
    private static final Pattern UUID_PATTERN = Pattern.compile("^[A-Za-z0-9]{8}-([A-Za-z0-9]{4}-){3}[A-Za-z0-9]{12}$");
    private static final long VER = 7L << 12;
    private static final long VAR = 1L << 63;

    public Xid {
        if (mostSigBits < 0 || (mostSigBits & VER) != VER || (leastSigBits & VAR) != VAR) {
            throw new IllegalArgumentException(
                    "mostSigBits %s, leastSigBits %s".formatted(Long.toBinaryString(mostSigBits), Long.toBinaryString(leastSigBits)));
        }
    }

    public static Xid create() {
        Instant now = XClock.instant();
        long unixts = now.getEpochSecond() << 28;
        long subsec_a = (now.getNano() & 0b11111111111100000000000000000000) >>> 4;
        long subsec_b = (now.getNano() & 0b00000000000011111111111100000000) >>> 8;
        long mostSigBits = unixts | subsec_a | VER | subsec_b;
        long subsec_c = (now.getNano() & 0b00000000000000000000000011111111L) << 34;
        long node_1 = ThreadLocalRandom.current().nextLong() >>> 10;
        long leastSigBits = VAR | subsec_c | node_1;
        return new Xid(mostSigBits, leastSigBits);
    }

    public Instant getCreatedAt() {
        long epochSecond = mostSigBits >>> 28;
        int subseq_a = (int) ((mostSigBits >>> 16) << 20);
        int subseq_b = (int) ((mostSigBits & 0b111111111111) << 8);
        int subseq_c = (int) ((leastSigBits << 2) >>> 56);
        int nanoAdjustment = subseq_a + subseq_b + subseq_c;
        return Instant.ofEpochSecond(epochSecond, nanoAdjustment);
    }

    @Override
    public @Nonnull String toString() {
        char[] buffer = new char[22];
        Base62.encode(mostSigBits, buffer, 0, 11);
        Base62.encode(leastSigBits & BIT_MASK_LONG_63, buffer, 11, 11);
        return new String(buffer);
    }

    public static Xid fromString(String value) {
        if (value == null) {
            throw new IllegalArgumentException("string cannot be null");
        } else if (XID_PATTERN.matcher(value).matches()) {
            char[] chars = value.toCharArray();
            long mostSigBits = Base62.decode(chars, 0, 11);
            long leastSigBits = Base62.decode(chars, 11, 11) | VAR;
            return new Xid(mostSigBits, leastSigBits);
        } else if (UUID_PATTERN.matcher(value).matches()) {
            return fromUUID(UUID.fromString(value));
        } else {
            throw new IllegalArgumentException(format("incorrect format of value %s", value));
        }
    }

    public UUID toUUID() {
        return new UUID(mostSigBits, leastSigBits);
    }

    public static Xid fromUUID(UUID uuid) {
        return new Xid(uuid.getMostSignificantBits(), uuid.getLeastSignificantBits());
    }
}
