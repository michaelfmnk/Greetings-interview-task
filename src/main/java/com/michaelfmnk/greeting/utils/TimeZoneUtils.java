package com.michaelfmnk.greeting.utils;

import com.michaelfmnk.greeting.exception.CityNotFoundException;
import com.michaelfmnk.greeting.exception.HourNotPossibleException;
import org.joda.time.DateTime;

import java.util.Calendar;
import java.util.TimeZone;

public class TimeZoneUtils {
    private static final String DEFAULT_TIMEZONE_ID = "Etc/GMT";

    public static DayPart getDayPart(String city, boolean fallback) throws CityNotFoundException{
        city = city.toLowerCase().replace(" ", "_");
        Calendar calendar = DateTime.now().toGregorianCalendar();
        for (String id:
                TimeZone.getAvailableIDs()) {
            if (id.toLowerCase().matches("(.*)(\\/)"+city+"$")){

                calendar.setTimeZone(TimeZone.getTimeZone(id));
                final int hours = calendar.get(Calendar.HOUR_OF_DAY);
                return getDayPart(hours);
            }
        }
        if (fallback) {
            return getDayPart(TimeZone.getTimeZone(DEFAULT_TIMEZONE_ID));
        } else{
            throw new CityNotFoundException();
        }
    }


    public static DayPart getDayPart(TimeZone tz){
        Calendar calendar = DateTime.now().toGregorianCalendar();
        calendar.setTimeZone(tz);
        return getDayPart(calendar.get(Calendar.HOUR_OF_DAY));
    }

    private static DayPart getDayPart(int hours){
        if (hours > 24 || hours < 0) throw new HourNotPossibleException("hour not possible");

        if(hours >= 6 && hours < 9){
            return DayPart.MORNING;
        }else if(hours >= 9 && hours < 19){
            return DayPart.DAYTIME;
        }else if(hours >= 19 && hours < 23){
            return DayPart.EVENING;
        }else{
            return DayPart.NIGHT;
        }
    }


}
