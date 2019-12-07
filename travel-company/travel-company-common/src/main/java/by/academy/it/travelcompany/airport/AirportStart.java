package by.academy.it.travelcompany.airport;

public enum AirportStart {
    VNO("Lithuania","Vilnius"),
    KUN("Lithuania","Kaunas"),
    WMI("Poland","Warsaw Modlin"),
    WAW("Poland","Warsaw Chopin");

    private String country;
    private String city;

    AirportStart(String country, String city){
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
