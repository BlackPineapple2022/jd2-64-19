package by.academy.it.travelcompany.travelitem.airport;

/**
 * This enum contains all airports, that you can get to by Ryanair from
 * Poland Warsaw Modlin airport (code: WMI), exclude Great Britain and Ireland
 * (because this direction require special visa (not default schengen)
 * and for avoiding any problem when people, who do not know about that
 * buying airport tickets and losing money, time and holiday,
 * secondary this enum does not contain Ukraine direction, because
 * it is not actual for Belarussian citizens to travel on Ukraine from Europe
 */

public enum AirportFromWMIRY {

    VIE("Австрия","Вена"),
    CRL("Бельгия","Брюссель--Шарлеруа"),
    BOJ("Болгария","Бургас"),
    PFO("Кипр","Пафос"),
    MRS("Франция","Марсель"),
    BVA("Франция","Париж--Бове"),
    CGN("Германия","Кёльн"),
    ATH("Греция","Афины"),
    CHQ("Греция","Ханья"),
    CFU("Греция","Корфу"),
    SKG("Греция","Салоники"),
    ETM("Израиль","Эйлат"),
    TLV("Израиль","Тель-Авив"),
    BLQ("Италия","Болонья"),
    CAG("Италия","Кальяри"),
    BGY("Италия","Милан--Бергамо"),
    NAP("Италия","Неаполь"),
    PSR("Италия","Пескара"),
    PSA("Италия","Пиза"),
    RMI("Италия","Римини"),
    CIA("Италия","Рим--Чампино"),
    TSF("Италия","Венеция--Тревизо"),
    AMM("Иордания","Амман"),
    EIN("Нидерланды","Эйндховен"),
    TRF("Норвегия","Осло--Торп"),
    FAO("Португалия","Фаро"),
    LIS("Португалия","Лиссабон"),
    OPO("Португалия","Порто"),
    ALC("Испания","Аликанте"),
    BCN("Испания","Барселона"),
    MAD("Испания","Мадрид"),
    PMI("Испания","Пальма-де-Мальорка"),
    SVQ("Испания","Севилья"),
    TFS("Испания","Тенерифе--юг"),
    VLC("Испания","Валенсия"),
    GOT("Швеция","Гётеборг--Ландветтер"),
    NYO("Швеция","Стокгольм--Скавста");

    private String country;
    private String city;

    AirportFromWMIRY(String country, String city){
        this.country = country;
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
