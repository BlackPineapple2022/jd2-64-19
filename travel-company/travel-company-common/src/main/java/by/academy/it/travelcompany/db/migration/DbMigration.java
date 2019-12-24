package by.academy.it.travelcompany.db.migration;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;

    public final class DbMigration {

        private static final Logger logger = LoggerFactory.getLogger(DbMigration.class);

        private DbMigration() {
        }

        public static void migrate(DataSource dataSource) {
            logger.info("Starting DB migration...");
            Flyway flyway = Flyway.configure().dataSource(dataSource).load();
            flyway.migrate();
            logger.info("DB migration finished");
        }
}
