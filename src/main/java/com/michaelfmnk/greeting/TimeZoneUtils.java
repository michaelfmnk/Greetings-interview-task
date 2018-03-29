package com.michaelfmnk.greeting;

import com.michaelfmnk.greeting.exception.CityNotFoundException;
import com.michaelfmnk.greeting.exception.HourNotPossibleException;
import org.apache.log4j.Logger;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class TimeZoneUtils {
    private static Logger log = Logger.getLogger(TimeZoneUtils.class.getName());


    public static DayPart getDayPart(String city) throws CityNotFoundException{
        city = city.toLowerCase().replace(" ", "_");
        Calendar calendar = new GregorianCalendar(TimeZone.getDefault());
        for (String id:
                TimeZone.getAvailableIDs()) {
            if (id.toLowerCase().matches("(.*)(\\/)"+city+"$")){

                calendar.setTimeZone(TimeZone.getTimeZone(id));
                final int hours = calendar.get(Calendar.HOUR_OF_DAY);
                return getDayPart(hours);
            }
        }
        throw new CityNotFoundException();
    }


    public static DayPart getDayPart(TimeZone tz){
        Calendar calendar = new GregorianCalendar(tz);
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
