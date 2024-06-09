package com.bitespeed.contact.common.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Log4j2
public class Utility {

    private static ObjectMapper mapper = new ObjectMapper();

    public static String toJson(Object o) {
        try {
            return mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            log.error(e);
            return null;
        }
    }

    public static ZonedDateTime getZonedDateTimeStamp(String timeZone){
        ZoneId time = ZoneId.of(timeZone);
        ZonedDateTime dateTime = ZonedDateTime.now(time);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
        String timeString = dateTime.format(formatter);
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(timeString, formatter);
        return zonedDateTime.withZoneSameInstant(ZoneId.of(timeZone));
    }

    public static boolean isNullOrEmpty(Object o)
    {
        return o == null || o.toString().trim().length() == 0;

    }
}
