package com.b2x2.core.time;

import java.time.*;

public class XClock {
    protected static Clock clock = Clock.systemUTC();

    public static Instant instant() {
        return Instant.now(clock);
    }

    public static LocalDate localDate() {
        return LocalDate.now(clock);
    }

    public static LocalTime localTime() {
        return LocalTime.now(clock);
    }

    public static LocalDateTime localDateTime() {
        return LocalDateTime.now(clock);
    }

    public static ZonedDateTime zonedDateTime() {
        return ZonedDateTime.now(clock);
    }

    public static Year year() {
        return Year.now(clock);
    }

    public static YearMonth yearMonth() {
        return YearMonth.now(clock);
    }
}
