package by.academy.it.travelcompany.controller.rest;

import by.academy.it.travelcompany.FlightScannerData;
import by.academy.it.travelcompany.dto.FlightDTO;
import by.academy.it.travelcompany.scanner.FlightScanner;
import by.academy.it.travelcompany.scanner.impl.RYFlightScanner;
import by.academy.it.travelcompany.scanner.impl.WIZZFlightScanner;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "flights/")
public class FlightRestController {

    private static final Long timeout = 2000L;
    private static final Integer multiplier = 0;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public String updateFlight(
            @RequestBody List<FlightDTO> flightDTOs) {

        List<JSONObject> response = new ArrayList<>();

        log.info("REST: start updating flights " + flightDTOs);

        List<FlightScannerData> ryDataList = new ArrayList<>();
        List<FlightScannerData> wizzDataList = new ArrayList<>();
        List<FlightDTO> ryFlightDTO = new ArrayList<>();
        List<FlightDTO> wizzFlightDTO = new ArrayList<>();


        for (FlightDTO flightDTO : flightDTOs) {
            if (flightDTO.getRouteMap().split("--")[0].equals("RY")) {
                String routeMap = flightDTO.getRouteMap();
                LocalDate localDate = LocalDateTime.parse(flightDTO.getDepartureDateTime()).toLocalDate();
                ryDataList.add(new FlightScannerData(routeMap, 0, localDate));
                ryFlightDTO.add(flightDTO);
            }
            if (flightDTO.getRouteMap().split("--")[0].equals("WIZZ")) {
                String routeMap = flightDTO.getRouteMap();
                LocalDate localDate = LocalDateTime.parse(flightDTO.getDepartureDateTime()).toLocalDate();
                wizzDataList.add(new FlightScannerData(routeMap, 0, localDate));
                wizzFlightDTO.add(flightDTO);
            }
        }

        FlightScanner ry = new RYFlightScanner(ryDataList);
        FlightScanner wizz = new WIZZFlightScanner(wizzDataList);

        List<JSONObject> ryRespDirty = ry.parse(timeout, multiplier);
        List<JSONObject> wizzRespDirty = wizz.parse(timeout, multiplier);

        for (FlightDTO f : ryFlightDTO) {
            ryRespDirty.removeIf(j ->
                    (j.getString("departureDateTime").split("T")[0].equals(f.getDepartureDateTime().split("T")[0]))
                            && !j.getString("flightNumber").equals(f.getFlightNumber())
            );
        }

        for (FlightDTO f : wizzFlightDTO) {
            wizzRespDirty.removeIf(j ->
                    (j.getString("departureDateTime").split("T")[0].equals(f.getDepartureDateTime().split("T")[0]))
                            && !j.getString("flightNumber").equals(f.getFlightNumber())
            );
        }

        response.addAll(ryRespDirty);
        response.addAll(wizzRespDirty);
        log.info("updating flight are completed: "+ response);
        return response.toString();
    }
}
