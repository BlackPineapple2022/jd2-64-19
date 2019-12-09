package by.academy.it.travelcompany.airport;

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

    LCA("Cyprus","Larnaca"),
    BLL("Denmark","Billund"),
    NCE("France","Nice"),
    BVA("France","Paris Beauvais"),
    KUT("Georgia","Kutaisi"),
    DTM("Germany","Dortmund"),
    ATH("Greece","Athens"),
    KEF("Iceland","Reykjavik"),
    ETM("Israel","Eilat"),
    TLV("Israel","Tel-Aviv"),
    MXP("Italy","Milan Malpensa"),
    EIN("Netherlands","Eindhoven"),
    TRF("Norway","Oslo Sandefjord Torp"),
    BCN("Spain","Barcelona El Prat");

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
