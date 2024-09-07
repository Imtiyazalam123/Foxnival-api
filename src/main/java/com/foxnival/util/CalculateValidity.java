package com.foxnival.util;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@Service
public class CalculateValidity {
    
    private static Calendar calendar;

    static {
        calendar = Calendar.getInstance();
        TimeZone timeZone = TimeZone.getTimeZone("IST");
        calendar.setTimeZone(timeZone);
    }

    public Instant getValidityDate(String planYear) {
        int year = Integer.parseInt(planYear);
        calendar.setTime(new Date());
        int validity = year == 0 ? 5 : 365 * year;
        calendar.add(Calendar.DATE, validity);
        Date time = calendar.getTime();
        return time.toInstant();
    }
}
