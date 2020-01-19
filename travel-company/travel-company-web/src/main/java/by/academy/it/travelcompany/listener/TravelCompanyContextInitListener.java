package by.academy.it.travelcompany.listener;

import by.academy.it.travelcompany.db.connection.pool.TcDataSource;
import by.academy.it.travelcompany.db.migration.DbMigration;
import by.academy.it.travelcompany.scanner.flightscanner.FlightScannerImpl;
import by.academy.it.travelcompany.service.global.imp.CurrencyServiceImpl;
import by.academy.it.travelcompany.service.global.imp.FlightServiceImpl;
import by.academy.it.travelcompany.service.global.imp.RouteMapServiceImpl;
import by.academy.it.travelcompany.travelitem.currency.Currency;
import by.academy.it.travelcompany.travelitem.flight.Flight;
import by.academy.it.travelcompany.travelitem.routemap.RouteMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

@WebListener()
public class TravelCompanyContextInitListener implements ServletContextListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(TravelCompanyContextInitListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOGGER.info("Context initialized");
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("mysql_hikari");
            TcDataSource.configure(bundle);
            DataSource dataSource = TcDataSource.getDataSource();
            DbMigration.migrate(dataSource);


        } catch (Exception e) {
            LOGGER.error("error", e);
            throw new RuntimeException("Datasource initialisation error", e);
        }

            /*Airport a = AirportServiceImpl.getInstance().getAirportByCode("VN4");
            System.out.println(a);*/
        //RouteMapServiceImpl.getInstance().addToBase("RY--VNO--BGY--Direct");
        //new AirlineServiceImpl().add(new Airline(null,"BOBO"));
        //System.out.println(RouteMapServiceImpl.getInstance().getRouteMapByParam("RY","VNO","2BGY","Direct").get().getOriginAirport().getId());
        /*Optional<RouteMap> r = RouteMapServiceImpl.getInstance().getRouteMapByParam("RY", "VNO", "BGY", "Direct");

        RouteMap routeMap = r.get();
        LocalDateTime departureTime = LocalDateTime.now();
        LocalDateTime arriveTime = LocalDateTime.now().plusDays(1);
        Flight flight = new Flight(
                null,
                routeMap,
                departureTime,
                arriveTime,
                new Currency(CurrencyServiceImpl.getInstance().getIdByName("EUR"), "EUR"),
                12.02,
                "SSS"
        );
        flight.setSearchId(123123123L);
        System.out.println(FlightServiceImpl.getInstance().create(flight).getId());


        FlightScannerImpl flightScanner = new FlightScannerImpl(1L, routeMap, LocalDate.now(),10);
        List<Flight> flightList = flightScanner.parseFlightsRY();
        for (Flight f :flightList) {
            System.out.println(f);
            FlightServiceImpl.getInstance().create(f);
        }*/


       /* Optional<RouteMap> r = RouteMapServiceImpl.getInstance().getRouteMapByParam("WIZZ", "VNO", "MXP", "Direct");
        RouteMap routeMap = r.get();
        FlightScannerImpl flightScanner = new FlightScannerImpl(2L, routeMap, LocalDate.now(),10);
        List<Flight> flightList = flightScanner.parseFlightsWIZZ();
        for (Flight f :flightList) {
            System.out.println(f);
            FlightServiceImpl.getInstance().create(f);
        }*/

    }

}

