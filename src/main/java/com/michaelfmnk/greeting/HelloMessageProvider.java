package com.michaelfmnk.greeting;

import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class HelloMessageProvider {
    private final String city;
    private Map<DayPart, Map<Locale, String>> messages;

    HelloMessageProvider(String city) {
        this.city = StringUtils.capitalize(city);
        messages = new HashMap<>();

        Map<Locale, String> morningMessages = new HashMap<>();
        morningMessages.put(Locale.EN, "Good morning");
        morningMessages.put(Locale.RU, "Доброе утро");
        messages.put(DayPart.MORNING, morningMessages);


        Map<Locale, String> daytimeMessages = new HashMap<>();
        daytimeMessages.put(Locale.EN, "Good day");
        daytimeMessages.put(Locale.RU, "Добрый день");
        messages.put(DayPart.DAYTIME, daytimeMessages);


        Map<Locale, String> eveningMessages = new HashMap<>();
        eveningMessages.put(Locale.EN, "Good evening");
        eveningMessages.put(Locale.RU, "Добрый вечер");
        messages.put(DayPart.EVENING, eveningMessages);


        Map<Locale, String> nightMessages = new HashMap<>();
        nightMessages.put(Locale.EN, "Good evening");
        nightMessages.put(Locale.RU, "Добрый вечер");
        messages.put(DayPart.EVENING, nightMessages);
    }


    public String getMessage(String locale){
        DayPart dayPart = DayPart.getDayPart(city);
        String msg;
        return messages.get(dayPart).get(Locale.valueOf(locale.toUpperCase())) + ", " + this.city;
    }
}
