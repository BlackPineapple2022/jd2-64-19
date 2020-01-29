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

    VIE("Austria","Vienna"),
    CRL("Belgium","Brussels Charleroi"),
    BVA("France","Paris Beauvais"),
    SXF("Germany","Berlin Schonefeld"),
    BRE("Germany","Bremen"),
    HHN("Germany","Frankfurt Hahn"),
    ATH("Greece","Athens"),
    CHQ("Greece","Chania"),
    CFU("Greece","Corfu"),
    TLV("Israel","Tel-Aviv"),
    BGY("Italy","Milan--Bergamo"),
    CIA("Italy","Rome--Ciampino"),
    TSF("Italy","Venice Treviso"),
    AMM("Jordan","Amman"),
    MLA("Malta","Malta"),
    OSL("Norway","Oslo"),
    BCN("Spain","Barcelona"),
    MAD("Spain","Madrid");

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
