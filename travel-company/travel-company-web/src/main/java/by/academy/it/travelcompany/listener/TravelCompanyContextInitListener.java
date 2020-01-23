package by.academy.it.travelcompany.listener;

import by.academy.it.travelcompany.dao.impl.FlightDAOImpl;
import by.academy.it.travelcompany.dao.impl.RouteMapDAOImpl;
import by.academy.it.travelcompany.db.connection.pool.TcDataSource;
import by.academy.it.travelcompany.db.migration.DbMigration;
import by.academy.it.travelcompany.scanner.flightscanner.FlightScannerImpl;
import by.academy.it.travelcompany.service.global.imp.CurrencyServiceImpl;
import by.academy.it.travelcompany.service.global.imp.FavouriteServiceImpl;
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
import java.util.*;

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

        /*String a1 = "VNO";
        String a2 = "KUN";
        String a3 = "WMI";
        String a4 = "WAW";

        String a5 = "BGY";
        String a6 = "MXP";

        Set<String> set1 = new HashSet<>();
        Set<String> set2 = new HashSet<>();
        Set<String> set3 = new HashSet<>();
        Set<String> set4 = new HashSet<>();

        set1.add(a1);
        set1.add(a2);
        set1.add(a3);
        set1.add(a4);

        set2.add(a5);
        set2.add(a6);

        set3.add(a5);
        set3.add(a6);

        set4.add(a1);
        set4.add(a2);
        set4.add(a3);
        set4.add(a4);

        try {
            Set<RouteMap> routeMapSet = RouteMapDAOImpl.getInstance().getRouteMapSetByAirportCodeSets(set1, set2, set3, set4);
            System.out.println((routeMapSet));
        } catch (Exception e) {

        }*/


        FlightScannerImpl flightScanner1 = new FlightScannerImpl(23L, RouteMapServiceImpl.getInstance().read(23L).get(),LocalDate.now(),300);
        FlightScannerImpl flightScanner2 = new FlightScannerImpl(24L, RouteMapServiceImpl.getInstance().read(24L).get(),LocalDate.now(),300);
        FlightScannerImpl flightScanner11 = new FlightScannerImpl(37L, RouteMapServiceImpl.getInstance().read(37L).get(),LocalDate.now(),300);
        FlightScannerImpl flightScanner12 = new FlightScannerImpl(38L, RouteMapServiceImpl.getInstance().read(38L).get(),LocalDate.now(),300);

        flightScanner1.start();
        flightScanner2.start();
        flightScanner11.start();
        flightScanner12.start();

    }

}

