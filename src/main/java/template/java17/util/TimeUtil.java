package template.java17.util;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public final class TimeUtil {

    private static final long HOUR_IN_MILLIS = 3600000;
    private static final long DAY_IN_HOURS = 24;

    private TimeUtil() {
    }

    public static Long now() {
        return LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
    }

    public static Long monthAfter() {
        return LocalDateTime.now().plusMonths(1L).toInstant(ZoneOffset.UTC).toEpochMilli();
    }
}
