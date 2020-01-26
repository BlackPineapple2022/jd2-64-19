package by.academy.it.travelcompany.db.migration;

import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;

import javax.sql.DataSource;

@Slf4j
public final class DbMigration {

    private DbMigration() {
    }

    public static void migrate(DataSource dataSource) {
        log.info("Starting DB migration...");
        Flyway flyway = Flyway.configure().dataSource(dataSource).load();
        flyway.migrate();
        log.info("DB migration finished");
    }

}
