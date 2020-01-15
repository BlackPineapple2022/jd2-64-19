package by.academy.it.travelcompany.travelitem.routemap;

import java.util.Objects;

public class RouteMap {

    private static final String ROUTE_MAP_REGEX = "--";

    private String airlineStr;
    private String originAirportCode;
    private String destinationAirportCode;
    private String directionStr;

    public RouteMap(String airlineStr, String originAirportCode, String destinationAirportCode, String directionStr) {
        this.airlineStr = airlineStr;
        this.originAirportCode = originAirportCode;
        this.destinationAirportCode = destinationAirportCode;
        this.directionStr = directionStr;
    }

    public RouteMap(String routeMapString) {
        String[] routeMapArr = routeMapString.split(ROUTE_MAP_REGEX);
        this.airlineStr = routeMapArr[0];
        this.originAirportCode = routeMapArr[1];
        this.destinationAirportCode = routeMapArr[2];
        this.directionStr = routeMapArr[3];
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RouteMap routeMap = (RouteMap) o;
        return Objects.equals(airlineStr, routeMap.airlineStr) &&
                Objects.equals(originAirportCode, routeMap.originAirportCode) &&
                Objects.equals(destinationAirportCode, routeMap.destinationAirportCode) &&
                Objects.equals(directionStr, routeMap.directionStr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(airlineStr, originAirportCode, destinationAirportCode, directionStr);
    }

    @Override
    public String toString() {
        return "" + airlineStr + "--" + originAirportCode + "--" + destinationAirportCode + "--" + directionStr;
    }
}
