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

    VIE("Austria","Vienna"),
    CRL("Belgium","Brussels Charleroi"),
    BOJ("Bulgaria","Burgas"),
    PFO("Cyprus","Paphos"),
    MRS("France","Marseille"),
    BVA("France","Paris Beauvais"),
    CGN("Germany","Cologne"),
    ATH("Greece","Athens"),
    CHQ("Greece","Chania"),
    CFU("Greece","Corfu"),
    SKG("Greece","Thessaloniki"),
    ETM("Israel","Eilat"),
    TLV("Israel","Tel-Aviv"),
    BLQ("Italy","Bologna"),
    CAG("Italy","Cagliari"),
    BGY("Italy","Milan--Bergamo"),
    NAP("Italy","Naples"),
    PSR("Italy","Pescara"),
    PSA("Italy","Pisa"),
    RMI("Italy","Rimini"),
    CIA("Italy","Rome--Ciampino"),
    TSF("Italy","Venice Treviso"),
    AMM("Jordan","Amman"),
    EIN("Netherlands","Eindhoven"),
    TRF("Norway","Oslo--Torp"),
    FAO("Portugal","Faro"),
    LIS("Portugal","Lisbon"),
    OPO("Portugal","Porto"),
    ALC("Spain","Alicante"),
    BCN("Spain","Barcelona"),
    MAD("Spain","Madrid"),
    PMI("Spain","Palma de Mallorca"),
    SVQ("Spain","Seville"),
    TFS("Spain","Tenerife--South"),
    VLC("Spain","Valencia"),
    GOT("Sweden","Gothenburg Landvetter"),
    NYO("Sweden","Stockholm Skavsta");

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
