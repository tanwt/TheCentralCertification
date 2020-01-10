package com.jike.system1.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.function.Predicate;

@Slf4j
public final class UUIDUtils {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("yyMMddHHmmss");

    private static String getTimeStr() {
        return LocalDateTime.now().format(TIME_FORMATTER);
    }

    private UUIDUtils() {
    }

    public static String createUuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String createUuidWithTime(String prefix) {
        // 10位随机字符串，可达亿分之一的重复率
        return createUuidWithTime(prefix, 10);
    }

    public static String createUuidWithTime(String prefix, int randomLength) {
        return prefix + getTimeStr() + UUID.randomUUID().toString().replace("-", "").substring(32 - randomLength);
    }

    public static String createUuid(String prefix) {
        if (StringUtils.isBlank(prefix)) {
            return createUuid();
        }
        return prefix + createUuid().substring(prefix.length());
    }

    public static String createUuid(Predicate<String> uuidExist) {
        String uuid;
        do {
            uuid = createUuid();
        } while (uuidExist.test(uuid));
        return uuid;
    }

    public static String createUuid(Predicate<String> uuidExist, String prefix) {
        String uuid;
        do {
            uuid = createUuid(prefix);
        } while (uuidExist.test(uuid));
        return uuid;
    }
}
