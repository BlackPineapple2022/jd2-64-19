package by.academy.it.travelcompany.install;

import by.academy.it.travelcompany.db.connection.pool.TcDataSource;
import by.academy.it.travelcompany.db.migration.DbMigration;
import by.academy.it.travelcompany.service.global.imp.AirportServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.util.ResourceBundle;

public class AirportInstall {
    private static final Logger LOGGER = LoggerFactory.getLogger(AirportInstall.class);

    public static void main(String[] args) {
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("mysql_hikari");
            TcDataSource.configure(bundle);
            DataSource dataSource = TcDataSource.getDataSource();
            DbMigration.migrate(dataSource);

        } catch (Exception e) {
            LOGGER.error("error", e);
            throw new RuntimeException("Datasource initialisation error", e);
        }
        new AirportInstall().installAllAirportToBase();
    }

    public void installAllAirportToBase(){
        AirportServiceImpl airportService = AirportServiceImpl.getInstance();
        airportService.installAllAirportToBase();
    }
}
