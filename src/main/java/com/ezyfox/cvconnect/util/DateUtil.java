package com.ezyfox.cvconnect.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public final class DateUtil {

    public DateUtil() {}

    public static final String DATE_DDMMYYYY_PATTERN = "dd/MM/yyyy";
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static Date parseFromStringFormat(String date, String parttern) throws ParseException {
        if (date == null) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(parttern);
        return simpleDateFormat.parse(date);
    }

    public static LocalDateTime parseToLocalDateTimeFromStringFormat(String date, String parttern) {
        if (date == null) {
            return null;
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(parttern);
        return LocalDateTime.parse(date, dateTimeFormatter);
    }

    public static String parseLocalDateTimeToString(LocalDateTime dateTime, String parttern) {
        if (dateTime == null) {
            return null;
        }
        // Define the desired date-time format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(parttern);

        // Format the LocalDateTime object as a string
        return dateTime.format(formatter);
    }
}
