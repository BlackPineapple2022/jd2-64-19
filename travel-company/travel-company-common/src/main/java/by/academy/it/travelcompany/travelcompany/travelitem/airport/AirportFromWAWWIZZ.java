package by.academy.it.travelcompany.travelcompany.travelitem.airport;

/**
 * This enum contains all airports, that you can get to by Wizzair from
 * Poland Warsaw Chopin airport (code: WAW), exclude Great Britain and Ireland
 * (because this direction require special visa (not default schengen)
 * and for avoiding any problem when people, who do not know about that
 * buying airport tickets and losing money, time and holiday,
 * secondary this enum does not contain Ukraine direction, because
 * it is not actual for Belarussian citizens to travel on Ukraine from Europe
 */

public enum AirportFromWAWWIZZ {

    VIE("Австрия","Вена"),
    CRL("Бельгия","Брюссель--Шарлеруа"),
    BOJ("Болгария","Бургас"),
    SPU("Хорватия","Сплит"),
    LCA("Кипр","Ларнака"),
    BLL("Дания","Биллунн"),
    TKU("Финляндия","Турку"),
    GNB("Франция","Гренобль"),
    LYS("Франция","Лион--Сен Экзюпери"),
    NCE("Франция","Ницца"),
    KUT("Грузия","Кутаиси"),
    CFU("Греция","Корфу"),
    BUD("Венгрия","Будапешт"),
    KEF("Исландия","Рейкьявик"),
    ETM("Израиль","Эйлат"),
    TLV("Израиль","Тель-Авив"),
    AHO("Италия","Альгеро"),
    BRI("Италия","Бари"),
    BLQ("Италия","Болонья"),
    CTA("Италия","Катания"),
    BGY("Италия","Милан--Бергамо"),
    NAP("Италия","Неаполь"),
    FCO("Италия","Рим--Фьюмичино"),
    TRN("Италия","Турин"),
    VRN("Италия","Верона"),
    MLA("Мальта","Мальта"),
    TGD("Черногория","Подгорица"),
    RAK("Марокко","Марракеш"),
    EIN("Нидерланды","Эйндховен"),
    BGO("Норвегия","Берген"),
    TRF("Норвегия","Осло--Торп"),
    LIS("Португалия","Лиссабон"),
    OPO("Португалия","Порто"),
    OTP("Румыния","Бухарест"),
    ALC("Испания","Аликанте"),
    BCN("Испания","Барселона"),
    MAD("Испания","Мадрид"),
    SDR("Испания","Сантандер"),
    TFS("Испания","Тенерифе--юг"),
    GOT("Швеция","Гётеборг--Ландветтер"),
    MMX("Швеция","Мальмё"),
    NYO("Швеция","Стокгольм--Скавста"),
    BSL("Швейцария ","Аэропорт Базеля-Мюлуза");

    private String country;
    private String city;

    AirportFromWAWWIZZ(String country, String city){
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
