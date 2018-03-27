package com.michaelfmnk.greeting;

import com.michaelfmnk.greeting.exception.CityNotFoundException;
import com.michaelfmnk.greeting.exception.HourNotPossibleException;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import static com.michaelfmnk.greeting.TimeZoneUtils.getTimeZoneForCity;

public enum DayPart {
    MORNING, DAYTIME, EVENING, NIGHT;


    public static DayPart getDayPart(String city){
        city = city.replace(" ", "_");
        Calendar calendar = new GregorianCalendar(TimeZone.getDefault());
        try {
            TimeZone tz = getTimeZoneForCity(city);
            calendar.setTimeZone(tz);
        }catch (CityNotFoundException e){
            calendar.setTimeZone(TimeZone.getTimeZone("GMT"));
        }
        final int hours = calendar.get(Calendar.HOUR_OF_DAY);
        return getDayPart(hours);
    }

    public static DayPart getDayPart(TimeZone tz){
        Calendar calendar = new GregorianCalendar(tz);
        return getDayPart(calendar.get(Calendar.HOUR_OF_DAY));
    }

    private static DayPart getDayPart(int hours){
       // if (hours > 24 || hours < 0) throw new HourNotPossibleException();

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
