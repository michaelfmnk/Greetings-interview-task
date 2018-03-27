package com.michaelfmnk.greeting;

import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.util.*;

public class HelloMessageProvider {
    private final String city;
    private Map<DayPart, Map<Locale, String>> messages;

    HelloMessageProvider(String city){
        this.city = StringUtils.capitalize(city);
        messages = new HashMap<>();
    }


    public String getMessage(String localeStr, String timeZoneStr){
        localeStr = (localeStr==null) ? System.getProperty("user.language") : localeStr;
        Locale locale;
        DayPart dayPart;
        try{
            locale = Locale.valueOf(localeStr.toUpperCase());
        }catch(IllegalArgumentException e){
            locale = Locale.EN;
        }


        try{
            dayPart = DayPart.getDayPart(TimeZone.getTimeZone(timeZoneStr.toUpperCase()));
        }catch (Exception e){
            dayPart = DayPart.getDayPart(city);
        }


        return getMessageFromResource(dayPart, locale);

    }

    private String getMessageFromResource(DayPart dayPart, Locale locale){

        String propFileName = "messages.properties";

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName)){
            Properties prop = new Properties();


            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            return prop.getProperty(resolveResourceName(dayPart, locale));

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        throw new RuntimeException();
    }

    private String resolveResourceName(DayPart dayPart, Locale locale){
        String resourceName = "message." + dayPart.toString() + "." + locale.toString();
        return resourceName.toLowerCase();
    }
}
