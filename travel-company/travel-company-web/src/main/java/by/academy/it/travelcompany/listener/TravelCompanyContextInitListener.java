package by.academy.it.travelcompany.listener;

import by.academy.it.travelcompany.db.connection.pool.TcDataSource;
import by.academy.it.travelcompany.db.migration.DbMigration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.util.ResourceBundle;


@WebListener()
    public class TravelCompanyContextInitListener implements ServletContextListener{

        private static final Logger logger = LoggerFactory.getLogger(TravelCompanyContextInitListener.class);

        @Override
        public void contextInitialized(ServletContextEvent sce) {
            logger.info("Context initialized");
            try {
                ResourceBundle bundle = ResourceBundle.getBundle("mysql_hikari");
                TcDataSource.configure(bundle);
                DataSource dataSource = TcDataSource.getDataSource();
                DbMigration.migrate(dataSource);
            } catch (Exception e) {
                logger.error("error", e);
                throw new RuntimeException("Datasource initialisation error", e);
            }
        }

        @Override
        public void contextDestroyed(ServletContextEvent sce) {
            logger.info("Context destroyed");
        }
    }

