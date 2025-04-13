package com.cdsg.coursemanagement.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
}
