package by.academy.it.travelcompany.travelcompany.travelitem.routemap;

import by.academy.it.travelcompany.service.global.imp.AirportServiceImpl;
import by.academy.it.travelcompany.travelcompany.travelitem.airport.Airport;
import by.academy.it.travelcompany.travelcompany.travelitem.airline.Airline;
import by.academy.it.travelcompany.travelcompany.travelitem.direction.Direction;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/*
RouteMap - is Object that fully describes route with:
certain airlineEnum
certain origin airport
certain destination airport
certain direction
Useful override method toString() get:
for example: RY-VNO-BVA-Direct
          or WIZZ-AES-KUN-Return
*/

@Data
@NoArgsConstructor
public class RouteMap {

    private static final String ROUTE_MAP_REGEX = "--";

    private Long id;

    private String airlineStr;
    private String originAirportCode;
    private String destinationAirportCode;
    private String directionStr;

    private Airline airline;
    private Airport originAirport;
    private Airport destinationAirport;
    private Direction direction;

    public RouteMap(Long id, Airline airline, Airport originAirport, Airport destinationAirport, Direction direction) {

        this.id = id;
        this.airline = airline;
        this.originAirport = originAirport;
        this.destinationAirport = destinationAirport;
        this.direction = direction;

        this.airlineStr = airline.getAirlineName();
        this.originAirportCode = originAirport.getCode();
        this.destinationAirportCode = destinationAirport.getCode();
        this.directionStr = direction.getDirectionName();
    }

    public RouteMap(Long id, String airlineStr, String originAirportCode, String destinationAirportCode, String directionStr) {
        this(
                id,
                new Airline(null, airlineStr),
                AirportServiceImpl.getInstance().getAirportByCode(originAirportCode),
                AirportServiceImpl.getInstance().getAirportByCode(destinationAirportCode),
                new Direction(null, directionStr));
    }

    public RouteMap(Long id,String routeMapString) {
        this(
                id,
                routeMapString.split(ROUTE_MAP_REGEX)[0],
                routeMapString.split(ROUTE_MAP_REGEX)[1],
                routeMapString.split(ROUTE_MAP_REGEX)[2],
                routeMapString.split(ROUTE_MAP_REGEX)[3]
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RouteMap routeMap = (RouteMap) o;
        return Objects.equals(toString(), routeMap.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(toString());
    }

    @Override
    public String toString() {
        return "" + airlineStr + "--" + originAirportCode + "--" + destinationAirportCode + "--" + directionStr;
    }
}
