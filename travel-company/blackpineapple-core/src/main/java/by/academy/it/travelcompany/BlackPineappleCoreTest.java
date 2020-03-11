package by.academy.it.travelcompany;

import by.academy.it.travelcompany.entity.Airline;
import by.academy.it.travelcompany.entity.Direction;
import by.academy.it.travelcompany.install.airline.AirlineInstaller;
import by.academy.it.travelcompany.install.airport.AirportInstaller;
import by.academy.it.travelcompany.install.direction.DirectionInstaller;
import by.academy.it.travelcompany.install.routemap.RouteMapInstaller;
import by.academy.it.travelcompany.service.AirlineService;
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




    }

}
