package by.academy.it.travelcompany.travelitem.airport;

/**
 * This enum contains all airports, that you can get to by Wizz air from
 * Lithuania Kaunas national airport(code: KUN), exclude Great Britain and Ireland
 * (because this direction require special visa (not default schengen)
 * and for avoiding any problem when people, who do not know about that
 * buying airport tickets and losing money, time and holiday,
 * secondary this enum does not contain Ukraine direction, because
 * it is not actual for Belarussian citizens to travel on Ukraine from Europe
 */

public enum AirportFromKUNWIZZ {
    EIN("Netherlands","Eindhoven"),
    BGO("Norway","Bergen"),
    AES("Norway","Alesund"),
    SVG("Norway","Stavanger"),
    TKU("Finland","Turku");

    private String country;
    private String city;

    AirportFromKUNWIZZ(String country, String city){
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
