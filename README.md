[![Build Status](https://travis-ci.org/michaelfmnk/greetings.svg?branch=master)](https://travis-ci.org/michaelfmnk/greetings)


### Доступные команды
  - -l --language - установка языка для вывода сообщения
  - -tz --timezone - выбор часового пояса

При отсутсвии параметра -l будет использован системный язык, если сообщение на таком языке найдено не будет, будет выбран английский. 

При отсутствии параметра -tz, будет произведена попытка понять, в каком часовом поясе находится город, если же не получится, будет взят GMT+0.

### Примеры логов:


Main:19 - ### START ###  
Main:42 - accepted data: { city: new york, lang: en, tz: hthtt}  
utils.HelloMessageProvider:28 - getting datPart by city name: New York; timezone is not valid  
utils.HelloMessageProvider:35 - final message: Good Day, New York!  
Main:46 - ### END ###  

Main:19 - ### START ###  
Main:42 - accepted data: { city: kiev, lang: ua, tz: null}  
utils.HelloMessageProvider:28 - getting datPart by city name: Kiev; timezone is not valid  
utils.HelloMessageProvider:35 - final message: Доброго дня, Kiev!  
Main:46 - ### END ###  

Main:19 - ### START ###  
Main:42 - accepted data: { city: kiev, lang: en, tz: Etc/GMT+3}  
utils.HelloMessageProvider:31 - getting dayPart by provided timezone: Etc/GMT+3  
utils.HelloMessageProvider:35 - final message: Good Day, Kiev!  
Main:46 - ### END ###  


