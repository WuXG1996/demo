package com.example.demo.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by void on 2018/12/10.
 */
public class LocalDateTimeUtil {

    /**
     * localDateTime转date
     * @param dateTime
     * @return
     */
    public static Date localDateTime2Date(LocalDateTime dateTime){
        return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * date转localDateTime
     * @param date
     * @return
     */
    public static LocalDateTime date2LocalDateTime(Date date){
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }
}
