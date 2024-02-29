package com.pubsub.message.interchange.util;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class DateTimeUtil {

    public static LocalDateTime getCurrentUtcDateTime() {
        return ZonedDateTime.now(ZoneOffset.of("+0900"))
                .withZoneSameInstant(ZoneOffset.UTC)
                .toLocalDateTime();
    }
}
