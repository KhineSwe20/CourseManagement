package com.cdsg.coursemanagement.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

public class CommonUtil {

    public static String dateToString(String format, LocalDateTime dateTime) {
        if (dateTime == null) {
            return "";
        }
        if (format == null || format.trim().isEmpty()) {
            format = CommonConstant.DATE_TIME_FORMAT;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return dateTime.format(formatter);
    }

    public static boolean isValidCollection(Collection<?> collection) {
        return collection != null && !collection.isEmpty();
    }
}
