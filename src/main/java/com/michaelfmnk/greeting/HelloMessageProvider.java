package com.michaelfmnk.greeting;

import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;

import java.util.ResourceBundle;
import java.util.TimeZone;

public class HelloMessageProvider {
    private final String city;
    private static final String GREETING = "greetings";
    private ResourceBundle bundle;
    private static Logger log = Logger.getLogger(HelloMessageProvider.class.getName());

    public HelloMessageProvider(String city){
        this.city = WordUtils.capitalize(city);
    }

    public String getMessage(String timeZoneStr){
        DayPart dayPart;
        if(timeZoneStr!=null){
            dayPart = TimeZoneUtils.getDayPart(TimeZone.getTimeZone(timeZoneStr.toUpperCase()));
        }else{
            dayPart = TimeZoneUtils.getDayPart(city);
        }
        return createMessage(dayPart);
    }

    private String createMessage(DayPart dayPart) {
        bundle = ResourceBundle.getBundle(GREETING, new UTF8Control());
        String resName = "greeting." + dayPart.toString().toLowerCase();
        return bundle.getString(resName) + ", " + city + "!";
    }


    private String resourceNameResolver(DayPart dayPart){
        return "greeting." + dayPart.toString().toLowerCase();
    }

}
