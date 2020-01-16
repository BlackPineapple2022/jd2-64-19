package by.academy.it.travelcompany.travelitem.routemap;

import by.academy.it.travelcompany.travelitem.airline.AirlineEnum;
import by.academy.it.travelcompany.travelitem.airport.Airport;
import by.academy.it.travelcompany.travelitem.direction.Direction;

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

public class RouteMap {

    private static final String ROUTE_MAP_REGEX = "--";

    private String airlineStr;
    private String originAirportCode;
    private String destinationAirportCode;
    private String directionStr;

    private AirlineEnum airlineEnum;
    private Airport originAirport;
    private Airport destinationAirport;
    private Direction direction;

    public RouteMap(AirlineEnum airlineEnum, Airport originAirport, Airport destinationAirport, Direction direction) {
        this.airlineEnum = airlineEnum;
        this.originAirport = originAirport;
        this.destinationAirport = destinationAirport;
        this.direction = direction;

        this.airlineStr = airlineEnum.toString();
        this.originAirportCode = originAirport.getCode();
        this.destinationAirportCode = destinationAirport.getCode();
        this.directionStr = direction.toString();
    }

    public RouteMap(String airlineStr, String originAirportCode, String destinationAirportCode, String directionStr) {
        this(AirlineEnum.valueOf(airlineStr), new Airport(originAirportCode), new Airport(destinationAirportCode), Direction.valueOf(directionStr));
    }

    public RouteMap(String routeMapString) {
        this(
                routeMapString.split(ROUTE_MAP_REGEX)[0],
                routeMapString.split(ROUTE_MAP_REGEX)[1],
                routeMapString.split(ROUTE_MAP_REGEX)[2],
                routeMapString.split(ROUTE_MAP_REGEX)[3]
        );
    }

    public String getAirlineStr() {
        return airlineStr;
    }

    public void setAirlineStr(String airlineStr) {
        this.airlineStr = airlineStr;
    }

    public String getOriginAirportCode() {
        return originAirportCode;
    }

    public void setOriginAirportCode(String originAirportCode) {
        this.originAirportCode = originAirportCode;
    }

    public String getDestinationAirportCode() {
        return destinationAirportCode;
    }

    public void setDestinationAirportCode(String destinationAirportCode) {
        this.destinationAirportCode = destinationAirportCode;
    }

    public String getDirectionStr() {
        return directionStr;
    }

    public void setDirectionStr(String directionStr) {
        this.directionStr = directionStr;
    }

    public AirlineEnum getAirlineEnum() {
        return airlineEnum;
    }

    public void setAirlineEnum(AirlineEnum airlineEnum) {
        this.airlineEnum = airlineEnum;
    }

    public Airport getOriginAirport() {
        return originAirport;
    }

    public void setOriginAirport(Airport originAirport) {
        this.originAirport = originAirport;
    }

    public Airport getDestinationAirport() {
        return destinationAirport;
    }

    public void setDestinationAirport(Airport destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
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
