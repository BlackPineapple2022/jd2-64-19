package by.academy.it.travelcompany.converter.dtotoentity.impl;

import by.academy.it.travelcompany.converter.dtotoentity.FlightConverter;
import by.academy.it.travelcompany.dto.FlightDTO;
import by.academy.it.travelcompany.entity.Currency;
import by.academy.it.travelcompany.entity.Flight;
import by.academy.it.travelcompany.entity.RouteMap;
import by.academy.it.travelcompany.serializer.LocalDateTimeSerializer;
import by.academy.it.travelcompany.service.CurrencyService;
import by.academy.it.travelcompany.service.RouteMapService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class FlightConverterImpl implements FlightConverter {

    @Autowired
    RouteMapService routeMapService;
    @Autowired
    CurrencyService currencyService;

    @Override
    public Flight convert(FlightDTO flightDTO) {
        String routeMapStr = flightDTO.getRouteMap();
        RouteMap routeMap = routeMapService.getByRouteMapString(routeMapStr);
        Currency currency = currencyService.findByCode(flightDTO.getCurrency());

        LocalDateTime departure = LocalDateTime.parse(flightDTO.getDepartureDateTime());
        LocalDateTime arrive = LocalDateTime.parse(flightDTO.getArriveDateTime());

        return new Flight(flightDTO.getAmount(),
                flightDTO.getFlightNumber(),
                departure,
                arrive,
                routeMap,
                currency);


    }
}
