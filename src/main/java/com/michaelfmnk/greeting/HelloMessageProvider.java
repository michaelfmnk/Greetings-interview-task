package com.michaelfmnk.greeting;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

public class HelloMessageProvider {
    private final String city;
    private static Logger log = Logger.getLogger(HelloMessageProvider.class.getName());

    public HelloMessageProvider(String city){
        this.city = StringUtils.capitalize(city);
    }


    public String getMessage(String localeStr, String timeZoneStr){
        localeStr = (localeStr==null) ? System.getProperty("user.language") : localeStr;
        Locale locale;
        DayPart dayPart;
        try{
            locale = Locale.valueOf(localeStr.toUpperCase());
        }catch(IllegalArgumentException e){
            locale = Locale.EN;
            log.warn("language not found; english was selected");
        }

        try{
            dayPart = DayPart.getDayPart(TimeZone.getTimeZone(timeZoneStr.toUpperCase()));
        }catch (Exception e){
            dayPart = DayPart.getDayPart(city);
            log.warn("time zone not found; searching time zone for city: "+ city);
        }
        return formMassage(getGreetingFromResource(dayPart, locale), city);
    }

    private String getGreetingFromResource(DayPart dayPart, Locale locale){

        String propFileName = "messages.properties";

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName)){
            Properties prop = new Properties();

            if (inputStream != null) {
                prop.load(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            return prop.getProperty(resolveResourceName(dayPart, locale));

        } catch (Exception e) {
            log.fatal("error occurred tyring to read properties file");
        }
        throw new RuntimeException();
    }

    private String resolveResourceName(DayPart dayPart, Locale locale){
        String resourceName = "message." + dayPart.toString() + "." + locale.toString();
        return resourceName.toLowerCase();
    }

    private String formMassage(String greeting, String city){

        city = city.replace("_", " ");
        StringUtils.capitalize(city);
        return greeting + ", "+city+"!";

    }
}
