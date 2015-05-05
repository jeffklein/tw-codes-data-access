package org.jeffklein.turfwars.codes.dataaccess.util;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

/**
 * Created by jklein on 5/4/15.
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
}
