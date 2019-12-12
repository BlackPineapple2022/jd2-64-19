package by.academy.it.travelcompany.airport;

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

    VIE("Austria","Vienna"),
    CRL("Belgium","Brussels Charleroi"),
    BOJ("Bulgaria","Burgas"),
    SPU("Croatia","Split"),
    LCA("Cyprus","Larnaca"),
    BLL("Denmark","Billund"),
    TKU("Finland","Turku"),
    GNB("France","Grenoble"),
    LYS("France","Lyon Saint Exupery"),
    NCE("France","Nice"),
    KUT("Georgia","Kutaisi"),
    CFU("Greece","Corfu"),
    BUD("Hungary","Budapest"),
    KEF("Iceland","Reykjavik"),
    ETM("Israel","Eilat"),
    TLV("Israel","Tel-Aviv"),
    AHO("Italy","Alghero"),
    BRI("Italy","Bari"),
    BLQ("Italy","Bologna"),
    CTA("Italy","Catania"),
    BGY("Italy","Milan Bergamo"),
    NAP("Italy","Naples"),
    FCO("Italy","Rome Fiumicino"),
    TRN("Italy","Turin"),
    VRN("Italy","Verona"),
    MLA("Malta","Malta"),
    TGD("Montenegro","Podgorica"),
    RAK("Morocco","Marrakesh"),
    EIN("Netherlands","Eindhoven"),
    BGO("Norway","Bergen"),
    TRF("Norway","Oslo Sandefjord Torp"),
    LIS("Portugal","Lisbon"),
    OPO("Portugal","Porto"),
    OTP("Romania","Bucharest"),
    ALC("Spain","Alicante"),
    BCN("Spain","Barcelona El Prat"),
    MAD("Spain","Madrid"),
    SDR("Spain","Santander"),
    TFS("Spain","Tenerife South"),
    GOT("Sweden","Gothenburg Landvetter"),
    MMX("Sweden","Malmo"),
    NYO("Sweden","Stockholm Skavsta"),
    BSL("Switzerland ","Basel-Mulhouse-Freiburg");

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
