package by.academy.it.travelcompany.travelitem.airport;

/**
 * This enum contains all airports, that you can get to by Ryanair from
 * Lithuania Vilnius national airport(code: VNO), exclude Great Britain and Ireland
 * (because this direction require special visa (not default schengen)
 * and for avoiding any problem when people, who do not know about that
 * buying airport tickets and losing money, time and holiday,
 * secondary this enum does not contain Ukraine direction, because
 * it is not actual for Belarussian citizens to travel on Ukraine from Europe
 */

public enum AirportFromVNORY {

    VIE("Австрия","Вена"),
    CRL("Бельгия","Брюссель--Шарлеруа"),
    BVA("Франция","Париж--Бове"),
    SXF("Германия","Берлин--Шёнефельд"),
    BRE("Германия","Бремен"),
    HHN("Германия","Франкфурт--Хан"),
    ATH("Греция","Афины"),
    CHQ("Греция","Ханья"),
    CFU("Греция","Корфу"),
    TLV("Израиль","Тель-Авив"),
    BGY("Италия","Милан--Бергамо"),
    CIA("Италия","Рим--Чампино"),
    TSF("Италия","Венеция--Тревизо"),
    AMM("Иордания","Амман"),
    MLA("Мальта","Мальта"),
    OSL("Норвегия","Осло"),
    BCN("Испания","Барселона"),
    MAD("Испания","Мадрид");

    private String country;
    private String city;

    AirportFromVNORY(String country, String city){
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
