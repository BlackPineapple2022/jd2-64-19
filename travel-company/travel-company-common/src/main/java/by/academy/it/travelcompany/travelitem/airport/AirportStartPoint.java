package by.academy.it.travelcompany.travelitem.airport;

/**
 * This enum contains all airports, that you can start you trip from
 * it is four airport near Minsk,
 * VNO - Lithuania Vilnius airport
 * KUN - Lithuania Kaunas airport
 * WMI - Poland Warsaw Modlin airport
 * WAW - Poland Warsaw Chopin airport
 * You can get to this airport by train, bus, blablacar and other
 */

public enum AirportStartPoint {
    VNO("Литва","Вильнюс"),
    KUN("Литва","Каунас"),
    WMI("Польша","Варшава--Модлин"),
    WAW("Польша","Варшава--Шопен");

    private String country;
    private String city;

    AirportStartPoint(String country, String city){
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
