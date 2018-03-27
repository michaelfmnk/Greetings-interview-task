package com.michaelfmnk.greeting;

import com.michaelfmnk.greeting.exception.CityNotFoundException;

import java.util.TimeZone;

public class TimeZoneUtils {

    public static TimeZone getTimeZoneForCity(String city) throws CityNotFoundException {
        city = city.toLowerCase();
        for (String id:
                TimeZone.getAvailableIDs()) {
            if (id.toLowerCase().matches("(.*)(\\/)"+city+"$")){
                return TimeZone.getTimeZone(id);
            }
        }

        throw new CityNotFoundException();
    }
}
