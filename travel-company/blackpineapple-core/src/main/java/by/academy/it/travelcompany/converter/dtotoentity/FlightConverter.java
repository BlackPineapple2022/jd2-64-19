package by.academy.it.travelcompany.converter.dtotoentity;

import by.academy.it.travelcompany.dto.FlightDTO;
import by.academy.it.travelcompany.entity.Flight;

public interface FlightConverter {

    Flight convert(FlightDTO flightDTO);

    FlightDTO convert(Flight flight);

}
