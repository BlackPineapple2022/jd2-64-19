package by.academy.it.travelcompany.listener;

import by.academy.it.travelcompany.db.connection.pool.TcDataSource;
import by.academy.it.travelcompany.db.migration.DbMigration;
import by.academy.it.travelcompany.scanner.robot.FlightRobot;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.util.*;

@Slf4j
@WebListener()
public class TravelCompanyContextInitListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("Context initialized");
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("mysql_hikari");
            TcDataSource.configure(bundle);
            DataSource dataSource = TcDataSource.getDataSource();
            DbMigration.migrate(dataSource);

        } catch (Throwable t) {
            log.error("error", t);
            throw new RuntimeException("Datasource initialisation error", t);
        }

        FlightRobot flightRobotRY = FlightRobot.getFlightRobotRY();
        FlightRobot flightRobotWIZZ = FlightRobot.getFlightRobotWIZZ();

        flightRobotRY.setActive(true);
        flightRobotWIZZ.setActive(true);

        flightRobotRY.start();
        flightRobotWIZZ.start();

    }

}

