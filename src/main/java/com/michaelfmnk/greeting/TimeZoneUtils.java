package com.michaelfmnk.greeting;

import com.michaelfmnk.greeting.exception.CityNotFoundException;

import java.util.TimeZone;

public class TimeZoneUtils {

    public static TimeZone getTimeZoneForCity(String city) throws CityNotFoundException {
        for (String id:
                TimeZone.getAvailableIDs()) {
            if (id.toLowerCase().contains( // todo regex
                    city.toLowerCase()
            )){
                return TimeZone.getTimeZone(id);
            }
        }
        throw new CityNotFoundException();
    }
}
