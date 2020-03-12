package by.academy.it.travelcompany;

import by.academy.it.travelcompany.install.airline.AirlineInstaller;
import by.academy.it.travelcompany.install.airport.AirportInstaller;
import by.academy.it.travelcompany.install.currency.CurrencyInstaller;
import by.academy.it.travelcompany.install.direction.DirectionInstaller;
import by.academy.it.travelcompany.install.flightjournal.FlightJournalInstaller;
import by.academy.it.travelcompany.install.role.RoleInstaller;
import by.academy.it.travelcompany.install.routemap.RouteMapInstaller;
import by.academy.it.travelcompany.install.user.UserInstaller;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BlackPineappleCoreTest {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");

        AirlineInstaller airlineInstaller = context.getBean(AirlineInstaller.class);
        airlineInstaller.install();

        DirectionInstaller directionInstaller = context.getBean(DirectionInstaller.class);
        directionInstaller.install();

        AirportInstaller airportInstaller = context.getBean(AirportInstaller.class);
        airportInstaller.install();

        RouteMapInstaller routeMapInstaller = context.getBean(RouteMapInstaller.class);
        routeMapInstaller.install();

        CurrencyInstaller currencyInstaller = context.getBean(CurrencyInstaller.class);
        currencyInstaller.install();

        RoleInstaller roleInstaller = context.getBean(RoleInstaller.class);
        roleInstaller.install();

        FlightJournalInstaller flightJournalInstaller = context.getBean(FlightJournalInstaller.class);
        flightJournalInstaller.install();

        UserInstaller userInstaller = context.getBean(UserInstaller.class);
        userInstaller.install();


    }

}
