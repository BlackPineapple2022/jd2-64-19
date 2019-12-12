package by.academy.it.travelcompany.airport;

/**
 * This enum contains all airports, that you can get to by Ryanair from
 * Lithuania Kaunas national airport(code: KUN), exclude Great Britain and Ireland
 * (because this direction require special visa (not default schengen)
 * and for avoiding any problem when people, who do not know about that
 * buying airport tickets and losing money, time and holiday,
 * secondary this enum does not contain Ukraine direction, because
 * it is not actual for Belarussian citizens to travel on Ukraine from Europe
 */

public enum AirportFromKUNRY {
    PFO("Cyprus","Paphos"),
    CPH("Denmark", "Copenhagen"),
    CGN("Germany", "Cologne"),
    NUE("Germany","Nuremberg"),
    RHO("Greece","Rhodes"),
    ETM("Israel","Eilat Ramon"),
    BLQ("Italy","Bologna"),
    MXP("Italy","Milan Malpensa"),
    NAP("Italy","Naples"),
    RMI("Italy","Rimini"),
    ALC("Spain","Alicante"),
    PMI("Spain","Palma de Mallorca");

    private String country;
    private String city;

    AirportFromKUNRY(String country, String city){
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
