[![Build Status](https://travis-ci.org/michaelfmnk/greetings.svg?branch=master)](https://travis-ci.org/michaelfmnk/greetings)


### Доступные команды
  - -l --language - установка языка для вывода сообщения
  - -tz --timezone - выбор часового пояса

При отсутсвии параметра -l будет использован системный язык, если сообщение на таком языке найдено не будет, будет выбран английский. 

При отсутствии параметра -tz, будет произведена попытка понять, в каком часовом поясе находится город, если же не получится, будет взят GMT+0.

### Примеры логов:

Main:14 - ### START ###  
Main:28 - accepted data: { city: new_york, lang: ru, tz: null}  
HelloMessageProvider:30 - language: ru  
HelloMessageProvider:41 - time zone not found; searching time zone for city: New_york
HelloMessageProvider:44 - final message: Добрый день, New york!  
Main:35 - ### END ###  

Main:14 - ### START ###  
Main:28 - accepted data: { city: london, lang: null, tz: gmt+10}  
HelloMessageProvider:22 - language was not set; system default is en  
HelloMessageProvider:30 - language: en  
HelloMessageProvider:38 - dayPart in gmt+10 is NIGHT  
HelloMessageProvider:44 - final message: Good Night, London!  
Main:35 - ### END ###  
