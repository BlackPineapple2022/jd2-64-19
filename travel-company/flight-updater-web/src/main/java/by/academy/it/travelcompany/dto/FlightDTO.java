package by.academy.it.travelcompany.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@EqualsAndHashCode
public class FlightDTO {

    private Double amount;
    private String flightNumber;
    private String departureDateTime;
    private String arriveDateTime;
    private String routeMap;
    private String currency;

}