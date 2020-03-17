# ПРОЕКТ Турфирма
## Vladimir Dubovsky
## http://blackpineapple.by

Туристический сервис для планирования авиаперелётов по Европе из эропортов 
Литвы: Вильнюса, Каунаса и Польшы: Варшава-Модлен, Варшава-Шопен 

В качестве источников данных о рейсах используются данные сайтов Ryanair.com и Wizzair.com, данные хранятся в MySQL DB

# Описание модулей

## flight-scanner
Модуль содержит классы и интерфейсы для взаимодействия (через HTTP) с сайтами авиокомпаний **http://ryanair.com**
и **http://wizzair.com** для поиска авиабилетов. 

## currency-scanner
Модуль содержит классы и интерфейсы для взаимодействия (через HTTP) с сайтом **http://fixer.io** для получения
котировок валют по отношению к ЕВРО

## flight-finder
Самостоятельное веб приложение для парсинга базы авиаперелётов из четырёх аэропортов: Вильнюса, Каунаса,
Варшавы-Модлен, Варшавы-Шопен. Работа парсера ведётся в бесконечном цикле, активируется и деактивируется из главного меню.  

## flight-updater
Самостоятельное веб (REST API) приложение для обновления авиаперелётов из четырёх аэропортов: Вильнюса, Каунаса,
Варшавы-Модлен, Варшавы-Шопен. 

## blackpineapple-installer
Самостоятельное веб приложение для создания структуры БД и заполнения начальными значениями.

## blackpineapple-core
Модуль содержит дао, сервисы, репозитории, модели, утилиты и реализовывет логику проекта.

## blackpineapple-web 
Основное веб приложение проекта, также содержит REST API сервис для взаимодействия с другими модулями. 

# Порядок установки. 

Все веб приложения могут быть размещены как на одном, так и на различных серверах, рекомендуемая конфигурация: 

Первый сервер: blackpineapple-web + blackpineapple-installer + база данных. Второй сервер: flight-finder. 
Третий сервер: flight-updater.

Категорически не рекомендуется размещать flight-finder и flight-updater а одном сервере

### 1 Создайте новую БД MySql.

### 2 Установка blackpineapple-installer

2.1 Откройте файл `src/main/resources/spring-config.xml` из модуля blackpineapple-core и отредактируйте аттрибуты для доступа к БД:

 - **jdbcUrl**

пример для работы на локальной машине:
 
`<property name="jdbcUrl" value="jdbc:mysql://localhost:3306/blackpineapple?useUnicode=true&amp;characterEncoding=UTF-8&amp;useSSL=false&amp;serverTimezone=UTC"/>`

 - **user**

пример для работы на локальной машине:`<prop key="user">root</prop>`

 - **password**

пример для работы на локальной машине:`<prop key="password">1234</prop>`

2.2 Скомпилируйте **blackpineapple-installer-war**, разверните, на главной странице нажмите на кнопку: 
_INSTALL OR UPDATE STARTING DATE_, по завершению установки Вы увидите сообщение: _Thanks for installing data_.

2.3 Выключите приложение **blackpineapple-installer-war**, затем удалите его во избежания случайной инициализации при перезапуске сервера.

### 3 Установка flight-updater

3.1 Скомпилируете **flight-updater-web.war**, разверните, на главной странице Вы увидите сообщение: _Welcome to flight updater web module_

### 4 Установка blackpineapple-web

4.1 Откройте файл `src/main/webapp/WEB-INF/dispatcher-spring-servlet.xml` из модуля blackpineapple-web и повторите п 2.1 

4.2 Откройте файл `src/main/resources/blackpineapple.properties` из модуля blackpineapple-web и пропишите контекстный путь к fligh-updater 

**flight.updater.path**

пример для работы на локальной машине: `flight.updater.path=http://localhost:8080/flightupdater/`

4.2 Скомпилируйте blackpineapple-web, разверните

### 5 Установка flight-finder

5.1 Откройте `src/main/resources/flightfinder.properties` из модуля flight-finder и пропишите контекстный путь к blackpineapple-web 

**main.web.project.path**

пример для работы на локальной машине: `main.web.project.path=http://localhost:8080/blackpineapple/`

5.2 Скомпилируйте flight-finder, разверните, на главной странице активируйте сканеры.

