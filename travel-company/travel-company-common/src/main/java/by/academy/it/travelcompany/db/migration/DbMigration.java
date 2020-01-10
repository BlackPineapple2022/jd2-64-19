package by.academy.it.travelcompany.db.migration;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;

    public final class DbMigration {

        private static final Logger LOGGER = LoggerFactory.getLogger(DbMigration.class);

        private DbMigration() {
        }

        public static void migrate(DataSource dataSource) {
            LOGGER.info("Starting DB migration...");
            Flyway flyway = Flyway.configure().dataSource(dataSource).load();
            flyway.migrate();
            LOGGER.info("DB migration finished");
        }
}
