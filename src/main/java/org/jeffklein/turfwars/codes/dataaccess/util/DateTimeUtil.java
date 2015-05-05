package org.jeffklein.turfwars.codes.dataaccess.util;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

/**
 * Essentially this class makes sure any new datetime object is initialized
 * in UTC and has its millis trimmed to zero.For the purposes of this app,
 * we don't need millisecond precision. I was getting test breaks because
 * apparently the JDBC driver (or MySQL itself?) drops millis, so it was
 * easier just to zero them out since we don't need them anyway.
 */
public class DateTimeUtil {
    /**
     *
     * @param year the year. example: 1997
     * @param monthOfYear month of year. 1 = January, 12 = December
     * @param dayOfMonth day of month ranging between 1-31
     * @param hourOfDay hour of the day between 0-23
     * @param minuteOfHour minute of hour ranging between 0 and 59
     * @param secondOfMinute second of minute ranging between 0 and 59
     * @return the resulting Joda DateTime with millis truncated to zero.
     */
    public static final DateTime createPersistableUtcDateTime(int year,
                                                              int monthOfYear,
                                                              int dayOfMonth,
                                                              int hourOfDay,
                                                              int minuteOfHour,
                                                              int secondOfMinute) {
        return new DateTime(DateTimeZone.forID("UTC"))
                .withYear(year)
                .withMonthOfYear(monthOfYear)
                .withDayOfMonth(dayOfMonth)
                .withHourOfDay(hourOfDay)
                .withMinuteOfHour(minuteOfHour)
                .withSecondOfMinute(secondOfMinute)
                .withMillisOfSecond(0);
    }

    public static final DateTime nowUtc() {
        return new DateTime(DateTimeZone.forID("UTC")).withMillisOfSecond(0);
    }
}
