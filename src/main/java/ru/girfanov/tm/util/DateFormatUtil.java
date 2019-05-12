package ru.girfanov.tm.util;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {

    @NotNull private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
    @NotNull private static final SimpleDateFormat dateFormatForJsp = new SimpleDateFormat("yyyy-MM-dd");

    public static Date getDateISO8601(@NotNull final Date date) throws ParseException {
        return dateFormat.parse(dateFormat.format(date));
    }

    public static Date getDateISO8601(@NotNull final String date) throws ParseException {
        return dateFormatForJsp.parse(date);
    }
}