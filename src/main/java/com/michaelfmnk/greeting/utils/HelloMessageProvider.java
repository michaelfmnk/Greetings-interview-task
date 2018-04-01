package com.michaelfmnk.greeting.utils;

import com.michaelfmnk.greeting.UTF8Control;
import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;

import java.util.ResourceBundle;
import java.util.TimeZone;

public class HelloMessageProvider {
    private final String city;
    private final String timeZone;

    private static final String GREETING = "greetings";
    private ResourceBundle bundle;

    private static Logger log = Logger.getLogger(HelloMessageProvider.class.getName());

    public HelloMessageProvider(String city, String timezone){
        this.city = WordUtils.capitalize(city);
        this.timeZone = timezone;
    }

    public String getMessage(){
        DayPart dayPart;
        if (isTimeZoneValid(timeZone)) {
            log.info("getting dayPart by provided timezone: " + timeZone);
            dayPart = TimeZoneUtils.getDayPart(TimeZone.getTimeZone(timeZone));
        } else {
            log.info("getting datPart by city name: "+city+"; timezone is not valid");
            dayPart = TimeZoneUtils.getDayPart(city, true);
        }
        String message = createMessage(dayPart);
        log.info("final message: " + message);
        return message;
    }

    private String createMessage(DayPart dayPart) {
        bundle = ResourceBundle.getBundle(GREETING, new UTF8Control());
        String resName = "greeting." + dayPart.toString().toLowerCase();
        return bundle.getString(resName) + ", " + city + "!";
    }

    private boolean isTimeZoneValid(String timezone){
        if (timezone==null) return false;
        for (String id:
                TimeZone.getAvailableIDs()) {
            if (id.toLowerCase().equals(timezone.toLowerCase())){
                return true;
            }
        }
        return false;
    }
}
