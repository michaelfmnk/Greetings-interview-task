package com.michaelfmnk.greeting;

import com.michaelfmnk.greeting.exception.CityNotFoundException;
import com.sun.xml.internal.ws.api.ha.StickyFeature;

import java.util.TimeZone;

public class TimeZoneUtils {

    public static TimeZone getTimeZoneForCity(String city) throws CityNotFoundException {
        for (String id:
                TimeZone.getAvailableIDs()) {
            if (id.toLowerCase().matches("\\/"+city+"$")){
                return TimeZone.getTimeZone(id);
            }
        }

        throw new CityNotFoundException();
    }
}
