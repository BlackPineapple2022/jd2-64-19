package by.academy.it.travelcompany.travelitem.airport;

/**
 * This enum contains all airports, that you can get to by Wizzair from
 * Lithuania Vilnius national airport(code: VNO), exclude Great Britain and Ireland
 * (because this direction require special visa (not default schengen)
 * and for avoiding any problem when people, who do not know about that
 * buying airport tickets and losing money, time and holiday,
 * secondary this enum does not contain Ukraine direction, because
 * it is not actual for Belarussian citizens to travel on Ukraine from Europe
 */


public enum AirportFromVNOWIZZ {

    LCA("Кипр","Ларнака"),
    BLL("Дания","Биллунн"),
    NCE("Франция","Ницца"),
    BVA("Франция","Париж--Бове"),
    KUT("Грузия","Кутаиси"),
    DTM("Германия","Дортмунд"),
    ATH("Греция","Афины"),
    KEF("Исландия","Рейкьявик"),
    ETM("Израиль","Эйлат"),
    TLV("Израиль","Тель-Авив"),
    MXP("Италия","Милан--Мальпенса"),
    EIN("Нидерланды","Эйндховен"),
    TRF("Норвегия","Осло--Торп"),
    BCN("Испания","Барселона");

    private String country;
    private String city;

    AirportFromVNOWIZZ(String country, String city){
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
