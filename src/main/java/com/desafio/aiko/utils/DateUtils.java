package com.desafio.aiko.utils;


import lombok.Data;
import lombok.experimental.UtilityClass;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Data
@UtilityClass
public class DateUtils {
    public static String formatTimeStamp(Timestamp timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

        return sdf.format(timestamp);
    }
}

