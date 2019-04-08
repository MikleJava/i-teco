package ru.girfanov.tm.util;

import org.jetbrains.annotations.NotNull;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateConverterGregorianCalendar {
    public static XMLGregorianCalendar convert(@NotNull final Date date) throws DatatypeConfigurationException {
        final GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
    }
}
