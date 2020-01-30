package by.academy.it.travelcompany.travelitem.features.accommodation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Accommodation {

    private Long id;
    private String country;
    private String city;
    private String address;
    private String nameOfAccommodation;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private Accommodations accommodations;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Accommodation that = (Accommodation) o;
        return  Objects.equals(country, that.country) &&
                Objects.equals(city, that.city) &&
                Objects.equals(address, that.address) &&
                Objects.equals(nameOfAccommodation, that.nameOfAccommodation) &&
                Objects.equals(checkInDate, that.checkInDate) &&
                Objects.equals(checkOutDate, that.checkOutDate) &&
                accommodations == that.accommodations;
    }

    @Override
    public int hashCode() {
        return Objects.hash( country, city, address, nameOfAccommodation, checkInDate, checkOutDate, accommodations);
    }

    @Override
    public String toString() {
        return "Accommodation{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", nameOfAccommodation='" + nameOfAccommodation + '\'' +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", accommodations=" + accommodations +
                '}';
    }

}
